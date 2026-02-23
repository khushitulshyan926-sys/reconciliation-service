package com.kiddieopt.reconciliation.scheduler;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kiddieopt.reconciliation.service.ReconciliationService;

@Component
public class ReconciliationScheduler {

    private static final Logger log =
            LoggerFactory.getLogger(ReconciliationScheduler.class);

    private final ReconciliationService service;

    public ReconciliationScheduler(ReconciliationService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 300000) // 5 minutes
    public void runReconciliation() {
        log.info("Scheduled reconciliation started");

        try {
            service.reconcile();
            log.info("Scheduled reconciliation completed successfully");
        } catch (Exception ex) {
            log.error("Scheduled reconciliation failed", ex);
        }
    }
}