package com.wiloon.enlab.service;

import com.wiloon.enlab.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 1/6/13
 * Time: 4:45 PM
 */
public class AccountServiceImpl implements AccountService {
    @Override
    public User findUserByName(String name) {
        User user = new User();
        user.setUserName("user0");
        user.setPassword("password0");
        return user;
    }
}
