package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;

import java.sql.Connection;
import java.sql.SQLException;
import java.time.Period;
import java.util.List;

/**
 * The interface Periodicals dao.
 */
public interface PeriodicalsDao {
    /**
     * Gets all.
     *
     * @param con the con
     * @return the all
     * @throws SQLException the sql exception
     */
    List<Periodical> getAll(Connection con) throws SQLException;

    /**
     * Gets cart periodical.
     *
     * @param cartList the cart list
     * @param con      the con
     * @return the cart periodical
     * @throws SQLException the sql exception
     */
    List<Cart> getCartPeriodical(List<Cart> cartList, Connection con) throws SQLException;

    /**
     * Gets total cart price.
     *
     * @param cartList the cart list
     * @param con      the con
     * @return the total cart price
     * @throws SQLException the sql exception
     */
    double getTotalCartPrice(List<Cart> cartList, Connection con) throws SQLException;

    /**
     * Gets price of one periodical.
     *
     * @param id  the id
     * @param con the con
     * @return the price of one periodical
     * @throws SQLException the sql exception
     */
    double getPriceOfOnePeriodical(Integer id, Connection con) throws SQLException;

    /**
     * Insert periodical integer.
     *
     * @param periodical the periodical
     * @param connection the connection
     * @return the integer
     * @throws SQLException the sql exception
     */
    Integer insertPeriodical(Periodical periodical, Connection connection) throws SQLException;

    /**
     * Delete periodical admin boolean.
     *
     * @param id  the id
     * @param con the con
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean deletePeriodicalAdmin(Integer id, Connection con) throws SQLException;

    /**
     * Gets one period.
     *
     * @param id  the id
     * @param con the con
     * @return the one period
     * @throws SQLException the sql exception
     */
    Periodical getOnePeriod(Integer id, Connection con) throws SQLException;

    /**
     * Update periodical boolean.
     *
     * @param periodical the periodical
     * @param id         the id
     * @param newImage   the new image
     * @param oldImage   the old image
     * @param connection the connection
     * @return the boolean
     * @throws SQLException the sql exception
     */
    boolean updatePeriodical(Periodical periodical, Integer id, String newImage, String oldImage, Connection connection) throws SQLException;

    /**
     * Gets records.
     *
     * @param query      the query
     * @param connection the connection
     * @return the records
     * @throws SQLException the sql exception
     */
    List<Periodical> getRecords(String query, Connection connection) throws SQLException;

    /**
     * Gets number of rows.
     *
     * @param connection the connection
     * @return the number of rows
     * @throws SQLException the sql exception
     */
    int getNumberOfRows(Connection connection) throws SQLException;

    /**
     * @param title
     * @param connection
     * @return
     * @throws SQLException
     */
    Periodical getPeriodicalByTitle(String title, Connection connection) throws SQLException;
}
