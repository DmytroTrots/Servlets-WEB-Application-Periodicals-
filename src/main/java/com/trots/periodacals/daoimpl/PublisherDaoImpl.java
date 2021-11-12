package com.trots.periodacals.daoimpl;

import com.trots.periodacals.dao.PublisherDao;
import com.trots.periodacals.dbconnection.ConnectionPool;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Map;

public class PublisherDaoImpl {
    PublisherDao publisherDao = new PublisherDao();

    public Map<String, Integer> findAllPublishersWithoutTelephone(){
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return publisherDao.findAllPublishers(con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return Collections.emptyMap();
    }

    public Integer insertPublisherIntoDB(String publisher, String telephone){
        try(Connection con = ConnectionPool.getInstance().getConnection()) {
            return publisherDao.insertPublisher(publisher, telephone, con);
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }
}
