package com.trots.periodacals.entity;

public class ReceiptHasPeriodical {
    private int months;
    private double pricePerMonth;
    private int periodicalSellId;
    private int receiptId;

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public int getPeriodicalSellId() {
        return periodicalSellId;
    }

    public void setPeriodicalSellId(int periodicalSellId) {
        this.periodicalSellId = periodicalSellId;
    }

    public int getReceiptId() {
        return receiptId;
    }

    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
}
