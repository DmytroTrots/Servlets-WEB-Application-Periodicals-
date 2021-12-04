package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.ReceiptDao;

import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Receipt dao.
 */
public class ReceiptDaoImpl implements ReceiptDao, SQLQuery {
    @Override
    public Integer insertOrder(Receipt receipt, Connection con) throws SQLException {
        Integer receiptId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement(INSERT_ORDER_INTO_DB, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, receipt.getName());
            preparedStatement.setString(2, receipt.getSurname());
            preparedStatement.setString(3, receipt.getAdress());
            preparedStatement.setString(4, receipt.getTelephoneNumber());
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, receipt.getUserId());
            preparedStatement.setString(7, receipt.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    receiptId = resultSet.getInt(1);
                }

            }
        }
        return receiptId;
    }

    @Override
    public List<Receipt> findOrdersOfOneUser(Integer id, Connection con) throws SQLException {
        List<Receipt> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(FIND_ORDERS_OF_ONE_USER)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Receipt receipt = new Receipt();
                    receipt.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    receipt.setMonths(resultSet.getInt("number_of_month"));
                    receipt.setTitle(resultSet.getString("title"));
                    receipt.setStatusName(resultSet.getString("status_name"));
                    receipt.setPublisherName(resultSet.getString("name"));
                    receipt.setCreate_time(resultSet.getDate("create_time"));
                    list.add(receipt);
                }
            }
        }
        return list;
    }

    @Override
    public List<Receipt> findAllAcceptedOrdersOfUser(Connection connection) throws SQLException {
        List<Receipt> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ALL_ACCEPTED_ORDERS_FOR_ADMIN_REPORT)) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setDate(1, Date.valueOf(dtf.format(now)));
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Receipt receipt = new Receipt();
                    receipt.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    receipt.setMonths(resultSet.getInt("number_of_month"));
                    receipt.setTitle(resultSet.getString("title"));
                    receipt.setUserId(resultSet.getInt("user_id"));
                    list.add(receipt);
                }
            }
        }
        return list;
    }

    @Override
    public boolean acceptOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(ACCEPT_ORDER_OF_USER_FROM_ADMIN_PAGE)) {
            preparedStatement.setInt(1, receiptId);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean discardOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(DISCARD_ORDER_OF_USER_FROM_ADMIN_PAGE)) {
            preparedStatement.setInt(1, receiptId);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public boolean getAllOrdersForDelete(Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(FIND_ORDERS_OF_ALL_USERS_FOR_DELETE)) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                java.util.Date date = new java.util.Date();
                long currentDate =date.getTime();
                while (resultSet.next()) {
                    Date dateDB = resultSet.getDate("create_time");
                    int periodicity = resultSet.getInt("periodicity_per_year");
                    int months = resultSet.getInt("number_of_month");
                    long endDate = dateDB.getTime()+(((long) (12 / periodicity) *months)*30L*24L*60L*60L*1000L);
                    if (endDate<currentDate){
                        try(PreparedStatement preparedStatement1 = connection.prepareStatement(DELETE_ORDER_AFTER_TIME)){
                            preparedStatement1.setInt(1, resultSet.getInt("periodical_sell_id"));
                            preparedStatement1.setInt(2, resultSet.getInt("receipt_id"));
                            preparedStatement1.executeUpdate();
                        }
                    }
                }
            }
        }
        return true;
    }
}
