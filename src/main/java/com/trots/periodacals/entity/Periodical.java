package com.trots.periodacals.entity;

public class Periodical {
    private int sellId;
    private String title;
    private int numberOfPages;
    private int periodicityPerYear;
    private int percentageOfAdvertising;
    private int pricePerMonth;
    private String description;
    private double rating;
    private String language;
    private int publisherId;

    public int getSellId() {
        return sellId;
    }

    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getNumberOfPages() {
        return numberOfPages;
    }

    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    public int getPeriodicityPerYear() {
        return periodicityPerYear;
    }

    public void setPeriodicityPerYear(int periodicityPerYear) {
        this.periodicityPerYear = periodicityPerYear;
    }

    public int getPercentageOfAdvertising() {
        return percentageOfAdvertising;
    }

    public void setPercentageOfAdvertising(int percentageOfAdvertising) {
        this.percentageOfAdvertising = percentageOfAdvertising;
    }

    public int getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(int pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
}
