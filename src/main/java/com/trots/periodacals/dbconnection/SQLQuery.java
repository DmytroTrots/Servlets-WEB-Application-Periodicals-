package com.trots.periodacals.dbconnection;

public abstract class SQLQuery {
    public static final String INSERT_USER_BY_ADMIN = "INSERT INTO `dbperiodicals`.`user` " + "(`username`, `email`, `password`, `role`, `telephone`, `name`, `surname`)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String INSERT_USER_REGISTRATION = "INSERT INTO `dbperiodicals`.`user` " + "(`username`, `email`, `password`, `role`, `telephone`, `name`, `surname`) " + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_CREDENTIALS_LOGIN = "SELECT `username`, `password`, `role` FROM `user`";
}
