package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.rerository.PeriodicalsDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class PeriodicalService {
    private static final Logger log = LogManager.getLogger(PeriodicalService.class);

    private static PeriodicalService instance;

    public static synchronized PeriodicalService getInstance() {
        if (instance == null) {
            instance = new PeriodicalService();
        }
        return instance;
    }

    PeriodicalsDao repository = new MySQLDaoFactory().getPeriodicalDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Periodical> getAllPeriodicals() {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getAll(con);
        } catch (SQLException e) {
            log.error("Cannot get all periodicals");
        }
        return Collections.emptyList();
    }

    public List<Cart> getAllCartPeriodical(List<Cart> list) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getCartPeriodical(list, con);
        } catch (SQLException e) {
            log.error("Cannot get cart's periodicals");
        }
        return Collections.emptyList();
    }

    public double getTotalPriceOfCart(List<Cart> list) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getTotalCartPrice(list, con);
        } catch (SQLException e) {
            log.error("Cannot get total price of cart");
        }
        return 0;
    }

    public double getPriceById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getPriceOfOnePeriodical(id, con);
        } catch (SQLException e) {
            log.error("Cannot get price of periodical by id");
        }
        return 0;
    }

    public Integer insertPeriodicalIntoDB(Periodical periodical) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.insertPeriodical(periodical, con);
        } catch (SQLException e) {
            log.error("Cannot insert periodical into DB, admin page");
        }
        return null;
    }

    public boolean deletePeriodicalFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.deletePeriodicalAdmin(id, con);
        } catch (SQLException e) {
            log.error("Cannot delete periodical, admin page");
        }
        return false;
    }

    public Periodical getPeriodicalById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getOnePeriod(id, con);
        } catch (SQLException e) {
            log.error("Cannot get periodical by sell_id");
        }
        return null;
    }

    public boolean updatePeriodicalAdmin(Periodical periodical, String newImage, String oldImage, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.updatePeriodical(periodical, id, newImage, oldImage, con);
        } catch (SQLException e) {
            log.error("Cannot change info about periodical, admin page");
        }
        return false;
    }

    public List<Periodical> getRecordsForPagination(String query) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getRecords(query, con);
        } catch (SQLException e) {
            log.error("Cannot get limited periodicals for pagination");
        }
        return Collections.emptyList();
    }

    public int getNumbersOfRows() {
        try (Connection connection = connectionPool.getConnection()) {
            return repository.getNumberOfRows(connection);
        } catch (SQLException e) {
            log.error("Cannot get numbers of records in table periodical");
        }
        return 0;
    }
}
