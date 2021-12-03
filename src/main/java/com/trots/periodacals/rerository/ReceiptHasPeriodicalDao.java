package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Receipt has periodical dao.
 */
public interface ReceiptHasPeriodicalDao {
    /**
     * Insert record into receipt has periodicals boolean.
     *
     * @param receipt    the receipt
     * @param receiptId  the receipt id
     * @param connection the connection
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean insertRecordIntoReceiptHasPeriodicals(Receipt receipt, Integer receiptId, Connection connection) throws SQLException;

    /**
     * Gets all orders for proccess.
     *
     * @param connection the connection
     * @return the all orders for proccess
     * @throws SQLException the sql exception
     */
    List<Receipt> getAllOrdersForProccess(Connection connection) throws SQLException;
}
