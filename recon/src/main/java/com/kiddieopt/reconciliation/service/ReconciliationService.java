package com.kiddieopt.reconciliation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kiddieopt.reconciliation.dto.ValidationResult;
import com.kiddieopt.reconciliation.entity.ProcessedTrade;
import com.kiddieopt.reconciliation.entity.RawTrade;
import com.kiddieopt.reconciliation.repository.ProcessedTradeRepository;
import com.kiddieopt.reconciliation.repository.RawTradeRepository;

@Service
public class ReconciliationService {

    private static final Logger log =
            LoggerFactory.getLogger(ReconciliationService.class);

    private final RawTradeRepository rawRepo;
    private final ProcessedTradeRepository processedRepo;
    private final RuleEngine ruleEngine;
    private final ReportService reportService;

    public ReconciliationService(RawTradeRepository rawRepo,
                                 ProcessedTradeRepository processedRepo,
                                 RuleEngine ruleEngine,
                                 ReportService reportService) {
        this.rawRepo = rawRepo;
        this.processedRepo = processedRepo;
        this.ruleEngine = ruleEngine;
        this.reportService = reportService;
    }

    @Transactional
    public void reconcile() {

        List<RawTrade> trades = rawRepo.findByProcessedFlagFalse();

        if (trades.isEmpty()) {
            log.info("No unprocessed trades found for reconciliation");
            return;
        }

        log.info("Starting reconciliation for {} trades", trades.size());

        for (RawTrade trade : trades) {
            try {
                List<ValidationResult> results = ruleEngine.validate(trade);

                boolean isValid = results.stream()
                                         .allMatch(ValidationResult::isValid);

                String failureReasons = results.stream()
                        .filter(r -> !r.isValid())
                        .map(r -> r.getRuleName() + ": " + r.getMessage())
                        .collect(Collectors.joining("; "));

                ProcessedTrade processed = new ProcessedTrade();
                processed.setTradeId(trade.getTradeId());
                processed.setValidationStatus(isValid ? "VALID" : "INVALID");
                processed.setValidationReason(
                        isValid ? "All validations passed" : failureReasons
                );

                processedRepo.save(processed);

                trade.setProcessedFlag(true);
                rawRepo.save(trade);

                log.debug("Trade {} processed with status {}",
                        trade.getTradeId(),
                        isValid ? "VALID" : "INVALID");

            } catch (Exception ex) {
                log.error("Failed to process trade {}",
                        trade.getTradeId(), ex);
            }
        }

        log.info("Reconciliation completed. Generating report...");
        reportService.generateReport();
    }
}