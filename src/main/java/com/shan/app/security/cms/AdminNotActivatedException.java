package com.shan.app.security.cms;

import org.springframework.security.core.AuthenticationException;

/**
 * This exception is thrown in case of a not activated user trying to authenticate.
 */
public class AdminNotActivatedException extends AuthenticationException {

    private static final long serialVersionUID = 1L;

    public AdminNotActivatedException(String message) {
        super(message);
    }

    public AdminNotActivatedException(String message, Throwable t) {
        super(message, t);
    }
}
