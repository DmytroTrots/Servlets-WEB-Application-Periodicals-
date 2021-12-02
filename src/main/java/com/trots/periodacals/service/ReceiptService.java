package com.trots.periodacals.service;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.rerository.ReceiptDao;
import com.trots.periodacals.rerository.UserDao;
import com.trots.periodacals.rerository.mysql.MySQLDaoFactory;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.List;

public class ReceiptService {

    private static final Logger log = LogManager.getLogger(ReceiptService.class);

    private static ReceiptService instance;

    public static synchronized ReceiptService getInstance() {
        if (instance == null) {
            instance = new ReceiptService();
        }
        return instance;
    }

    ReceiptDao repository = new MySQLDaoFactory().getReceiptDao();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public Integer insertReceiptAfterPayment(Receipt receipt){
        try(Connection con = connectionPool.getConnection()) {
            return repository.insertOrder(receipt, con);
        } catch (SQLException throwables) {
            log.error("Cannot insert receipt into DB");
        }
        return null;
    }

    public List<Receipt> getAllOrdersOfUserById(Integer id){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findOrdersOfOneUser(id, con);
        } catch (SQLException throwables) {
            log.error("Cannot get all orders of user by ID");
        }
        return Collections.emptyList();
    }

    public List<Receipt> getAllAcceptedOrder(){
        try(Connection con = connectionPool.getConnection()) {
            return repository.findAllAcceptedOrdersOfUser(con);
        } catch (SQLException throwables) {
            log.error("Cannot get all accepted orders for report");
        }
        return Collections.emptyList();
    }

    public void acceptOrderByAdmin(Integer receiptId){
        try(Connection connection = connectionPool.getConnection()){
            repository.acceptOrderOfUserByAdmin(receiptId, connection);
        } catch (SQLException throwables) {
            log.error("Cannot accept order");
        }
    }

    public void discardOrderByAdmin(Integer receiptId){
        try(Connection connection = connectionPool.getConnection()){
            repository.discardOrderOfUserByAdmin(receiptId, connection);
        } catch (SQLException throwables) {
            log.error("Cannot accept order");
        }
    }
}
