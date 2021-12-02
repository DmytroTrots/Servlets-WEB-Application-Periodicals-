package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReceiptDao {
    Integer insertOrder(Receipt receipt, Connection con) throws SQLException;

    List<Receipt> findOrdersOfOneUser(Integer id, Connection con) throws SQLException;

    List<Receipt> findAllAcceptedOrdersOfUser(Connection connection) throws SQLException;

    void acceptOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException;

    void discardOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException;

}
