package com.wiloon.enlab.service;

import com.wiloon.enlab.entity.User;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 1/6/13
 * Time: 4:43 PM
 */
public interface AccountService {
    public User findUserByName(String name);
}
