package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.DBManager;
import com.trots.periodacals.entity.Periodical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class PeriodicalsDaoImpl {

    private static PeriodicalsDaoImpl instance;

    public static synchronized PeriodicalsDaoImpl getInstance() {
        if (instance == null) {
            instance = new PeriodicalsDaoImpl();
        }
        return instance;
    }

    private DBManager dbManager = DBManager.getInstance();

    private ConnectionPool connectionPool = ConnectionPool.getInstance();

    public List<Periodical> getAllPeriodicals() {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getAll(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Cart> getAllCartPeriodical(List<Cart> list){
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getCartPeriodical(list, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public double getTotalPriceOfCart(ArrayList<Cart> list){
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getTotalCartPrice(list, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public double getPriceById(Integer id){
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getPriceOfOnePeriodical(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return 0;
    }

    public Integer insertPeriodicalIntoDB(Periodical periodical){
        try (Connection con = connectionPool.getConnection()){
            return dbManager.insertPeriodical(periodical, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean deletePeriodicalFromAdminPage(Integer id){
        try(Connection con = connectionPool.getConnection()){
            return dbManager.deletePeriodicalAdmin(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public Periodical getPeriodicalById(Integer id) {
        try (Connection con = connectionPool.getConnection()) {
            return dbManager.getOnePeriod(id, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    public boolean updatePeriodicalAdmin(Periodical periodical, String newImage, String oldImage, Integer id){
        try(Connection con = connectionPool.getConnection()) {
            return dbManager.updatePeriodical(periodical, id, newImage, oldImage, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return false;
    }

    public List<Periodical> getRecordsForPagination(){
        try(Connection con = connectionPool.getConnection()){
            return dbManager.getRecords(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public List<Periodical> getRecordsForPaginationBySubject(int subject){
        try(Connection con = connectionPool.getConnection()){
            return dbManager.getRecordsWithSubject(subject, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyList();
    }

    public Periodical getRecordPeriodicalByName(String title){
        try(Connection con = connectionPool.getConnection()){
            return dbManager.getPeriodicalByName(title, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
