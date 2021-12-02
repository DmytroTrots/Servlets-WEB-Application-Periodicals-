package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface ReceiptHasPeriodicalDao {
    boolean insertRecordIntoReceiptHasPeriodicals(Receipt receipt, Integer receiptId, Connection connection) throws SQLException;

    List<Receipt> getAllOrdersForProccess(Connection connection) throws SQLException;
}
