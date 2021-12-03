package com.trots.periodacals.entity;

/**
 * The type Periodical.
 */
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

    /**
     * Instantiates a new Periodical.
     *
     * @param title                   the title
     * @param numberOfPages           the number of pages
     * @param periodicityPerYear      the periodicity per year
     * @param percentageOfAdvertising the percentage of advertising
     * @param pricePerMonth           the price per month
     * @param description             the description
     * @param rating                  the rating
     */
    public Periodical(String title, int numberOfPages, int periodicityPerYear, int percentageOfAdvertising, Double pricePerMonth, String description, double rating) {
        this.title = title;
        this.numberOfPages = numberOfPages;
        this.periodicityPerYear = periodicityPerYear;
        this.percentageOfAdvertising = percentageOfAdvertising;
        this.pricePerMonth = pricePerMonth;
        this.description = description;
        this.rating = rating;
    }

    /**
     * Instantiates a new Periodical.
     */
    public Periodical() {

    }

    /**
     * Gets telephone pub.
     *
     * @return the telephone pub
     */
    public String getTelephonePub() {
        return telephonePub;
    }

    /**
     * Sets telephone pub.
     *
     * @param telephonePub the telephone pub
     */
    public void setTelephonePub(String telephonePub) {
        this.telephonePub = telephonePub;
    }

    /**
     * Gets image.
     *
     * @return the image
     */
    public String getImage() {
        return image;
    }

    /**
     * Sets image.
     *
     * @param image the image
     */
    public void setImage(String image) {
        this.image = image;
    }

    /**
     * Gets publisher.
     *
     * @return the publisher
     */
    public String getPublisher() {
        return publisher;
    }

    /**
     * Sets publisher.
     *
     * @param publisher the publisher
     */
    public void setPublisher(String publisher) {
        this.publisher = publisher;
    }

    /**
     * Gets sell id.
     *
     * @return the sell id
     */
    public int getSellId() {
        return sellId;
    }

    /**
     * Sets sell id.
     *
     * @param sellId the sell id
     */
    public void setSellId(int sellId) {
        this.sellId = sellId;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets number of pages.
     *
     * @return the number of pages
     */
    public int getNumberOfPages() {
        return numberOfPages;
    }

    /**
     * Sets number of pages.
     *
     * @param numberOfPages the number of pages
     */
    public void setNumberOfPages(int numberOfPages) {
        this.numberOfPages = numberOfPages;
    }

    /**
     * Gets periodicity per year.
     *
     * @return the periodicity per year
     */
    public int getPeriodicityPerYear() {
        return periodicityPerYear;
    }

    /**
     * Sets periodicity per year.
     *
     * @param periodicityPerYear the periodicity per year
     */
    public void setPeriodicityPerYear(int periodicityPerYear) {
        this.periodicityPerYear = periodicityPerYear;
    }

    /**
     * Gets percentage of advertising.
     *
     * @return the percentage of advertising
     */
    public int getPercentageOfAdvertising() {
        return percentageOfAdvertising;
    }

    /**
     * Sets percentage of advertising.
     *
     * @param percentageOfAdvertising the percentage of advertising
     */
    public void setPercentageOfAdvertising(int percentageOfAdvertising) {
        this.percentageOfAdvertising = percentageOfAdvertising;
    }

    /**
     * Gets price per month.
     *
     * @return the price per month
     */
    public Double getPricePerMonth() {
        return pricePerMonth;
    }

    /**
     * Sets price per month.
     *
     * @param pricePerMonth the price per month
     */
    public void setPricePerMonth(Double pricePerMonth) {
        this.pricePerMonth = pricePerMonth;
    }

    /**
     * Gets description.
     *
     * @return the description
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets description.
     *
     * @param description the description
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets rating.
     *
     * @return the rating
     */
    public double getRating() {
        return rating;
    }

    /**
     * Sets rating.
     *
     * @param rating the rating
     */
    public void setRating(double rating) {
        this.rating = rating;
    }

    /**
     * Gets publisher id.
     *
     * @return the publisher id
     */
    public int getPublisherId() {
        return publisherId;
    }

    /**
     * Sets publisher id.
     *
     * @param publisherId the publisher id
     */
    public void setPublisherId(int publisherId) {
        this.publisherId = publisherId;
    }
}
