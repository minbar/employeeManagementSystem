package com.mindaugasbar.memployeemanagement.exceptions.handler;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;

@Aspect
public class DataAccessExceptionHandler {

    private Logger logger = LoggerFactory.getLogger(DataAccessExceptionHandler.class);

    @AfterThrowing
    public void dataAccessExceptionInterceptor(DataAccessException ex) {
        if(logger.isDebugEnabled()) {
            logger.debug("DataAccessException Caught");
        }
        System.out.println(ex.getCause().getMessage());
    }
}
