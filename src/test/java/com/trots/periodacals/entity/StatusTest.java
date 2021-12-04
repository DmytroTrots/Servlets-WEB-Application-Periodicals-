package com.trots.periodacals.entity;

import org.junit.Assert;
import org.junit.Test;

public class StatusTest {
    @Test
    public void checkBeansTest(){
        Status status = new Status();
        status.setId(1);
        status.setName("name");

        Assert.assertEquals(1, status.getId());
        Assert.assertEquals("name", status.getName());
    }

}