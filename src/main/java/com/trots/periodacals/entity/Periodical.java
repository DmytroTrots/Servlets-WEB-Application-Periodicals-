package com.trots.periodacals.entity;

public class Periodical {
    private int sellId;
    private String title;
    private int numberOfPages;
    private int periodicityPerYear;
    private int percentageOfAdvertising;
    private Double pricePerMonth;
    private String description;
    private double rating;
    private int publisherId;
    private String image;

    private String publisher;

    private String telephonePub;

    public Periodical(String title, int numberOfPages, int periodicityPerYear, int percentageOfAdvertising, Double pricePerMonth, String description, double rating) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.periodicityPerYear = periodicityPerYear;
        this.percentageOfAdvertising = percentageOfAdvertising;
        this.pricePerMonth = pricePerMonth;
        this.description = description;
        this.rating = rating;
    }

    public Periodical() {

    }

    public String getTelephonePub() {
        return telephonePub;
    }

    public void setTelephonePub(String telephonePub) {
        this.telephonePub = telephonePub;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getPublisher() {
        return publisher;
    }

    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

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

    public Double getPricePerMonth() {
        return pricePerMonth;
    }

    public void setPricePerMonth(Double pricePerMonth) {
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

    public int getPublisherId() {
        return publisherId;
    }

    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
}
