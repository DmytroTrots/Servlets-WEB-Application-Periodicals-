package com.trots.periodacals.dbconnection;

public class SQLQuery {
    public static final String INSERT_USER_BY_ADMIN = "INSERT INTO `dbperiodicals`.`user` " + "(`username`, `email`, `password`, `role`, `telephone`, `name`, `surname`, `address`)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    public static final String SELECT_ALL_USERS = "SELECT * FROM user";
    public static final String SELECT_CREDENTIALS_LOGIN = "SELECT `username`, `password`, `role` FROM `user`";
    public static final String SELECT_ALL_PERIODICALS = "SELECT `sell_id`, `title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, `description`, `rating`, `publisher`.`name`, `images` FROM periodical, publisher WHERE id = periodical.publisher_id";

}
