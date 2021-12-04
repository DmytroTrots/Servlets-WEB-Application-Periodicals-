package com.trots.periodacals.entity;

import org.junit.Assert;
import org.junit.Test;

public class SubjectTest {

    @Test
    public void checkBeansTest() {
        Subject subject = new Subject();
        subject.setId(1);
        subject.setThemesOfBooks("theme");

        Assert.assertEquals(1, subject.getId());
        Assert.assertEquals("theme", subject.getThemesOfBooks());
    }
}