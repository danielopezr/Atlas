package com.bank.controller;

public class CurrencyManager {
    public double convertToPesos(double amount, String currencyType) {
        double conversionRate = getConversionRate(currencyType);
        double pesosAmount = amount * conversionRate;
        return pesosAmount;
    }

    private double getConversionRate(String currencyType) {
        if (currencyType.equals("dollar")) {
            return 3868.47;
        } else if (currencyType.equals("euro")) {
            return 4221.19;
        } else {
            return 1.0;
        }
    }
}
