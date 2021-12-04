package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.rerository.PeriodicalsDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The type Periodical service.
 */
public class PeriodicalService {
    private static final Logger log = LogManager.getLogger(PeriodicalService.class);

    private static PeriodicalService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized PeriodicalService getInstance() {
        if (instance == null) {
            instance = new PeriodicalService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    PeriodicalsDao repository = new DaoImplFactory().getPeriodicalDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Gets all periodicals.
     *
     * @return the all periodicals
     */
    public List<Periodical> getAllPeriodicals() {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getAll(con);
        } catch (SQLException e) {
            log.error("Cannot get all periodicals");
        }
        return Collections.emptyList();
    }

    /**
     * Gets all cart periodical.
     *
     * @param list the list
     * @return the all cart periodical
     */
    public List<Cart> getAllCartPeriodical(List<Cart> list) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getCartPeriodical(list, con);
        } catch (SQLException e) {
            log.error("Cannot get cart's periodicals");
        }
        return Collections.emptyList();
    }

    /**
     * Gets total price of cart.
     *
     * @param list the list
     * @return the total price of cart
     */
    public double getTotalPriceOfCart(List<Cart> list) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getTotalCartPrice(list, con);
        } catch (SQLException e) {
            log.error("Cannot get total price of cart");
        }
        return 0;
    }

    /**
     * Gets price by id.
     *
     * @param id the id
     * @return the price by id
     */
    public double getPriceById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getPriceOfOnePeriodical(id, con);
        } catch (SQLException e) {
            log.error("Cannot get price of periodical by id");
        }
        return 0;
    }

    /**
     * Insert periodical into db integer.
     *
     * @param periodical the periodical
     * @return the integer
     */
    public Integer insertPeriodicalIntoDB(Periodical periodical) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.insertPeriodical(periodical, con);
        } catch (SQLException e) {
            log.error("Cannot insert periodical into DB, admin page");
        }
        return null;
    }

    /**
     * Delete periodical from admin page boolean.
     *
     * @param id the id
     * @return the boolean
     */
    public boolean deletePeriodicalFromAdminPage(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.deletePeriodicalAdmin(id, con);
        } catch (SQLException e) {
            log.error("Cannot delete periodical, admin page");
        }
        return false;
    }

    /**
     * Gets periodical by id.
     *
     * @param id the id
     * @return the periodical by id
     */
    public Periodical getPeriodicalById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getOnePeriod(id, con);
        } catch (SQLException e) {
            log.error("Cannot get periodical by sell_id");
        }
        return null;
    }

    /**
     * Update periodical admin boolean.
     *
     * @param periodical the periodical
     * @param newImage   the new image
     * @param oldImage   the old image
     * @param id         the id
     * @return the boolean
     */
    public boolean updatePeriodicalAdmin(Periodical periodical, String newImage, String oldImage, Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.updatePeriodical(periodical, id, newImage, oldImage, con);
        } catch (SQLException e) {
            log.error("Cannot change info about periodical, admin page");
        }
        return false;
    }

    /**
     * Gets records for pagination.
     *
     * @param query the query
     * @return the records for pagination
     */
    public List<Periodical> getRecordsForPagination(String query) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getRecords(query, con);
        } catch (SQLException e) {
            log.error("Cannot get limited periodicals for pagination");
        }
        return Collections.emptyList();
    }

    /**
     * Gets numbers of rows.
     *
     * @return the numbers of rows
     */
    public int getNumbersOfRows() {
        try (Connection connection = connectionPool.getConnection()) {
            return repository.getNumberOfRows(connection);
        } catch (SQLException e) {
            log.error("Cannot get numbers of records in table periodical");
        }
        return 0;
    }

    /**
     * @param title
     * @return
     */
    public Periodical getPeriodicalByTitle(String title) {
        try (Connection con = connectionPool.getConnection()) {
            return repository.getPeriodicalByTitle(title, con);
        } catch (SQLException e) {
            log.error("Cannot get periodical by title");
        }
        return null;
    }
}
