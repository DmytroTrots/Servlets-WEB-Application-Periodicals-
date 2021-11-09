package com.trots.periodacals.dao;

import com.trots.periodacals.dbconnection.SQLQuery;
import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PeriodicalsDao {

    public List<Periodical> getAll(Connection con) throws SQLException {
        List<Periodical> list = new ArrayList<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_PERIODICALS)) {
            while (resultSet.next()) {
                Periodical periodical = new Periodical();
                periodical.setSellId(resultSet.getInt("sell_id"));
                periodical.setTitle(resultSet.getString("title"));
                periodical.setNumberOfPages(resultSet.getInt("number_of_pages"));
                periodical.setPeriodicityPerYear(resultSet.getInt("periodicity_per_year"));
                periodical.setPercentageOfAdvertising(resultSet.getInt("percentage_of_advertising"));
                periodical.setPricePerMonth(resultSet.getDouble("price_per_month"));
                periodical.setDescription(resultSet.getString("description"));
                periodical.setRating(resultSet.getDouble("rating"));
                periodical.setLanguage(resultSet.getString("language"));
                periodical.setPublisher(resultSet.getString("name"));
                periodical.setImage(resultSet.getString("images"));
                list.add(periodical);
            }
        }
        return list;
    }

    public List<Cart> getCartPeriodical(List<Cart> cartList, Connection con) throws SQLException {
        List<Cart> periodicalsListInCart = new ArrayList<>();
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT `sell_id`, `title`, `price_per_month`, `name` FROM periodical, publisher WHERE sell_id=? AND  `publisher`.`id` = periodical.publisher_id")) {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    preparedStatement.setInt(1, item.getSellId());
                    resultSet = preparedStatement.executeQuery();
                    while (resultSet.next()) {
                        Cart cart = new Cart();
                        cart.setSellId(resultSet.getInt("sell_id"));
                        cart.setTitle(resultSet.getString("title"));
                        cart.setPublisher(resultSet.getString("name"));
                        cart.setPricePerMonth(resultSet.getDouble("price_per_month") * item.getMonths());
                        cart.setMonths(item.getMonths());
                        periodicalsListInCart.add(cart);
                    }
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }
        return periodicalsListInCart;
    }

    public double getTotalCartPrice(ArrayList<Cart> cartList, Connection con) throws SQLException {
        double summa = 0;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT price_per_month FROM periodical WHERE sell_id = ?")) {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    preparedStatement.setInt(1, item.getSellId());
                    resultSet = preparedStatement.executeQuery();

                    while (resultSet.next()) {
                        summa += resultSet.getDouble("price_per_month") * item.getMonths();
                    }
                }
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return summa;
    }

    public double getPriceOfOnePeriodical(Integer id, Connection con) throws SQLException {
        double summa = 0;
        ResultSet resultSet = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT price_per_month FROM periodical WHERE sell_id = ?")) {
            preparedStatement.setInt(1, id);
            resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                summa += resultSet.getDouble("price_per_month");
            }
        } finally {
            if (resultSet != null) {
                resultSet.close();
            }
        }

        return summa;
    }
}
