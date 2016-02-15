package com.github.roloreaper.ftpmock.internal;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.junit.Assert.*;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/15.
 */
public class SftpServerTest {

    private SftpServer sftpServer;
    private File file;

    @Before
    public void setUp() throws Exception {
        sftpServer = new SftpServer();
        file = new File("./hostKey.ser");

    }

    @After
    public void tearDown() throws Exception {
        sftpServer.stop();
    }

    @Test
    public void testStart() throws InterruptedException {
        sftpServer.start(8081,file);
        Thread.sleep(1000);
    }
}
