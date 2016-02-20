package com.github.roloreaper.ftpmock;

import java.io.File;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/14.
 */
public interface MockServer {
    void start(int port);

    boolean isStarted();

    void addValidUser(String username, String password);

    void assertIsSatisfied();
}
