package com.trots.periodacals.entity;


import org.junit.Assert;
import org.junit.Test;

public class PublisherTest {

    @Test
    public void checkBeansTest(){
        Publisher publisher = new Publisher();
        publisher.setId(1);
        publisher.setName("publisher");
        publisher.setTelephoneNumber("380963303303");

        Assert.assertEquals(1, publisher.getId());
        Assert.assertEquals("publisher", publisher.getName());
        Assert.assertEquals("380963303303", publisher.getTelephoneNumber());
    }

}