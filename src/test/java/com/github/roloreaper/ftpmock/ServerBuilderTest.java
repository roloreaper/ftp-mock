package com.github.roloreaper.ftpmock;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by
 * User :roloreaper
 * Date :2016/03/09.
 */
public class ServerBuilderTest {

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
        ServerBuilder.getInstance().stopAll();
    }

    @Test
    public void testGetInstanceIsSingleTon() throws Exception {
        assertThat(ServerBuilder.getInstance(),is(ServerBuilder.getInstance()));
    }

    @Test
    public void testThatEachTimeWeBuildItStartsAServer() {
        FTPMockServer ftpMockServer = ServerBuilder.getInstance().build(8080);
        //ftpMockServer.stop();
        assertThat(ftpMockServer,is(notNullValue(FTPMockServer.class)));
        try {
            FTPMockServer secondftpMockServer = ServerBuilder.getInstance().build(8080);
            secondftpMockServer.stop();
        }
        catch (Exception e) {
            System.out.println("Should Throw Exception");
        }
    }
}