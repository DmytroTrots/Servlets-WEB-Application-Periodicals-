package com.trots.periodacals.entity;

import org.junit.Assert;
import org.junit.Test;


public class ReceiptHasPeriodicalTest {

    @Test
    public void checkBeansTest(){
        ReceiptHasPeriodical receiptHasPeriodical = new ReceiptHasPeriodical();
        receiptHasPeriodical.setReceiptId(1);
        receiptHasPeriodical.setPeriodicalSellId(1);
        receiptHasPeriodical.setMonths(1);
        receiptHasPeriodical.setPricePerMonth(1.0);

        Assert.assertEquals(1, receiptHasPeriodical.getReceiptId());
        Assert.assertEquals(1, receiptHasPeriodical.getPeriodicalSellId());
        Assert.assertEquals(1,receiptHasPeriodical.getMonths());
        Assert.assertEquals(1.0, receiptHasPeriodical.getPricePerMonth(), 0.1);
    }

}