package com.marych.insuranceApp.tools;

import org.apache.log4j.Logger;



public class UserLogger {
    private static final Logger logger = Logger.getLogger(UserLogger.class);
    public void info(String info){
        logger.info(info);
    }
    public void error(String error){
        logger.error(error);
    }
}
