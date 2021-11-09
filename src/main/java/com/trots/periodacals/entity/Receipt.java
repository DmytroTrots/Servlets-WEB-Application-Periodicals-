package com.trots.periodacals.entity;

import java.sql.Date;

public class Receipt extends Periodical {
    private int id;
    private int months;
    private String name;
    private String surname;
    private String address;
    private String telephoneNumber;
    private int statusId;
    private int userId;
    private Double price;
    private String email;
    private Date create_time;
    private int periodicalId;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPeriodicalId() {
        return periodicalId;
    }

    public void setPeriodicalId(int periodicalId) {
        this.periodicalId = periodicalId;
    }

    public Receipt(int id, int months, String name, String surname, String address, String telephoneNumber, int statusId, int userId, Double price, String email) {
        this.id = id;
        this.months = months;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.statusId = statusId;
        this.userId = userId;
        this.price = price;
        this.email = email;
    }

    public Receipt(int months, String name, String surname, String address, String telephoneNumber, int statusId, int userId, Double price, String email) {
        this.months = months;
        this.name = name;
        this.surname = surname;
        this.address = address;
        this.telephoneNumber = telephoneNumber;
        this.statusId = statusId;
        this.userId = userId;
        this.price = price;
        this.email = email;
    }

    public Receipt() {
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public int getMonths() {
        return months;
    }

    public void setMonths(int months) {
        this.months = months;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getAdress() {
        return address;
    }

    public void setAdress(String adress) {
        this.address = adress;
    }

    public String getTelephoneNumber() {
        return telephoneNumber;
    }

    public void setTelephoneNumber(String telephoneNumber) {
        this.telephoneNumber = telephoneNumber;
    }

    public int getStatusId() {
        return statusId;
    }

    public void setStatusId(int statusId) {
        this.statusId = statusId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
