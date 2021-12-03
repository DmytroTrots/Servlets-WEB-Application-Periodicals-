package com.trots.periodacals.entity;

import java.sql.Date;

/**
 * The type Receipt.
 */
public class Receipt extends ReceiptHasPeriodical {
    private int id;
    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;
    private int statusId;
    private int userId;
    private String email;
    private Date create_time;

    private String statusName;
    private String publisherName;
    private String title;
    private String allPeriodicalsId;

    /**
     * Gets all periodicals id.
     *
     * @return the all periodicals id
     */
    public String getAllPeriodicalsId() {
        return allPeriodicalsId;
    }

    /**
     * Sets all periodicals id.
     *
     * @param allPeriodicalsId the all periodicals id
     */
    public void setAllPeriodicalsId(String allPeriodicalsId) {
        this.allPeriodicalsId = allPeriodicalsId;
    }

    /**
     * Gets status name.
     *
     * @return the status name
     */
    public String getStatusName() {
        return statusName;
    }

    /**
     * Sets status name.
     *
     * @param statusName the status name
     */
    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    /**
     * Gets publisher name.
     *
     * @return the publisher name
     */
    public String getPublisherName() {
        return publisherName;
    }

    /**
     * Sets publisher name.
     *
     * @param publisherName the publisher name
     */
    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
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
     * Gets address.
     *
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets address.
     *
     * @param address the address
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Instantiates a new Receipt.
     */
    public Receipt() {
    }

    /**
     * Gets create time.
     *
     * @return the create time
     */
    public Date getCreate_time() {
        return create_time;
    }

    /**
     * Sets create time.
     *
     * @param create_time the create time
     */
    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    /**
     * Gets email.
     *
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets email.
     *
     * @param email the email
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Gets name.
     *
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets name.
     *
     * @param name the name
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets surname.
     *
     * @return the surname
     */
    public String getSurname() {
        return surname;
    }

    /**
     * Sets surname.
     *
     * @param surname the surname
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * Gets adress.
     *
     * @return the adress
     */
    public String getAdress() {
        return address;
    }

    /**
     * Sets adress.
     *
     * @param adress the adress
     */
    public void setAdress(String adress) {
        this.address = adress;
    }

    /**
     * Gets telephone number.
     *
     * @return the telephone number
     */
    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    /**
     * Sets telephone number.
     *
     * @param telephoneNumber the telephone number
     */
    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    /**
     * Gets status id.
     *
     * @return the status id
     */
    public int getStatusId() {
        return statusId;
    }

    /**
     * Sets status id.
     *
     * @param statusId the status id
     */
    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    /**
     * Gets user id.
     *
     * @return the user id
     */
    public int getUserId() {
        return userId;
    }

    /**
     * Sets user id.
     *
     * @param userId the user id
     */
    public void setUserId(int userId) {
        this.userId = userId;
    }
}
