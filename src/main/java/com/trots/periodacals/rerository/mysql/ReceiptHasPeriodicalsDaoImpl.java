package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.ReceiptHasPeriodicalDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Receipt has periodicals dao.
 */
public class ReceiptHasPeriodicalsDaoImpl implements ReceiptHasPeriodicalDao, SQLQuery {
    @Override
    public boolean insertRecordIntoReceiptHasPeriodicals(Receipt receipt, Integer receiptId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_RECORD_INTO_RECEIPT_HAS_PERIODICALS_TABLE)) {
            preparedStatement.setInt(1, receipt.getPeriodicalSellId());
            preparedStatement.setInt(2, receiptId);
            preparedStatement.setDouble(3, receipt.getPricePerMonth());
            preparedStatement.setInt(4, receipt.getMonths());
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public List<Receipt> getAllOrdersForProccess(Connection connection) throws SQLException {
        List<Receipt> receiptList = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(GET_ALL_ORDERS_FOR_ADMIN_PAGE)) {
            while (resultSet.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(resultSet.getInt("id"));
                receipt.setAdress(resultSet.getString("adress"));
                receipt.setName(resultSet.getString("name"));
                receipt.setSurname(resultSet.getString("surname"));
                receipt.setStatusName(resultSet.getString("s"));
                receipt.setUserId(resultSet.getInt("user_id"));
                receipt.setTelephoneNumber(resultSet.getString("telephone_number"));
                receipt.setEmail(resultSet.getString("email"));
                receipt.setCreate_time(resultSet.getDate("create_time"));
                receipt.setPricePerMonth(resultSet.getDouble("sum"));
                receipt.setAllPeriodicalsId(resultSet.getString("conc"));
                receiptList.add(receipt);
            }
        }
        return receiptList;
    }
}
