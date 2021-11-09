package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.ReceiptDao;
import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Receipt;

import java.sql.Connection;
import java.sql.SQLException;

public class ReceiptDaoImpl {
    private ReceiptDao receiptDao = new ReceiptDao();

    public boolean insertReceiptAfterPayment(Receipt receipt){
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return receiptDao.insertOrder(receipt, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }
}
