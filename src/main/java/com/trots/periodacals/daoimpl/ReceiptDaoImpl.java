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

public class ReceiptDaoImpl {

    private static final Logger log = LogManager.getLogger(ReceiptDaoImpl.class);

    private static ReceiptDaoImpl instance;

    public static synchronized ReceiptDaoImpl getInstance() {
        if (instance == null) {
            instance = new ReceiptDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public boolean insertReceiptAfterPayment(Receipt receipt){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.insertOrder(receipt, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert receipt into DB");
        }
        return false;
    }

    public List<Receipt> getAllOrdersOfUserById(Integer id){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.findOrdersOfOneUser(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get all orders of user by ID");
        }
        return Collections.emptyList();
    }
}
