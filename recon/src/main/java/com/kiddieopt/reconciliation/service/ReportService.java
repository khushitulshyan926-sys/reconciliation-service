package com.kiddieopt.reconciliation.service;

import org.springframework.stereotype.Service;

import com.kiddieopt.reconciliation.entity.ReconciliationReport;
import com.kiddieopt.reconciliation.repository.ProcessedTradeRepository;
import com.kiddieopt.reconciliation.repository.RawTradeRepository;
import com.kiddieopt.reconciliation.repository.ReconciliationReportRepository;

@Service
public class ReportService {

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
    }
}

