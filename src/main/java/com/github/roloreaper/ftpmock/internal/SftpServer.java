package com.github.roloreaper.ftpmock.internal;

import com.github.roloreaper.ftpmock.FileServer;

import java.io.File;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/20.
 */
public interface SftpServer {
    void start(int port, File keystorePath, FileServer fileServer) ;
    void stop();
}
