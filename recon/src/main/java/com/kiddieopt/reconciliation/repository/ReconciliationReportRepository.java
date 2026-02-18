package com.kiddieopt.reconciliation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiddieopt.reconciliation.entity.ReconciliationReport;

@Repository
public interface ReconciliationReportRepository extends JpaRepository<ReconciliationReport, Long> {
}
