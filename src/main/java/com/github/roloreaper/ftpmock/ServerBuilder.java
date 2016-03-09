package com.github.roloreaper.ftpmock;

/**
 * Created by
 * User :roloreaper
 * Date :2016/03/09.
 */
public class ServerBuilder {

    private static ServerBuilder instance;

    public static ServerBuilder getInstance() {
        if (instance==null) {
            instance = new ServerBuilder();
        }
        return instance;
    }

    public FTPMockServer build(int port) {
        return null;
    }
}
