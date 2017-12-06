package com.mindaugasbar.memployeemanagement.exceptions;

import static java.lang.String.format;

public class MissingEmployeeException extends Exception {

    public MissingEmployeeException(final String message) {
        super(message);
    }
}
