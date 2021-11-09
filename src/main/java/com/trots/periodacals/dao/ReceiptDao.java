package com.trots.periodacals.dao;

import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ReceiptDao {

    public boolean insertOrder(Receipt receipt, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`receipt` (`name`, " +
                "`surname`, `adress`, `telephone_number`, `status_id`, `user_id`, `price_per_month`, `number_of_moths`, `email`, `periodical_id`) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)")) {
            preparedStatement.setString(1, receipt.getName());
            preparedStatement.setString(2, receipt.getSurname());
            preparedStatement.setString(3, receipt.getAdress());
            preparedStatement.setString(4, receipt.getTelephoneNumber());
            preparedStatement.setInt(5, 2);
            preparedStatement.setInt(6, receipt.getUserId());
            preparedStatement.setDouble(7, receipt.getPrice());
            preparedStatement.setInt(8, receipt.getMonths());
            preparedStatement.setString(9,receipt.getEmail());
            preparedStatement.setInt(10, receipt.getPeriodicalId());
            preparedStatement.executeUpdate();
        }
        return true;
    }
}
