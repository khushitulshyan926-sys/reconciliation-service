package com.kiddieopt.reconciliation.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.kiddieopt.reconciliation.dto.ValidationResult;
import com.kiddieopt.reconciliation.entity.ProcessedTrade;
import com.kiddieopt.reconciliation.entity.RawTrade;
import com.kiddieopt.reconciliation.repository.ProcessedTradeRepository;
import com.kiddieopt.reconciliation.repository.RawTradeRepository;

@Service
public class ReconciliationService {

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

    public void reconcile() {

        List<RawTrade> trades = rawRepo.findByProcessedFlagFalse();

        for (RawTrade trade : trades) {

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
        }

        reportService.generateReport();
    }
}
