package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

/**
 * The interface Receipt dao.
 */
public interface ReceiptDao {
    /**
     * Insert order integer.
     *
     * @param receipt the receipt
     * @param con     the con
     * @return the integer
     * @throws SQLException the sql exception
     */
    Integer insertOrder(Receipt receipt, Connection con) throws SQLException;

    /**
     * Find orders of one user list.
     *
     * @param id  the id
     * @param con the con
     * @return the list
     * @throws SQLException the sql exception
     */
    List<Receipt> findOrdersOfOneUser(Integer id, Connection con) throws SQLException;

    /**
     * Find all accepted orders of user list.
     *
     * @param connection the connection
     * @return the list
     * @throws SQLException the sql exception
     */
    List<Receipt> findAllAcceptedOrdersOfUser(Connection connection) throws SQLException;

    /**
     * Accept order of user by admin.
     *
     * @param receiptId  the receipt id
     * @param connection the connection
     * @throws SQLException the sql exception
     */
    void acceptOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException;

    /**
     * Discard order of user by admin.
     *
     * @param receiptId  the receipt id
     * @param connection the connection
     * @throws SQLException the sql exception
     */
    void discardOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException;

    /**
     * @param connection
     * @throws SQLException
     */
    void getAllOrdersForDelete(Connection connection) throws SQLException;

}
