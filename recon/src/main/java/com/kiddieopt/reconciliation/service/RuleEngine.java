package com.kiddieopt.reconciliation.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.kiddieopt.reconciliation.dto.ValidationResult;
import com.kiddieopt.reconciliation.entity.RawTrade;

@Service
public class RuleEngine {

    public List<ValidationResult> validate(RawTrade trade) {

        List<ValidationResult> results = new ArrayList<>();

        // Trade ID validation
        if (trade.getTradeId() == null) {
            results.add(new ValidationResult(
                    "TradeIdValidation",
                    false,
                    "Trade ID missing"
            ));
        } else {
            results.add(new ValidationResult(
                    "TradeIdValidation",
                    true,
                    "Valid Trade ID"
            ));
        }

        // Quantity validation
        if (trade.getQuantity() == null || trade.getQuantity() <= 0) {
            results.add(new ValidationResult(
                    "QuantityValidation",
                    false,
                    "Invalid quantity"
            ));
        } else {
            results.add(new ValidationResult(
                    "QuantityValidation",
                    true,
                    "Valid quantity"
            ));
        }

        // Price validation
        if (trade.getPrice() == null || trade.getPrice() <= 0) {
            results.add(new ValidationResult(
                    "PriceValidation",
                    false,
                    "Invalid price"
            ));
        } else {
            results.add(new ValidationResult(
                    "PriceValidation",
                    true,
                    "Valid price"
            ));
        }

        // Side validation
        if (trade.getSide() == null ||
                (!trade.getSide().equalsIgnoreCase("BUY")
                 && !trade.getSide().equalsIgnoreCase("SELL"))) {

            results.add(new ValidationResult(
                    "SideValidation",
                    false,
                    "Side must be BUY or SELL"
            ));
        } else {
            results.add(new ValidationResult(
                    "SideValidation",
                    true,
                    "Valid side"
            ));
        }

        return results;
    }
}