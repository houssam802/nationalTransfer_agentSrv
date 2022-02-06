package com.agent.agent.exception;

public class JwtException extends RuntimeException {

    public JwtException() {
        super();
    }

    public JwtException(String message) {
        super(message);
    }

    public JwtException(String message, Throwable cause) {
        super(message, cause);
    }

    public JwtException(Throwable cause) {
        super(cause);
    }
}