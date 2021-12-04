package com.trots.periodacals.entity;


import org.junit.Assert;
import org.junit.Test;

public class SubjectPeriodicalsTest {
    @Test
    public void checkBeansTest(){
        SubjectPeriodicals subjectPeriodicals = new SubjectPeriodicals();
        subjectPeriodicals.setSubjectId(1);
        subjectPeriodicals.setPeriodicalId(1);

        Assert.assertEquals(1, subjectPeriodicals.getSubjectId());
        Assert.assertEquals(1, subjectPeriodicals.getPeriodicalId());
    }
}