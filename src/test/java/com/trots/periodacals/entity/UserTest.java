package com.trots.periodacals.entity;

import org.junit.Assert;
import org.junit.Test;

public class UserTest {
    @Test
    public void checkBeansTest(){
        User user = new User();
        user.setId(1);
        user.setUsername("username");
        user.setEmail("email");
        user.setPassword("password");
        user.setTelephone("380963303303");
        user.setRole("role");
        user.setName("name");
        user.setSurname("surname");
        user.setBanStatus("banned");
        user.setBalance(1.0);
        user.setAddress("address");

        Assert.assertEquals(1, user.getId());
        Assert.assertEquals("username", user.getUsername());
        Assert.assertEquals("email",user.getEmail());
        Assert.assertEquals("password",user.getPassword());
        Assert.assertEquals("380963303303",user.getTelephone());
        Assert.assertEquals("role",user.getRole());
        Assert.assertEquals("name",user.getName());
        Assert.assertEquals("surname", user.getSurname());
        Assert.assertEquals("banned",user.getBanStatus());
        Assert.assertEquals(1.0, user.getBalance(),0.1);
        Assert.assertEquals("address",user.getAddress());



    }

}