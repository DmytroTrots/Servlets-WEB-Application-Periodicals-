package com.trots.periodacals.dao;

import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

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

    public List<Receipt> findOrdersOfOneUser(Integer id, Connection con) throws SQLException {
        ResultSet resultSet = null;
        List<Receipt> list = new ArrayList<>();
        try(PreparedStatement preparedStatement = con.prepareStatement("select receipt.price_per_month, " +
                "receipt.number_of_moths, `status`.`name`, periodical.title, receipt.create_time\n" +
                "from ((`receipt` inner join `status` on  receipt.`status_id` = `status`.`id`)\n" + "" +
                "inner join `periodical` on receipt.`periodical_id` = `periodical`.`sell_id`)\n" +
                "where receipt.user_id = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()){
                Receipt receipt = new Receipt();
                receipt.setPrice(resultSet.getDouble("price_per_month"));
                receipt.setMonths(resultSet.getInt("number_of_moths"));
                receipt.setTitle(resultSet.getString("title"));
                receipt.setDescription(resultSet.getString("name"));
                receipt.setCreate_time(resultSet.getDate("create_time"));
                list.add(receipt);
            }

        }
        return list;
    }
}
