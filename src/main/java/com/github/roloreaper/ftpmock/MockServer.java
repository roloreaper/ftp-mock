package com.github.roloreaper.ftpmock;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/14.
 */
public interface MockServer {
    void start(int port);

    boolean isStarted();
}
