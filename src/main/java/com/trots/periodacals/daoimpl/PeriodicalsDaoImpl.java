package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.PeriodicalsDao;
import com.trots.periodacals.dbconnection.ConnectionPool;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalsDaoImpl {

    private PeriodicalsDao periodicalsDao = new PeriodicalsDao();

    public List<Periodical> getAllPeriodicals() {
        List<Periodical> list = new ArrayList<>();
        try (Connection con = ConnectionPool.getInstance().getConnection()) {
            list = periodicalsDao.getAll(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return list;
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
}
