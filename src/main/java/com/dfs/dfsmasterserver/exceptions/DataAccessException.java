package com.dfs.dfsmasterserver.exceptions;

public class DataAccessException extends RuntimeException {

    private static long serialVersionUID = 1L;
    public DataAccessException(String message) {
        super(message);
    }
}
