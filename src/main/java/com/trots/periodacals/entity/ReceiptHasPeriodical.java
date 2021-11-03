package com.trots.periodacals.entity;

public class ReceiptHasPeriodical {
    private int numberOfMonths;
    private int price;
    private int fullPrice;

    public int getNumberOfMonths() {
        return numberOfMonths;
    }

    public void setNumberOfMonths(int numberOfMonths) {
        this.numberOfMonths = numberOfMonths;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public int getFullPrice() {
        return fullPrice;
    }

    public void setFullPrice(int fullPrice) {
        this.fullPrice = fullPrice;
    }
}
