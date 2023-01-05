package com.marych.insuranceApp.service.information;


import org.apache.log4j.Logger;

public class AppLogger{
        private static final Logger logger = Logger.getLogger(AppLogger.class);
        public static void info(String info){
            logger.info(info);
        }
        public static void error(String error){
            logger.error(error);
        }

}
