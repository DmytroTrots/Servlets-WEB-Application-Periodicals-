package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.DBManager;
import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReceiptDaoImpl {

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
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Receipt> getAllOrdersOfUserById(Integer id){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.findOrdersOfOneUser(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }
}
