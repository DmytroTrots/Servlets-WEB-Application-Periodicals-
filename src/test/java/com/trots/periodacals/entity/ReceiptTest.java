package com.trots.periodacals.entity;


import org.junit.Assert;
import org.junit.Test;

public class ReceiptTest {

    @Test
    public void checkBeansTest(){
        Receipt receipt = new Receipt();
        receipt.setPeriodicalSellId(1);
        receipt.setStatusId(1);
        receipt.setAddress("address");
        receipt.setUserId(1);
        receipt.setEmail("email");
        receipt.setTelephoneNumber("380963303303");
        receipt.setTitle("title");
        receipt.setSurname("surname");
        receipt.setId(1);
        receipt.setName("name");
        receipt.setPricePerMonth(1.0);
        receipt.setAllPeriodicalsId("1");
        receipt.setStatusName("status_name");
        receipt.setPublisherName("publisher");
        receipt.setReceiptId(1);
        receipt.setMonths(1);

        Assert.assertEquals(1, receipt.getPeriodicalSellId());
        Assert.assertEquals(1, receipt.getStatusId());
        Assert.assertEquals("address", receipt.getAddress());
        Assert.assertEquals(1, receipt.getUserId());
        Assert.assertEquals("email", receipt.getEmail());
        Assert.assertEquals("380963303303", receipt.getTelephoneNumber());
        Assert.assertEquals("title", receipt.getTitle());
        Assert.assertEquals("surname", receipt.getSurname());
        Assert.assertEquals(1, receipt.getId());
        Assert.assertEquals("name", receipt.getName());
        Assert.assertEquals(1.0, receipt.getPricePerMonth(),0.1);
        Assert.assertEquals("1", receipt.getAllPeriodicalsId());
        Assert.assertEquals("status_name", receipt.getStatusName());
        Assert.assertEquals(1, receipt.getReceiptId());
        Assert.assertEquals(1, receipt.getMonths());
        Assert.assertEquals("publisher", receipt.getPublisherName());
    }
}