package com.kiddieopt.reconciliation.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.kiddieopt.reconciliation.entity.ProcessedTrade;

@Repository
public interface ProcessedTradeRepository extends JpaRepository<ProcessedTrade, Long> {

    long countByValidationStatus(String status);
}
