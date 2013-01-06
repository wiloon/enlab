package com.wiloon.enlab.controller;

import com.opensymphony.xwork2.ActionSupport;
import org.apache.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Created with IntelliJ IDEA.
 * User: wiloon
 * Date: 11/18/12
 * Time: 3:30 PM
 */
public class LoginAction extends ActionSupport {
    Logger logger = Logger.getLogger(LoginAction.class);
    private String userName;
    private String password;

    public String login() {
        logger.debug("login action start.");
        try {
            UsernamePasswordToken token = new UsernamePasswordToken(userName, password);
            Subject subject = SecurityUtils.getSubject();
            subject.login(token);
            token.clear();
        } catch (Exception e) {
            logger.error(e);
        }
        return SUCCESS;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
