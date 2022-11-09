package com.marych.insuranceApp.tools;

import org.apache.log4j.Logger;

import java.util.logging.FileHandler;

public class UserLogger {
    private static final Logger logger = Logger.getLogger(UserLogger.class);
    EmailErrorSender emailErrorSender;
    public void info(String info){
        logger.info(info);
    }
    public void error(String error){
        logger.error(error);
        emailErrorSender = new EmailErrorSender();
        emailErrorSender.send(error);
    }
}
