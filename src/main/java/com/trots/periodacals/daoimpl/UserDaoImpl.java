package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.UserDao;
import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.User;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class UserDaoImpl {

    private UserDao userDao = new UserDao();

    public String authenticateUser(User user) {
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            return userDao.loginCheck(user, con);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return "Invalid user credentials";
    }

    public boolean addUser(User user) {
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            return userDao.registrationByAdmin(user, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public boolean registerUser(User user) {
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            return userDao.registrationMethod(user, con);
        } catch (SQLException e) {
            Logger.getLogger(e.getMessage());
        }
        return false;
    }

    public List<User> findAllUsers() {
        List<User> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            list = userDao.findAll(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public boolean updateFieldBalanceAfterTopUp(int id, Double balance) {
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return userDao.updateBalanceTopUp(id, balance, con);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Double getBalanceOfUserBiId(Integer id){
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return userDao.findBalanceOfUserById(id, con);
        }catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public User getSingleUserById(Integer id){
        User user = null;
        try (Connection con = ConnectionPool.getInstance().getConnection()){
            user = userDao.findSingleUserById(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return user;
    }
}
