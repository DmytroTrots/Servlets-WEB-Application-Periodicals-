package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.ReceiptHasPeriodicalDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReceiptHasPeriodicalService {
    private static final Logger log = LogManager.getLogger(ReceiptHasPeriodicalService.class);

    private static ReceiptHasPeriodicalService instance;

    public static synchronized ReceiptHasPeriodicalService getInstance() {
        if (instance == null) {
            instance = new ReceiptHasPeriodicalService();
        }
        return instance;
    }

    ReceiptHasPeriodicalDao repository = new MySQLDaoFactory().getReceiptHasPeriodicalDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public boolean insertReceiptAndPeriodical(Receipt receipt, Integer receiptID){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertRecordIntoReceiptHasPeriodicals(receipt, receiptID, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert receipt into DB");
        }
        return false;
    }
    public List<Receipt> getAllReceipts(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.getAllOrdersForProccess(con);
        } catch (SQLException throwables) {
            log.error("Cannot select receipts for proccess");
        }
        return Collections.emptyList();
    }
}
