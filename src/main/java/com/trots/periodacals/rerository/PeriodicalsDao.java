package com.trots.periodacals.rerository;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public interface PeriodicalsDao {
    List<Periodical> getAll(Connection con) throws SQLException;

    List<Cart> getCartPeriodical(List<Cart> cartList, Connection con) throws SQLException;

    double getTotalCartPrice(List<Cart> cartList, Connection con) throws SQLException;

    double getPriceOfOnePeriodical(Integer id, Connection con) throws SQLException;

    Integer insertPeriodical(Periodical periodical, Connection connection) throws SQLException;

    boolean deletePeriodicalAdmin(Integer id, Connection con) throws SQLException;

    Periodical getOnePeriod(Integer id, Connection con) throws SQLException;

    boolean updatePeriodical(Periodical periodical, Integer id, String newImage, String oldImage, Connection connection) throws SQLException;

    List<Periodical> getRecords(String query, Connection connection) throws SQLException;

    int getNumberOfRows(Connection connection) throws SQLException;
}
