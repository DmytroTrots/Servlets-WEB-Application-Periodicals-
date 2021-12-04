package com.trots.periodacals.entity;

import org.junit.Assert;
import org.junit.Test;

public class PeriodicalTest {

    @Test
    public void checkBeansTest(){
        Periodical periodical = new Periodical("title",1,1,1,1.0,"description",1.0);
        periodical.setSellId(1);
        periodical.setPublisherId(1);
        periodical.setPublisher("publisher");
        periodical.setImage("image");

        Assert.assertEquals(1,periodical.getSellId());
        Assert.assertEquals("title", periodical.getTitle());
        Assert.assertEquals(1,periodical.getNumberOfPages());
        Assert.assertEquals(1,periodical.getPeriodicityPerYear());
        Assert.assertEquals(1,periodical.getPercentageOfAdvertising());
        Assert.assertEquals(1.0, periodical.getPricePerMonth(),0.1);
        Assert.assertEquals("description", periodical.getDescription());
        Assert.assertEquals(1.0, periodical.getRating(), 0.1);
        Assert.assertEquals(1, periodical.getPublisherId());
        Assert.assertEquals("publisher", periodical.getPublisher());
        Assert.assertEquals("image", periodical.getImage());
    }

}