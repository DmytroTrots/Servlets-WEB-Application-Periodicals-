package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.dbconnection.DBManager;
import com.trots.periodacals.entity.Periodical;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PeriodicalsDaoImpl {

    private static final Logger log = LogManager.getLogger(PeriodicalsDaoImpl.class);

    private static PeriodicalsDaoImpl instance;

    public static synchronized PeriodicalsDaoImpl getInstance() {
        if (instance == null) {
            instance = new PeriodicalsDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Periodical> getAllPeriodicals() {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getAll(con);
        } catch (SQLException e) {
            log.error("Cannot get all periodicals");
        }
        return Collections.emptyList();
    }

    public List<Cart> getAllCartPeriodical(List<Cart> list) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getCartPeriodical(list, con);
        } catch (SQLException e) {
            log.error("Cannot get cart's periodicals");
        }
        return Collections.emptyList();
    }

    public double getTotalPriceOfCart(List<Cart> list) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getTotalCartPrice(list, con);
        } catch (SQLException e) {
            log.error("Cannot get total price of cart");
        }
        return 0;
    }

    public double getPriceById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getPriceOfOnePeriodical(id, con);
        } catch (SQLException e) {
            log.error("Cannot get price of periodical by id");
        }
        return 0;
    }

    public Integer insertPeriodicalIntoDB(Periodical periodical) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.insertPeriodical(periodical, con);
        } catch (SQLException e) {
            log.error("Cannot insert periodical into DB, admin page");
        }
        return null;
    }

    public boolean deletePeriodicalFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.deletePeriodicalAdmin(id, con);
        } catch (SQLException e) {
            log.error("Cannot delete periodical, admin page");
        }
        return false;
    }

    public Periodical getPeriodicalById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getOnePeriod(id, con);
        } catch (SQLException e) {
            log.error("Cannot get periodical by sell_id");
        }
        return null;
    }

    public boolean updatePeriodicalAdmin(Periodical periodical, String newImage, String oldImage, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.updatePeriodical(periodical, id, newImage, oldImage, con);
        } catch (SQLException e) {
            log.error("Cannot change info about periodical, admin page");
        }
        return false;
    }

    public List<Periodical> getRecordsForPagination(String query) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getRecords(query, con);
        } catch (SQLException e) {
            log.error("Cannot get limited periodicals for pagination");
        }
        return Collections.emptyList();
    }

    public int getNumbersOfRows() {
        try (Connection connection = connectionPool.getConnection()) {
            return dbManager.getNumberOfRows(connection);
        } catch (SQLException e) {
            log.error("Cannot get numbers of records in table periodical");
        }
        return 0;
    }

}
