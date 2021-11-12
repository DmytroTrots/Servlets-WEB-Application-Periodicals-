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

    public Integer insertPeriodical(Periodical periodical, Connection connection) throws SQLException {
        Integer periodicalId = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `dbperiodicals`.`periodical` " + "(`title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, " + "`description`, `rating`, `language`, `publisher_id`, `images`) " + "VALUES (?,?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, periodical.getTitle());
            preparedStatement.setInt(2, periodical.getNumberOfPages());
            preparedStatement.setInt(3, periodical.getPeriodicityPerYear());
            preparedStatement.setInt(4, periodical.getPercentageOfAdvertising());
            preparedStatement.setDouble(5, periodical.getPricePerMonth());
            preparedStatement.setString(6, periodical.getDescription());
            preparedStatement.setDouble(7, periodical.getRating());
            preparedStatement.setString(8, periodical.getLanguage());
            preparedStatement.setInt(9, periodical.getPublisherId());
            preparedStatement.setString(10, periodical.getImage());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    periodicalId = resultSet.getInt(1);
                }
            }
        }
        return periodicalId;
    }

    public boolean deletePeriodicalAdmin(Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM `dbperiodicals`.`periodical` WHERE (`sell_id` = ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    public Periodical getOnePeriod(Integer id, Connection con) throws SQLException {
        Periodical periodical = new Periodical();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT `sell_id`, `title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, `description`, `rating`, `language`, `publisher`.`name`, `publisher`.`telephone_number`, `images` FROM periodical, publisher WHERE sell_id = ?")) {
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
                    periodical.setLanguage(resultSet.getString("language"));
                    periodical.setTelephonePub(resultSet.getString("telephone_number"));
                    periodical.setPublisher(resultSet.getString("name"));
                    periodical.setImage(resultSet.getString("images"));
                }
            }
        }
        return periodical;
    }

    public boolean updatePeriodical(Periodical periodical, Integer id, String newImage, String oldImage, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `dbperiodicals`.`periodical` " + "SET `title` = ?, `number_of_pages` = ?, `periodicity_per_year` = ?, `percentage_of_advertising` = ?, " + "`price_per_month` = ?, `description` = ?, `rating` = ?, `language` = ?, `publisher_id` = ?, `images` = ? " + "WHERE (`sell_id` = ?)")) {
            preparedStatement.setString(1, periodical.getTitle());
            preparedStatement.setInt(2, periodical.getNumberOfPages());
            preparedStatement.setInt(3, periodical.getPeriodicityPerYear());
            preparedStatement.setInt(4, periodical.getPercentageOfAdvertising());
            preparedStatement.setDouble(5, periodical.getPricePerMonth());
            preparedStatement.setString(6, periodical.getDescription());
            preparedStatement.setDouble(7, periodical.getRating());
            preparedStatement.setString(8, periodical.getLanguage());
            preparedStatement.setInt(9, periodical.getPublisherId());
            if (!newImage.equals(oldImage) && !newImage.equals("")) {
                preparedStatement.setString(10, newImage);
            } else {
                preparedStatement.setString(10, oldImage);
            }
            preparedStatement.setInt(11, id);

            preparedStatement.executeUpdate();
        }
        return true;
    }

    public List<Periodical> getRecords(Connection connection) throws SQLException {
        List<Periodical> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select periodical.sell_id, periodical.rating, periodical.title, periodical.price_per_month, periodical.images, publisher.name from periodical, publisher WHERE publisher.id = publisher_id")) {
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Periodical periodical = new Periodical();
                    periodical.setSellId(resultSet.getInt("sell_id"));
                    periodical.setTitle(resultSet.getString("title"));
                    periodical.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    periodical.setPublisher(resultSet.getString("name"));
                    periodical.setImage(resultSet.getString("images"));
                    periodical.setRating(resultSet.getDouble("rating"));
                    list.add(periodical);
                }
            }
        }
        return list;
    }


    public List<Periodical> getRecordsWithSubject(int subject, Connection connection) throws SQLException {
        List<Periodical> periodicalsList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("SELECT `periodical`.`sell_id`, periodical.rating, `periodical`.`title`, " + "`periodical`.`price_per_month`,`periodical`.`images`, `publisher`.`name`\n" + "FROM periodical_has_subject\n" + "    " + "JOIN periodical ON periodical_has_subject.periodical_id = periodical.sell_id\n" + "    " + "JOIN publisher ON periodical.publisher_id = publisher.id\n" + "    " + "JOIN `subject` ON periodical_has_subject.subject_id = `subject`.id \n" + "WHERE `subject`.id = ?")) {
            preparedStatement.setInt(1, subject);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    Periodical periodical = new Periodical();
                    periodical.setSellId(resultSet.getInt("sell_id"));
                    periodical.setTitle(resultSet.getString("title"));
                    periodical.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    periodical.setPublisher(resultSet.getString("name"));
                    periodical.setImage(resultSet.getString("images"));
                    periodical.setRating(resultSet.getDouble("rating"));
                    periodicalsList.add(periodical);
                }
            }
        }
        return periodicalsList;
    }

    public Periodical getPeriodicalByName(String title, Connection connection) throws SQLException {
        Periodical periodical = null;
        try(PreparedStatement preparedStatement = connection.prepareStatement("select periodical.sell_id, periodical.rating, periodical.title, periodical.price_per_month, periodical.images, publisher.name from periodical, publisher WHERE publisher.id = publisher_id and periodical.title = ?")){
            preparedStatement.setString(1, title);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    periodical = new Periodical();
                    periodical.setSellId(resultSet.getInt("sell_id"));
                    periodical.setTitle(resultSet.getString("title"));
                    periodical.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    periodical.setPublisher(resultSet.getString("name"));
                    periodical.setImage(resultSet.getString("images"));
                    periodical.setRating(resultSet.getDouble("rating"));
                }
            }
        }
        return periodical;
    }
}

