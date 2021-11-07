package com.trots.periodacals.entity;

import java.math.BigDecimal;
import java.sql.Date;

public class User {
    private int id;
    private String username;
    private String email;
    private String password;
    private String telephone;
    private Date create_time;
    private String role;
    private String name;
    private String surname;
    private String banStatus;
    private BigDecimal balance;

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getTelephone() {
        return telephone;
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
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

    public String getBanStatus() {
        return banStatus;
    }

    public void setBanStatus(String banStatus) {
        this.banStatus = banStatus;
    }

    public User(int id, String username, String email, String password, String telephone, Date create_time, String role, String name, String surname, String banStatus, BigDecimal balance) {
        this.id = id;
        this.username = username;
        this.email = email;
        this.password = password;
        this.telephone = telephone;
        this.create_time = create_time;
        this.role = role;
        this.name = name;
        this.surname = surname;
        this.banStatus = banStatus;
        this.balance = balance;
    }

    public User() {
    }
}
