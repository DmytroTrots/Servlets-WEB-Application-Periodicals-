package com.trots.periodacals.rerository;

import java.lang.reflect.InvocationTargetException;

/**
 * The type Abstract dao factory.
 */
public abstract class AbstractDAOFactory {
    private static String daoInstance;
    private static AbstractDAOFactory instance;

    /**
     * Instantiates a new Abstract dao factory.
     */
    protected AbstractDAOFactory() {
    }

    /**
     * Gets instance.
     *
     * @return the instance
     */
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

    /**
     * Sets dao class.
     *
     * @param instance the instance
     */
    public static void setDaoClass(String instance) {
        daoInstance = instance;
    }

    /**
     * Gets user dao.
     *
     * @return the user dao
     */
    public abstract UserDao getUserDao();

    /**
     * Gets periodical dao.
     *
     * @return the periodical dao
     */
    public abstract PeriodicalsDao getPeriodicalDao();

    /**
     * Gets receipt has periodical dao.
     *
     * @return the receipt has periodical dao
     */
    public abstract ReceiptHasPeriodicalDao getReceiptHasPeriodicalDao();

    /**
     * Gets subject dao.
     *
     * @return the subject dao
     */
    public abstract SubjectDao getSubjectDao();

    /**
     * Gets subject periodical dao.
     *
     * @return the subject periodical dao
     */
    public abstract SubjectPeriodicalsDao getSubjectPeriodicalDao();

    /**
     * Gets publisher dao.
     *
     * @return the publisher dao
     */
    public abstract PublisherDao getPublisherDao();

    /**
     * Gets receipt dao.
     *
     * @return the receipt dao
     */
    public abstract ReceiptDao getReceiptDao();
}
