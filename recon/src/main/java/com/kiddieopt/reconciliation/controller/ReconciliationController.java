package com.kiddieopt.reconciliation.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiddieopt.reconciliation.service.ReconciliationService;

@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {

    private static final Logger log =
            LoggerFactory.getLogger(ReconciliationController.class);

    private final ReconciliationService service;

    public ReconciliationController(ReconciliationService service) {
        this.service = service;
    }

    @PostMapping("/run")
    public ResponseEntity<String> run() {
        log.info("Reconciliation process triggered");

        try {
            service.reconcile();
            log.info("Reconciliation process completed successfully");
            return ResponseEntity.ok("Reconciliation executed");
        } catch (Exception ex) {
            log.error("Error occurred during reconciliation", ex);
            return ResponseEntity.internalServerError()
                    .body("Reconciliation failed");
        }
    }
}
