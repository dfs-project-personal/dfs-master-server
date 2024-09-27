package com.dfs.dfsmasterserver.exceptions;

public class AuthenticationFailedException extends RuntimeException {

    private static long serialVersionUID = 1L;
    public AuthenticationFailedException(String message) {
        super(message);
    }
}
