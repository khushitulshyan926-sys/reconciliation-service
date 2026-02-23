package com.kiddieopt.reconciliation.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.kiddieopt.reconciliation.entity.ReconciliationReport;
import com.kiddieopt.reconciliation.repository.ProcessedTradeRepository;
import com.kiddieopt.reconciliation.repository.RawTradeRepository;
import com.kiddieopt.reconciliation.repository.ReconciliationReportRepository;

@Service
public class ReportService {

    private static final Logger log =
            LoggerFactory.getLogger(ReportService.class);

    private final RawTradeRepository rawRepo;
    private final ProcessedTradeRepository processedRepo;
    private final ReconciliationReportRepository reportRepo;

    public ReportService(RawTradeRepository rawRepo,
                         ProcessedTradeRepository processedRepo,
                         ReconciliationReportRepository reportRepo) {
        this.rawRepo = rawRepo;
        this.processedRepo = processedRepo;
        this.reportRepo = reportRepo;
    }

    public void generateReport() {

        try {
            long totalRaw = rawRepo.count();
            long totalProcessed = processedRepo.count();
            long validCount = processedRepo.countByValidationStatus("VALID");
            long invalidCount = processedRepo.countByValidationStatus("INVALID");

            double matchPercentage =
                    totalRaw == 0 ? 0 : (validCount * 100.0) / totalRaw;

            ReconciliationReport report = new ReconciliationReport();
            report.setTotalRaw(totalRaw);
            report.setTotalProcessed(totalProcessed);
            report.setValidCount(validCount);
            report.setInvalidCount(invalidCount);
            report.setMatchPercentage(matchPercentage);

            reportRepo.save(report);

            log.info(
                "Reconciliation report generated: totalRaw={}, valid={}, invalid={}, matchPercentage={}%",
                totalRaw, validCount, invalidCount, matchPercentage
            );

        } catch (Exception ex) {
            log.error("Failed to generate reconciliation report", ex);
            throw ex; // rethrow so transaction (if any) can rollback
        }
    }
}
