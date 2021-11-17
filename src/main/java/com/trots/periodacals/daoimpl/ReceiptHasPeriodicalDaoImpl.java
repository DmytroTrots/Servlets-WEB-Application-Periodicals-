package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;
import com.trots.periodacals.entity.Receipt;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReceiptHasPeriodicalDaoImpl {

    private static final Logger log = LogManager.getLogger(ReceiptHasPeriodicalDaoImpl.class);

    private static ReceiptHasPeriodicalDaoImpl instance;

    public static synchronized ReceiptHasPeriodicalDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReceiptHasPeriodicalDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public boolean insertReceiptAndPeriodical(Receipt receipt, Integer receiptID){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.insertRecordIntoReceiptHasPeriodicals(receipt, receiptID, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert receipt into DB");
        }
        return false;
    }
    public List<Receipt> getAllReceipts(){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.getAllOrdersForProccess(con);
        } catch (SQLException throwables) {
            log.error("Cannot select receipts for proccess");
        }
        return Collections.emptyList();
    }


}
