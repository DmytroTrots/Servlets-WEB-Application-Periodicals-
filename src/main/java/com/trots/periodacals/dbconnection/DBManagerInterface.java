package com.trots.periodacals.dbconnection;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.entity.User;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

public interface DBManagerInterface {
    ///USER'S DAO METHODS
    ///method for login
    String loginCheck(Connection connection, User user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException;

    ///method for finding all users of DataBase
    List<User> findAll(Connection con) throws SQLException;

    ///method for registering user by Admin(connect with previous method)
    Integer userRegistration(User user, Connection con) throws SQLException;

    ///method for balance updating
    boolean updateBalanceTopUp(int id, Double balance, Connection con) throws SQLException;

    ///method for finding one user by id
    User findSingleUserById(Integer id, Connection con) throws SQLException;

    ///method for ban user
    boolean setBanStatus(String status, Integer id, Connection con) throws SQLException;

    ///method for deleting user
    boolean deleteUserAdmin(Integer id, Connection con) throws SQLException;

    ///START OF PERIODICAL DAO
    List<Periodical> getAll(Connection con) throws SQLException;

    List<Cart> getCartPeriodical(List<Cart> cartList, Connection con) throws SQLException;

    double getTotalCartPrice(List<Cart> cartList, Connection con) throws SQLException;

    double getPriceOfOnePeriodical(Integer id, Connection con) throws SQLException;

    Integer insertPeriodical(Periodical periodical, Connection connection) throws SQLException;

    boolean deletePeriodicalAdmin(Integer id, Connection con) throws SQLException;

    Periodical getOnePeriod(Integer id, Connection con) throws SQLException;

    boolean updatePeriodical(Periodical periodical, Integer id, String newImage, String oldImage, Connection connection) throws SQLException;

    List<Periodical> getRecords(String query, Connection connection) throws SQLException;

    int getNumberOfRows(Connection connection) throws SQLException;

    ///START OF PUBLISHER DAO
    Map<String, Integer> findAllPublishers(Connection con) throws SQLException;

    Integer insertPublisher(String publisher, String telephone, Connection con) throws SQLException;

    ///START OF RECEIPT DAO
    Integer insertOrder(Receipt receipt, Connection con) throws SQLException;

    List<Receipt> findOrdersOfOneUser(Integer id, Connection con) throws SQLException;

    List<Receipt> findAllAcceptedOrdersOfUser(Connection connection) throws SQLException;

    boolean insertRecordIntoReceiptHasPeriodicals(Receipt receipt, Integer receiptId, Connection connection) throws SQLException;

    List<Receipt> getAllOrdersForProccess(Connection connection) throws SQLException;

    void acceptOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException;

    ///START OF SUBJECT DAO
    Map<String, Integer> findAllSubjects(Connection con) throws SQLException;

    Integer insertSubject(String subj, Connection con) throws SQLException;

    ///START OF PERIODICAL<->SUBJECT DAO
    boolean insertSubjIdAndPeriodicalId(Integer subjId, Integer periodId, Connection con) throws SQLException;

    List<String> getSubjectOfPeriodById(Integer id, Connection con) throws SQLException;
}
