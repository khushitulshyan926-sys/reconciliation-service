package com.kiddieopt.reconciliation.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kiddieopt.reconciliation.service.ReconciliationService;

@RestController
@RequestMapping("/reconciliation")
public class ReconciliationController {

    private final ReconciliationService service;

    public ReconciliationController(ReconciliationService service) {
        this.service = service;
    }

    @PostMapping("/run")
    public String run() {
        service.reconcile();
        return "Reconciliation executed";
    }
}

