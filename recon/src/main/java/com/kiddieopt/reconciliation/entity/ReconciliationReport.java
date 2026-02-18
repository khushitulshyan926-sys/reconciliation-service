package com.kiddieopt.reconciliation.entity;

import java.time.LocalDateTime;

import jakarta.persistence.*;

@Entity
@Table(name = "reconciliation_report", schema = "audit_schema")
public class ReconciliationReport {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long totalRaw;
    private Long totalProcessed;
    private Long validCount;
    private Long invalidCount;
    private Double matchPercentage;

    private LocalDateTime generatedAt = LocalDateTime.now();

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalRaw() {
        return totalRaw;
    }

    public void setTotalRaw(Long totalRaw) {
        this.totalRaw = totalRaw;
    }

    public Long getTotalProcessed() {
        return totalProcessed;
    }

    public void setTotalProcessed(Long totalProcessed) {
        this.totalProcessed = totalProcessed;
    }

    public Long getValidCount() {
        return validCount;
    }

    public void setValidCount(Long validCount) {
        this.validCount = validCount;
    }

    public Long getInvalidCount() {
        return invalidCount;
    }

    public void setInvalidCount(Long invalidCount) {
        this.invalidCount = invalidCount;
    }

    public Double getMatchPercentage() {
        return matchPercentage;
    }

    public void setMatchPercentage(Double matchPercentage) {
        this.matchPercentage = matchPercentage;
    }

    public LocalDateTime getGeneratedAt() {
        return generatedAt;
    }

    public void setGeneratedAt(LocalDateTime generatedAt) {
        this.generatedAt = generatedAt;
    }

    

}
