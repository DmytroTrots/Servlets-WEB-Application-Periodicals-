package com.trots.periodacals.entity;

/**
 * The type Receipt has periodical.
 */
public class ReceiptHasPeriodical {
    private int months;
    private double pricePerMonth;
    private int periodicalSellId;
    private int receiptId;

    /**
     * Gets months.
     *
     * @return the months
     */
    public int getMonths() {
        return months;
    }

    /**
     * Sets months.
     *
     * @param months the months
     */
    public void setMonths(int months) {
        this.months = months;
    }

    /**
     * Gets price per month.
     *
     * @return the price per month
     */
    public double getPricePerMonth() {
        return pricePerMonth;
    }

    /**
     * Sets price per month.
     *
     * @param pricePerMonth the price per month
     */
    public void setPricePerMonth(double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    /**
     * Gets periodical sell id.
     *
     * @return the periodical sell id
     */
    public int getPeriodicalSellId() {
        return periodicalSellId;
    }

    /**
     * Sets periodical sell id.
     *
     * @param periodicalSellId the periodical sell id
     */
    public void setPeriodicalSellId(int periodicalSellId) {
        this.periodicalSellId = periodicalSellId;
    }

    /**
     * Gets receipt id.
     *
     * @return the receipt id
     */
    public int getReceiptId() {
        return receiptId;
    }

    /**
     * Sets receipt id.
     *
     * @param receiptId the receipt id
     */
    public void setReceiptId(int receiptId) {
        this.receiptId = receiptId;
    }
}
