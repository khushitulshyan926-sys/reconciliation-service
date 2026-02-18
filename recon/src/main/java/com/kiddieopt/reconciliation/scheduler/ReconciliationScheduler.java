package com.kiddieopt.reconciliation.scheduler;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.kiddieopt.reconciliation.service.ReconciliationService;

@Component
public class ReconciliationScheduler {

    private final ReconciliationService service;

    public ReconciliationScheduler(ReconciliationService service) {
        this.service = service;
    }

    @Scheduled(fixedDelay = 300000) // 5 minutes
    public void runReconciliation() {
        service.reconcile();
    }
}

