package com.trots.periodacals.dbconnection;

import com.trots.periodacals.entity.Cart;
import com.trots.periodacals.entity.Periodical;
import com.trots.periodacals.entity.Receipt;
import com.trots.periodacals.entity.User;
import com.trots.periodacals.util.PBKDF2PasswordHashing;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.sql.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DBManager implements DBManagerInterface {

    private static final Logger log = LogManager.getLogger(DBManager.class);

    private static DBManager instance;

    public static synchronized DBManager getInstance() {
        if (instance == null) {
            instance = new DBManager();
        }
        return instance;
    }

    private DBManager() {
    }

    ///USER'S DAO METHODS
    ///method for login
    @Override
    public String loginCheck(Connection connection, User user) throws SQLException, NoSuchAlgorithmException, InvalidKeySpecException {
        String username = user.getUsername();
        String password = user.getPassword();

        String passwordDB;
        String roleDB;
        String userNameDB;
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery(SQLQuery.SELECT_CREDENTIALS_LOGIN)) {
            while (resultSet.next()) {
                userNameDB = resultSet.getString("username");
                passwordDB = resultSet.getString("password");
                roleDB = resultSet.getString("role");

                if (username.equals(userNameDB) && PBKDF2PasswordHashing.validatePassword(password, passwordDB) && roleDB.equals("admin"))
                    return "admin";
                else if (username.equals(userNameDB) && PBKDF2PasswordHashing.validatePassword(password, passwordDB) && roleDB.equals("manager"))
                    return "manager";
                else if (username.equals(userNameDB) && PBKDF2PasswordHashing.validatePassword(password, passwordDB) && roleDB.equals("customer"))
                    return "customer";
            }
            return "invalid user Credential";
        }
    }


    ///method for finding all users of DataBase
    @Override
    public List<User> findAll(Connection con) throws SQLException {
        List<User> users = new ArrayList<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery(SQLQuery.SELECT_ALL_USERS)) {
            while (resultSet.next()) {
                User user = new User();
                user.setId(resultSet.getInt(1));
                user.setUsername(resultSet.getString(2));
                user.setEmail(resultSet.getString(3));
                user.setPassword(resultSet.getString(4));
                user.setCreate_time(resultSet.getDate(5));
                user.setTelephone(resultSet.getString(6));
                user.setName(resultSet.getString(7));
                user.setSurname(resultSet.getString(8));
                user.setBanStatus(resultSet.getString(9));
                user.setBalance(resultSet.getDouble(10));
                user.setRole(resultSet.getString(11));
                user.setAddress(resultSet.getString(12));
                users.add(user);
            }
        }
        return users;
    }

    ///method for registering user by Admin(connect with previous method)
    @Override
    public boolean userRegistration(User user, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement(SQLQuery.INSERT_USER_BY_ADMIN)) {
            preparedStatement.setString(1, user.getUsername());
            preparedStatement.setString(2, user.getEmail());
            preparedStatement.setString(3, user.getPassword());
            preparedStatement.setString(4, user.getRole());
            preparedStatement.setString(5, user.getTelephone());
            preparedStatement.setString(6, user.getName());
            preparedStatement.setString(7, user.getSurname());
            preparedStatement.setString(8, user.getAddress());
            preparedStatement.executeUpdate();
            return true;
        }
    }

    ///method for balance updating
    @Override
    public boolean updateBalanceTopUp(int id, Double balance, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE `dbperiodicals`.`user` SET `balance` = ? WHERE (`id` = ?)")) {
            preparedStatement.setDouble(1, balance);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    ///method for finding one user by id
    @Override
    public User findSingleUserById(Integer id, Connection con) throws SQLException {
        User user = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT * FROM user WHERE (`id` = ?)")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    user = new User();
                    user.setName(resultSet.getString("name"));
                    user.setSurname(resultSet.getString("surname"));
                    user.setEmail(resultSet.getString("email"));
                    user.setTelephone(resultSet.getString("telephone"));
                    user.setAddress(resultSet.getString("address"));
                    user.setBalance(resultSet.getDouble("balance"));
                    user.setBanStatus(resultSet.getString("ban_status"));
                }
            }
        }
        return user;
    }

    ///method for ban user
    @Override
    public boolean setBanStatus(String status, Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("UPDATE `dbperiodicals`.`user` " + "SET `ban_status` = ? WHERE (`id` = ?)")) {
            preparedStatement.setString(1, status);
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    ///method for deleting user
    @Override
    public boolean deleteUserAdmin(Integer id, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM `dbperiodicals`.`user` WHERE (`id` = ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }
    ///USER'S DAO METHODS END

    ///START OF PERIODICAL DAO
    @Override
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
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT `sell_id`, `title`, `price_per_month`, `name` FROM periodical, publisher WHERE sell_id=? AND  `publisher`.`id` = periodical.publisher_id")) {
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
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT price_per_month FROM periodical WHERE sell_id = ?")) {
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
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT price_per_month FROM periodical WHERE sell_id = ?")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `dbperiodicals`.`periodical` " + "(`title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, " + "`description`, `rating`, `publisher_id`, `images`) " + "VALUES (?,?,?,?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
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
        try (PreparedStatement preparedStatement = con.prepareStatement("DELETE FROM `dbperiodicals`.`periodical` WHERE (`sell_id` = ?)")) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public Periodical getOnePeriod(Integer id, Connection con) throws SQLException {
        Periodical periodical = new Periodical();
        try (PreparedStatement preparedStatement = con.prepareStatement("SELECT `sell_id`, `title`, `number_of_pages`, `periodicity_per_year`, `percentage_of_advertising`, `price_per_month`, `description`, `rating`, `publisher`.`name`, `publisher`.`telephone_number`, `images` FROM periodical, publisher WHERE sell_id = ?")) {
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
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `dbperiodicals`.`periodical` " + "SET `title` = ?, `number_of_pages` = ?, `periodicity_per_year` = ?, `percentage_of_advertising` = ?, " + "`price_per_month` = ?, `description` = ?, `rating` = ?, `publisher_id` = ?, `images` = ? " + "WHERE (`sell_id` = ?)")) {
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
                periodical.setRating(resultSet.getDouble("rating"));
                list.add(periodical);
            }
        } return list;
    }

    @Override
    public Integer getNumberOfRows(Connection connection) throws SQLException {
        Integer numOfRows = 0;
        try(Statement statement = connection.createStatement();
        ResultSet resultSet = statement.executeQuery("SELECT COUNT(sell_id) FROM periodical")) {
            if (resultSet.next()){
                numOfRows = resultSet.getInt(1);
            }
        }
        return numOfRows;
    }
    ///END OF PERIODICAL DAO

    ///START OF PERIODICAL DAO
    @Override
    public Map<String, Integer> findAllPublishers(Connection con) throws SQLException {
        Map<String, Integer> publisherMap = new HashMap<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM publisher")) {
            while (resultSet.next()) {
                publisherMap.put(resultSet.getString("name"), resultSet.getInt("id"));
            }
        }
        return publisherMap;
    }

    @Override
    public Integer insertPublisher(String publisher, String telephone, Connection con) throws SQLException {
        Integer publisherId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`publisher` " + "(`name`, `telephone_number`) VALUES (?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, publisher);
            preparedStatement.setString(2, telephone);
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    publisherId = resultSet.getInt(1);
                }
            }
        }
        return publisherId;
    }
    ///END OF PERIODICAL DAO

    ///START OF RECEIPT DAO
    @Override
    public Integer insertOrder(Receipt receipt, Connection con) throws SQLException {
        Integer receiptId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`receipt` (`name`, " + "`surname`, `adress`, `telephone_number`, `status_id`, `user_id`, `email`)" + "VALUES (?, ?, ?, ?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, receipt.getName());
            preparedStatement.setString(2, receipt.getSurname());
            preparedStatement.setString(3, receipt.getAdress());
            preparedStatement.setString(4, receipt.getTelephoneNumber());
            preparedStatement.setInt(5, 1);
            preparedStatement.setInt(6, receipt.getUserId());
            preparedStatement.setString(7, receipt.getEmail());
            preparedStatement.executeUpdate();
            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    receiptId = resultSet.getInt(1);
                }

            }
        }
        return receiptId;
    }

    @Override
    public List<Receipt> findOrdersOfOneUser(Integer id, Connection con) throws SQLException {
        List<Receipt> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("select periodical_has_receipt.price_per_month, periodical_has_receipt.number_of_month, `status`.`status_name`, periodical.title, receipt.create_time, publisher.`name`\n" + "from ((((periodical_has_receipt\n" + "inner join receipt on  periodical_has_receipt.receipt_id = receipt.id)\n" + "inner join `periodical` on periodical_has_receipt.periodical_sell_id = periodical.sell_id)\n" + "inner join `status` on receipt.status_id = `status`.id)\n" + "inner join publisher on periodical.publisher_id = publisher.id)\n" + "where receipt.user_id = ? order by status_name asc")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Receipt receipt = new Receipt();
                    receipt.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    receipt.setMonths(resultSet.getInt("number_of_month"));
                    receipt.setTitle(resultSet.getString("title"));
                    receipt.setStatusName(resultSet.getString("status_name"));
                    receipt.setPublisherName(resultSet.getString("name"));
                    receipt.setCreate_time(resultSet.getDate("create_time"));
                    list.add(receipt);
                }
            }
        }
        return list;
    }

    public List<Receipt> findAllAcceptedOrdersOfUser(Connection connection) throws SQLException {
        List<Receipt> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement("select periodical_has_receipt.price_per_month, periodical_has_receipt.number_of_month, periodical.title, receipt.user_id\n" + "from periodical_has_receipt \n" + "inner join receipt on  periodical_has_receipt.receipt_id = receipt.id\n" + "inner join `periodical` on periodical_has_receipt.periodical_sell_id = periodical.sell_id\n" + "where cast(receipt.create_time as date) = ? and receipt.status_id = 2;")) {
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDateTime now = LocalDateTime.now();
            preparedStatement.setDate(1, Date.valueOf(dtf.format(now)));
            try (ResultSet resultSet = preparedStatement.executeQuery();) {
                while (resultSet.next()) {
                    Receipt receipt = new Receipt();
                    receipt.setPricePerMonth(resultSet.getDouble("price_per_month"));
                    receipt.setMonths(resultSet.getInt("number_of_month"));
                    receipt.setTitle(resultSet.getString("title"));
                    receipt.setUserId(resultSet.getInt("user_id"));
                    list.add(receipt);
                }
            }
        }
        return list;
    }
    ///END OF RECEIPT DAO

    ///START OF RECEIPT-PERIODICALS DAO
    public boolean insertRecordIntoReceiptHasPeriodicals(Receipt receipt, Integer receiptId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("INSERT INTO `dbperiodicals`.`periodical_has_receipt` (`periodical_sell_id`, `receipt_id`, `price_per_month`, `number_of_month`) VALUES (?, ?, ?, ?);")) {
            preparedStatement.setInt(1, receipt.getPeriodicalSellId());
            preparedStatement.setInt(2, receiptId);
            preparedStatement.setDouble(3, receipt.getPricePerMonth());
            preparedStatement.setInt(4, receipt.getMonths());
            preparedStatement.executeUpdate();
        }
        return true;
    }

    public List<Receipt> getAllOrdersForProccess(Connection connection) throws SQLException {
        List<Receipt> receiptList = new ArrayList<>();
        try (Statement statement = connection.createStatement(); ResultSet resultSet = statement.executeQuery("select receipt.id, receipt.adress, receipt.`name`, receipt.surname, MAX(`status`.`status_name`) as s, " + "receipt.user_id, receipt.telephone_number, receipt.email, receipt.create_time, " + "sum(periodical_has_receipt.price_per_month) as sum, " + "group_concat(periodical_sell_id) as conc from periodical_has_receipt, receipt, status " + "where receipt.status_id = `status`.`id`  and receipt_id = receipt.id  group by receipt_id order by s DESC")) {
            while (resultSet.next()) {
                Receipt receipt = new Receipt();
                receipt.setId(resultSet.getInt("id"));
                receipt.setAdress(resultSet.getString("adress"));
                receipt.setName(resultSet.getString("name"));
                receipt.setSurname(resultSet.getString("surname"));
                receipt.setStatusName(resultSet.getString("s"));
                receipt.setUserId(resultSet.getInt("user_id"));
                receipt.setTelephoneNumber(resultSet.getString("telephone_number"));
                receipt.setEmail(resultSet.getString("email"));
                receipt.setCreate_time(resultSet.getDate("create_time"));
                receipt.setPricePerMonth(resultSet.getDouble("sum"));
                receipt.setAllPeriodicalsId(resultSet.getString("conc"));
                receiptList.add(receipt);
            }
        }
        return receiptList;
    }

    public void acceptOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `dbperiodicals`.`receipt` SET `status_id` = '3' WHERE (`id` = ?)")) {
            preparedStatement.setInt(1, receiptId);
            preparedStatement.executeUpdate();
        }
    }

    public void discardOrderOfUserByAdmin(Integer receiptId, Connection connection) throws SQLException {
        try (PreparedStatement preparedStatement = connection.prepareStatement("UPDATE `dbperiodicals`.`receipt` SET `status_id` = '2' WHERE (`id` = ?)")) {
            preparedStatement.setInt(1, receiptId);
            preparedStatement.executeUpdate();
        }
    }

    ///START OF SUBJECT DAO
    @Override
    public Map<String, Integer> findAllSubjects(Connection con) throws SQLException {
        Map<String, Integer> subjectMap = new HashMap<>();
        try (Statement statement = con.createStatement(); ResultSet resultSet = statement.executeQuery("SELECT * FROM subject")) {
            while (resultSet.next()) {
                subjectMap.put(resultSet.getString("themes_of_books"), resultSet.getInt("id"));
            }
        }
        return subjectMap;
    }

    @Override
    public Integer insertSubject(String subj, Connection con) throws SQLException {
        Integer subjectId = null;
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO `dbperiodicals`.`subject` " + "(`themes_of_books`) VALUES (?)", Statement.RETURN_GENERATED_KEYS)) {
            preparedStatement.setString(1, subj);
            preparedStatement.executeUpdate();

            try (ResultSet resultSet = preparedStatement.getGeneratedKeys()) {
                if (resultSet.next()) {
                    subjectId = resultSet.getInt(1);
                }

            }
        }
        return subjectId;
    }
    ///END OF SUBJECT DAO

    ///START OF PERIODICAL<->SUBJECT DAO
    @Override
    public boolean insertSubjIdAndPeriodicalId(Integer subjId, Integer periodId, Connection con) throws SQLException {
        try (PreparedStatement preparedStatement = con.prepareStatement("INSERT INTO periodical_has_subject " + "(periodical_id, subject_id) VALUES (?,?)")) {
            preparedStatement.setInt(1, periodId);
            preparedStatement.setInt(2, subjId);
            preparedStatement.executeUpdate();
        }
        return true;
    }

    @Override
    public List<String> getSubjectOfPeriodById(Integer id, Connection con) throws SQLException {
        List<String> list = new ArrayList<>();
        try (PreparedStatement preparedStatement = con.prepareStatement("Select `subject`.`themes_of_books` " + "from periodical_has_subject\n" + "Inner join `subject` on subject_id=`subject`.id\n" + "where periodical_has_subject.periodical_id = ?")) {
            preparedStatement.setInt(1, id);
            try (ResultSet resultSet = preparedStatement.executeQuery()) {
                while (resultSet.next()) {
                    list.add(resultSet.getString("themes_of_books"));
                }
            }
        }
        return list;
    }
    ///END OF PERIODICAL<->SUBJECT DAO
}
