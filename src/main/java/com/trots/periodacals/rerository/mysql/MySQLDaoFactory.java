package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.rerository.*;

public class MySQLDaoFactory extends AbstractDAOFactory {
    public UserDao userDao;

    public PeriodicalsDao periodicalsDao;

    public ReceiptHasPeriodicalDao receiptHasPeriodicalDao;

    public SubjectDao subjectDao;

    public SubjectPeriodicalsDao subjectPeriodicalsDao;

    public PublisherDao publisherDao;

    public ReceiptDao receiptDao;

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            return new MySQLUserDao();
        }
        return userDao;
    }

    @Override
    public PeriodicalsDao getPeriodicalDao() {
        if (periodicalsDao == null) {
            return new PeriodicalsDaoImpl();
        }
        return periodicalsDao;
    }

    @Override
    public ReceiptHasPeriodicalDao getReceiptHasPeriodicalDao() {
        if (receiptHasPeriodicalDao == null) {
            return new MySQLReceiptHasPeriodicalsDao();
        }
        return receiptHasPeriodicalDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            return new MySQLSubjectDao();
        }
        return subjectDao;
    }

    @Override
    public SubjectPeriodicalsDao getSubjectPeriodicalDao() {
        if (subjectPeriodicalsDao == null) {
            return new MySQLSubjectPeriodicalsDao();
        }
        return subjectPeriodicalsDao;
    }

    @Override
    public PublisherDao getPublisherDao() {
        if (publisherDao == null) {
            return new MysSQLPublisherDao();
        }
        return publisherDao;
    }

    @Override
    public ReceiptDao getReceiptDao() {
        if (receiptDao == null) {
            return new MySQLReceiptDao();
        }
        return receiptDao;
    }
}
