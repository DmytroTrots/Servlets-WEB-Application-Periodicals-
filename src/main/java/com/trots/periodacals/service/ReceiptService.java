package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.ReceiptDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The type Receipt service.
 */
public class ReceiptService {

    private static final Logger log = LogManager.getLogger(ReceiptService.class);

    private static ReceiptService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized ReceiptService getInstance() {
        if (instance == null) {
            instance = new ReceiptService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    ReceiptDao repository = new DaoImplFactory().getReceiptDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Insert receipt after payment integer.
     *
     * @param receipt the receipt
     * @return the integer
     */
    public Integer insertReceiptAfterPayment(Receipt receipt){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertOrder(receipt, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert receipt into DB");
        }
        return null;
    }

    /**
     * Get all orders of user by id list.
     *
     * @param id the id
     * @return the list
     */
    public List<Receipt> getAllOrdersOfUserById(Integer id){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findOrdersOfOneUser(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get all orders of user by ID");
        }
        return Collections.emptyList();
    }

    /**
     * Get all accepted order list.
     *
     * @return the list
     */
    public List<Receipt> getAllAcceptedOrder(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findAllAcceptedOrdersOfUser(con);
        } catch (SQLException throwables) {
            log.error("Cannot get all accepted orders for report");
        }
        return Collections.emptyList();
    }

    /**
     * Accept order by admin.
     *
     * @param receiptId the receipt id
     */
    public void acceptOrderByAdmin(Integer receiptId){
        try(Connection connection = connectionPool.getConnection()){
            repository.acceptOrderOfUserByAdmin(receiptId, connection);
        } catch (SQLException throwables) {
            log.error("Cannot accept order");
        }
    }

    /**
     * Discard order by admin.
     *
     * @param receiptId the receipt id
     */
    public void discardOrderByAdmin(Integer receiptId){
        try(Connection connection = connectionPool.getConnection()){
            repository.discardOrderOfUserByAdmin(receiptId, connection);
        } catch (SQLException throwables) {
            log.error("Cannot accept order");
        }
    }
}
