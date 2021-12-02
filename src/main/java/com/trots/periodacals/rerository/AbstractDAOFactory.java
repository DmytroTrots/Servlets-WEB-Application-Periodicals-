package com.trots.periodacals.rerository;

import java.lang.reflect.InvocationTargetException;

public abstract class AbstractDAOFactory {
    private static String daoInstance;
    private static AbstractDAOFactory instance;

    protected AbstractDAOFactory() {
    }

    public static synchronized AbstractDAOFactory getInstance() {
        if (instance == null) {
            try {
                instance = (AbstractDAOFactory) Class.forName(daoInstance).getDeclaredConstructor().newInstance();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
        return instance;
    }

    public static void setDaoClass(String instance) {
        daoInstance = instance;
    }

    public abstract UserDao getUserDao();

    public abstract PeriodicalsDao getPeriodicalDao();

    public abstract ReceiptHasPeriodicalDao getReceiptHasPeriodicalDao();

    public abstract SubjectDao getSubjectDao();

    public abstract SubjectPeriodicalsDao getSubjectPeriodicalDao();

    public abstract PublisherDao getPublisherDao();

    public abstract ReceiptDao getReceiptDao();
}
