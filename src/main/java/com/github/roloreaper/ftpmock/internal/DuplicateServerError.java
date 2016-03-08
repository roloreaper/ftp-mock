package com.github.roloreaper.ftpmock.internal;

/**
 * Created by
 * User :roloreaper
 * Date :2016/03/09.
 */
public class DuplicateServerError extends RuntimeException {
    public DuplicateServerError(int port,Throwable throwable) {
        super("Server already Running on this port or somthing else is Running on port : " +port,throwable);
    }
}
