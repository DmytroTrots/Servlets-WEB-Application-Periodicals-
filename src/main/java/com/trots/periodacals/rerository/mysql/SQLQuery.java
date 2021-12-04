package com.trots.periodacals.rerository.mysql;

/**
 * The interface Sql query.
 */
public interface SQLQuery {
    String INSERT_USER_BY_ADMIN = "INSERT INTO `dbperiodicals`.`user` " + "(`username`, `email`, `password`, `role`, `telephone`, `name`, `surname`, `address`)" + "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
    String SELECT_ALL_USERS = "SELECT * FROM user";
    String SELECT_CREDENTIALS_LOGIN = "SELECT `username`, `password`, `role` FROM `user`";
    String SELECT_ALL_PERIODICALS = "SELECT `sell_id`, `title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, `description`, `rating`, `publisher`.`name`, `images` FROM periodical, publisher WHERE id = periodical.publisher_id";
    String GET_SINGLE_USER_BY_ID = "SELECT * FROM user WHERE (`id` = ?)";
    String UPDATE_BALANCE_AFTER_TOP_UP = "UPDATE `dbperiodicals`.`user` SET `balance` = ? WHERE (`id` = ?)";
    String GET_SINGLE_USER_BY_USERNAME = "SELECT * FROM user WHERE (`username` = ?)";
    String GET_SINGLE_USER_BY_TELEPHONE = "SELECT * FROM user WHERE (`telephone` = ?)";
    String GET_SINGLE_USER_BY_EMAIL = "SELECT * FROM user WHERE (`email` = ?)";
    String SET_BAN_STATUS = "UPDATE `dbperiodicals`.`user` " + "SET `ban_status` = ? WHERE (`id` = ?)";
    String DELETE_USER_FROM_ADMIN_PAGE = "DELETE FROM `dbperiodicals`.`user` WHERE (`id` = ?)";
    String GET_PERIODICALS_FOR_CART = "SELECT `sell_id`, `title`, `price_per_month`, `name` FROM periodical, publisher WHERE sell_id=? AND `publisher`.`id` = periodical.publisher_id";
    String GET_TOTAL_CART_PRICE = "SELECT price_per_month FROM periodical WHERE sell_id = ?";
    String GET_PRICE_OF_ONE_PERIODICAL_BY_ID = "SELECT price_per_month FROM periodical WHERE sell_id = ?";
    String INSERT_PERIODICAL = "INSERT INTO `dbperiodicals`.`periodical` " + "(`title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, " + "`description`, `rating`, `publisher_id`, `images`) " + "VALUES (?,?,?,?,?,?,?,?,?)";
    String DELETE_PERIODICAL_FROM_ADMIN_PAGE = "DELETE FROM `dbperiodicals`.`periodical` WHERE (`sell_id` = ?)";
    String GET_ONE_PERIODICAL_BY_ID = "SELECT `sell_id`, `title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, `description`, `rating`, `publisher`.`name`, `publisher`.`telephone_number`, `images` FROM `periodical` join `publisher` on `publisher_id` = `publisher`.`id` where `sell_id` = ?;";
    String GET_ONE_PERIODICAL_BY_TITLE = "SELECT * FROM periodical WHERE title = ?";
    String UPDATE_PERIODICAL_FROM_ADMIN_PAGE = "UPDATE `dbperiodicals`.`periodical` " + "SET `title` = ?, `number_of_pages` = ?, `periodicity_per_year` = ?, `percentage_of_advertising` = ?, " + "`price_per_month` = ?, `description` = ?, `rating` = ?, `publisher_id` = ?, `images` = ? " + "WHERE (`sell_id` = ?)";
    String GET_NUMBER_OF_ALL_PERIODICALS = "SELECT COUNT(sell_id) FROM periodical";
    String GET_ALL_PUBLISHERS = "SELECT * FROM publisher";
    String INSERT_PUBLISHER = "INSERT INTO `dbperiodicals`.`publisher` " + "(`name`, `telephone_number`) VALUES (?, ?)";
    String INSERT_ORDER_INTO_DB = "INSERT INTO `dbperiodicals`.`receipt` (`name`, " + "`surname`, `adress`, `telephone_number`, `status_id`, `user_id`, `email`)" + "VALUES (?, ?, ?, ?, ?, ?, ?)";
    String FIND_ORDERS_OF_ONE_USER = "select periodical_has_receipt.price_per_month, periodical_has_receipt.number_of_month, `status`.`status_name`, periodical.title, receipt.create_time, publisher.`name`\n" + "from ((((periodical_has_receipt\n" + "inner join receipt on  periodical_has_receipt.receipt_id = receipt.id)\n" + "inner join `periodical` on periodical_has_receipt.periodical_sell_id = periodical.sell_id)\n" + "inner join `status` on receipt.status_id = `status`.id)\n" + "inner join publisher on periodical.publisher_id = publisher.id)\n" + "where receipt.user_id = ? order by status_name asc";
    String FIND_ALL_ACCEPTED_ORDERS_FOR_ADMIN_REPORT = "select periodical_has_receipt.price_per_month, periodical_has_receipt.number_of_month, periodical.title, receipt.user_id\n" + "from periodical_has_receipt \n" + "inner join receipt on  periodical_has_receipt.receipt_id = receipt.id\n" + "inner join `periodical` on periodical_has_receipt.periodical_sell_id = periodical.sell_id\n" + "where cast(receipt.create_time as date) = ? and receipt.status_id = 3;";
    String INSERT_RECORD_INTO_RECEIPT_HAS_PERIODICALS_TABLE = "INSERT INTO `dbperiodicals`.`periodical_has_receipt` (`periodical_sell_id`, `receipt_id`, `price_per_month`, `number_of_month`) VALUES (?, ?, ?, ?)";
    String GET_ALL_ORDERS_FOR_ADMIN_PAGE = "select receipt.id, receipt.adress, receipt.`name`, receipt.surname, MAX(`status`.`status_name`) as s, " + "receipt.user_id, receipt.telephone_number, receipt.email, receipt.create_time, " + "sum(periodical_has_receipt.price_per_month) as sum, " + "group_concat(periodical_sell_id) as conc from periodical_has_receipt, receipt, status " + "where receipt.status_id = `status`.`id`  and receipt_id = receipt.id  group by receipt_id order by s DESC";
    String ACCEPT_ORDER_OF_USER_FROM_ADMIN_PAGE = "UPDATE `dbperiodicals`.`receipt` SET `status_id` = '3' WHERE (`id` = ?)";
    String DISCARD_ORDER_OF_USER_FROM_ADMIN_PAGE = "UPDATE `dbperiodicals`.`receipt` SET `status_id` = '2' WHERE (`id` = ?)";
    String GET_ALL_SUBJECTS = "SELECT * FROM subject";
    String INSERT_SUBJECT_INTO_DB = "INSERT INTO `dbperiodicals`.`subject` " + "(`themes_of_books`) VALUES (?)";
    String INSERT_RECORD_INTO_PERIODICAL_HAS_SUBJECT = "INSERT INTO periodical_has_subject " + "(periodical_id, subject_id) VALUES (?,?)";
    String GET_SUBJECT_OF_ONE_PERIODICAL_BY_ITS_ID = "Select `subject`.`themes_of_books` " + "from periodical_has_subject\n" + "Inner join `subject` on subject_id=`subject`.id\n" + "where periodical_has_subject.periodical_id = ?";

}
