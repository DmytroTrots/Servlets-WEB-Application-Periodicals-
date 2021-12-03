package com.trots.periodacals.rerository.mysql;

import com.trots.periodacals.rerository.*;

/**
 * The type Dao impl factory.
 */
public class DaoImplFactory extends AbstractDAOFactory {
    /**
     * The User dao.
     */
    public UserDao userDao;

    /**
     * The Periodicals dao.
     */
    public PeriodicalsDao periodicalsDao;

    /**
     * The Receipt has periodical dao.
     */
    public ReceiptHasPeriodicalDao receiptHasPeriodicalDao;

    /**
     * The Subject dao.
     */
    public SubjectDao subjectDao;

    /**
     * The Subject periodicals dao.
     */
    public SubjectPeriodicalsDao subjectPeriodicalsDao;

    /**
     * The Publisher dao.
     */
    public PublisherDao publisherDao;

    /**
     * The Receipt dao.
     */
    public ReceiptDao receiptDao;

    @Override
    public UserDao getUserDao() {
        if (userDao == null) {
            return new UserDaoImpl();
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
            return new ReceiptHasPeriodicalsDaoImpl();
        }
        return receiptHasPeriodicalDao;
    }

    @Override
    public SubjectDao getSubjectDao() {
        if (subjectDao == null) {
            return new SubjectDaoImpl();
        }
        return subjectDao;
    }

    @Override
    public SubjectPeriodicalsDao getSubjectPeriodicalDao() {
        if (subjectPeriodicalsDao == null) {
            return new SubjectPeriodicalsDaoImpl();
        }
        return subjectPeriodicalsDao;
    }

    @Override
    public PublisherDao getPublisherDao() {
        if (publisherDao == null) {
            return new PublisherDaoImpl();
        }
        return publisherDao;
    }

    @Override
    public ReceiptDao getReceiptDao() {
        if (receiptDao == null) {
            return new ReceiptDaoImpl();
        }
        return receiptDao;
    }
}
