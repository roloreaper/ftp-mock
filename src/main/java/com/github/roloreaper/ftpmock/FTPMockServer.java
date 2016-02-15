package com.github.roloreaper.ftpmock;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/14.
 */
public class FTPMockServer implements MockServer {
    boolean started=false;
    public void start(int port) {
            started=true;
    }

    public boolean isStarted() {
        return started;
    }
}
