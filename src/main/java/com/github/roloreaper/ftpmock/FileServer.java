package com.github.roloreaper.ftpmock;

import java.io.File;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/16.
 */
public interface FileServer {
    boolean loginViaPassword(String username ,String password);

    void upload(String from, String to);

    File download(String from, String to);
}
