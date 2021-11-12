package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.PeriodicalsDao;
import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeriodicalsDaoImpl {

    private PeriodicalsDao periodicalsDao = new PeriodicalsDao();

    public List<Periodical> getAllPeriodicals() {
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            return periodicalsDao.getAll(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Cart> getAllCartPeriodical(List<Cart> list){
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            list = periodicalsDao.getCartPeriodical(list, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
    }

    public double getTotalPriceOfCart(ArrayList<Cart> list){
        double summa = 0;
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            summa = periodicalsDao.getTotalCartPrice(list, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return summa;
    }

    public double getPriceById(Integer id){
        double summa = 0;
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            summa = periodicalsDao.getPriceOfOnePeriodical(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return summa;
    }

    public Integer insertPeriodicalIntoDB(Periodical periodical){
        try (Connection connection = ConnectionPool.getInstance().getConnection()){
            return periodicalsDao.insertPeriodical(periodical, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean deletePeriodicalFromAdminPage(Integer id){
        try(Connection con = ConnectionPool.getInstance().getConnection()){
            return periodicalsDao.deletePeriodicalAdmin(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Periodical getPeriodicalById(Integer id) {
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            return periodicalsDao.getOnePeriod(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updatePeriodicalAdmin(Periodical periodical, String newImage, String oldImage, Integer id){
        try(Connection connection = ConnectionPool.getInstance().getConnection()) {
            return periodicalsDao.updatePeriodical(periodical, id, newImage, oldImage, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Periodical> getRecordsForPagination(){
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            return periodicalsDao.getRecords(connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Periodical> getRecordsForPaginationBySubject(int subject){
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            return periodicalsDao.getRecordsWithSubject(subject, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Periodical getRecordPeriodicalByName(String title){
        try(Connection connection = ConnectionPool.getInstance().getConnection()){
            return periodicalsDao.getPeriodicalByName(title, connection);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
