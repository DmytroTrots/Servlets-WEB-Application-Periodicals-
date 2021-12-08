package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.rerository.PeriodicalsDao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * The type Periodicals dao.
 */
public class PeriodicalsDaoImpl implements PeriodicalsDao, SQLQuery {

    @Override
    public List<Periodical> getAll(Connection con) throws SQLException {
        List<Periodical> list = new ArrayList<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(SELECT_ALL_PERIODICALS)) {
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
                periodical.setPublisher(resultSet.getString("name"));
                periodical.setImage(resultSet.getString("images"));
                list.add(periodical);
            }
        }
        return list;
    }

    @Override
    public List<Cart> getCartPeriodical(List<Cart> cartList, Connection con) throws SQLException {
        List<Cart> periodicalsListInCart = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_PERIODICALS_FOR_CART)) {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    preparedStatement.setInt(1, item.getSellId());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
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
            }
        }
        return periodicalsListInCart;
    }

    @Override
    public double getTotalCartPrice(List<Cart> cartList, Connection con) throws SQLException {
        double summa = 0;
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_TOTAL_CART_PRICE)) {
            if (cartList.size() > 0) {
                for (Cart item : cartList) {
                    preparedStatement.setInt(1, item.getSellId());
                    try (ResultSet resultSet = preparedStatement.executeQuery()) {
                        while (resultSet.next()) {
                            summa += resultSet.getDouble("price_per_month") * item.getMonths();
                        }
                    }
                }
            }
        }
        return summa;
    }

    @Override
    public double getPriceOfOnePeriodical(Integer id, Connection con) throws SQLException {
        double summa = 0;
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_PRICE_OF_ONE_PERIODICAL_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    summa += resultSet.getDouble("price_per_month");
                }
            }
        }
        return summa;
    }

    @Override
    public Integer insertPeriodical(Periodical periodical, Connection connection) throws SQLException {
        Integer periodicalId = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(INSERT_PERIODICAL, Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, periodical.getTitle());
            preparedStatement.setInt(2, periodical.getNumberOfPages());
            preparedStatement.setInt(3, periodical.getPeriodicityPerYear());
            preparedStatement.setInt(4, periodical.getPercentageOfAdvertising());
            preparedStatement.setDouble(5, periodical.getPricePerMonth());
            preparedStatement.setString(6, periodical.getDescription());
            preparedStatement.setDouble(7, periodical.getRating());
            preparedStatement.setInt(8, periodical.getPublisherId());
            preparedStatement.setString(9, periodical.getImage());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    periodicalId = resultSet.getInt(1);
                }
            }
        }
        return periodicalId;
    }

    @Override
    public boolean deletePeriodicalAdmin(Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(DELETE_PERIODICAL_FROM_ADMIN_PAGE)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public Periodical getOnePeriod(Integer id, Connection con) throws SQLException {
        Periodical periodical = new Periodical();
        try (PreparedStatement preparedStatement = con.prepareStatement(GET_ONE_PERIODICAL_BY_ID)) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    periodical.setTitle(resultSet.getString("title"));
                    periodical.setNumberOfPages(resultSet.getInt("number_of_pages"));
                    periodical.setPeriodicityPerYear(resultSet.getInt("periodicity_per_year"));
                    periodical.setPercentageOfAdvertising(resultSet.getInt("percentage_of_advertising"));
                    periodical.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    periodical.setDescription(resultSet.getString("description"));
                    periodical.setRating(resultSet.getDouble("rating"));
                    periodical.setTelephonePub(resultSet.getString("telephone_number"));
                    periodical.setPublisher(resultSet.getString("name"));
                    periodical.setImage(resultSet.getString("images"));
                }
            }
        }
        return periodical;
    }

    @Override
    public boolean updatePeriodical(Periodical periodical, Integer id, String newImage, String oldImage, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement(UPDATE_PERIODICAL_FROM_ADMIN_PAGE)) {
            preparedStatement.setString(1, periodical.getTitle());
            preparedStatement.setInt(2, periodical.getNumberOfPages());
            preparedStatement.setInt(3, periodical.getPeriodicityPerYear());
            preparedStatement.setInt(4, periodical.getPercentageOfAdvertising());
            preparedStatement.setDouble(5, periodical.getPricePerMonth());
            preparedStatement.setString(6, periodical.getDescription());
            preparedStatement.setDouble(7, periodical.getRating());
            preparedStatement.setInt(8, periodical.getPublisherId());
            if (!newImage.equals(oldImage) && !newImage.equals("")) {
                preparedStatement.setString(9, newImage);
            } else {
                preparedStatement.setString(9, oldImage);
            }
            preparedStatement.setInt(10, id);

            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public List<Periodical> getRecords(String query, Connection connection) throws SQLException {
        List<Periodical> list = new ArrayList<>();
        try (PreparedStatement statement = connection.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {
            while (resultSet.next()) {
                Periodical periodical = new Periodical();
                periodical.setSellId(resultSet.getInt("sell_id"));
                periodical.setTitle(resultSet.getString("title"));
                periodical.setPricePerMonth(resultSet.getDouble("price_per_month"));
                periodical.setPublisher(resultSet.getString("name"));
                periodical.setImage(resultSet.getString("images"));
                periodical.setPeriodicityPerYear(resultSet.getInt("periodicity_per_year"));
                periodical.setRating(resultSet.getDouble("rating"));
                list.add(periodical);
            }
        }
        return list;
    }

    @Override
    public int getNumberOfRows(Connection connection) throws SQLException {
        int numOfRows = 0;
        try(Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(GET_NUMBER_OF_ALL_PERIODICALS)) {
            if (resultSet.next()){
                numOfRows = resultSet.getInt(1);
            }
        }
        return numOfRows;
    }

    @Override
    public Periodical getPeriodicalByTitle(String title, Connection connection) throws SQLException {
        Periodical periodical = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(GET_ONE_PERIODICAL_BY_TITLE)) {
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    periodical = new Periodical();
                }
            }
        }
        return periodical;
    }
}
