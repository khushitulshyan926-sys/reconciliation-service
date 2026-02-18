package com.kiddieopt.reconciliation.dto;

public class ValidationResult {

    private String ruleName;
    private boolean valid;
    private String message;

    public ValidationResult(String ruleName, boolean valid, String message) {
        this.ruleName = ruleName;
        this.valid = valid;
        this.message = message;
    }

    public String getRuleName() {
        return ruleName;
    }

    public boolean isValid() {
        return valid;
    }

    public String getMessage() {
        return message;
    }
}
