package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.ReceiptHasPeriodicalDao;
import com.trots.periodacals.rerository.mysql.DaoImplFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

/**
 * The type Receipt has periodical service.
 */
public class ReceiptHasPeriodicalService {
    private static final Logger log = LogManager.getLogger(ReceiptHasPeriodicalService.class);

    private static ReceiptHasPeriodicalService instance;

    /**
     * Gets instance.
     *
     * @return the instance
     */
    public static synchronized ReceiptHasPeriodicalService getInstance() {
        if (instance == null) {
            instance = new ReceiptHasPeriodicalService();
        }
        return instance;
    }

    /**
     * The Repository.
     */
    ReceiptHasPeriodicalDao repository = new DaoImplFactory().getReceiptHasPeriodicalDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    /**
     * Insert receipt and periodical boolean.
     *
     * @param receipt   the receipt
     * @param receiptID the receipt id
     * @return the boolean
     */
    public boolean insertReceiptAndPeriodical(Receipt receipt, Integer receiptID){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertRecordIntoReceiptHasPeriodicals(receipt, receiptID, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert receipt into DB");
        }
        return false;
    }

    /**
     * Get all receipts list.
     *
     * @return the list
     */
    public List<Receipt> getAllReceipts(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.getAllOrdersForProccess(con);
        } catch (SQLException throwables) {
            log.error("Cannot select receipts for proccess");
        }
        return Collections.emptyList();
    }
}
