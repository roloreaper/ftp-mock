package com.github.roloreaper.ftpmock;

import com.github.roloreaper.ftpmock.internal.SftpServer;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.*;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/20.
 */
public class FTPMockServerTest {

    private Mockery mockery;
    private FTPMockServer ftpMockServer;
    private SftpServer sftpServer;
    private int port;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        ftpMockServer = new FTPMockServer();
        sftpServer = mockery.mock(SftpServer.class);
        ftpMockServer.setSftpServer(sftpServer);
    }

    @Test
    public void testStart() throws Exception {
        port = 8080;
        mockery.checking(new Expectations() {
            {
                one(sftpServer).start(with(port), with(aNull(File.class)), with(aNonNull(FileServer.class)));
                one(sftpServer).stop();
            }
        });
        ftpMockServer.start(port);
        assertThat(ftpMockServer.isStarted(), is(true));
    }

    @Test
    public void testAddValidUser() throws Exception {

        ftpMockServer.addValidUser("Test", "case");
        mockery.checking(new Expectations() {
            {
                one(sftpServer).start(with(8091), with(aNull(File.class)), with(aNonNull(FileServer.class)));
                one(sftpServer).stop();

            }
        });
        ftpMockServer.start(8091);
    }

    @Test
    public void testAddInValidUser() throws Exception {
        ftpMockServer.addInValidUser("test", "notCase");
        mockery.checking(new Expectations() {
            {
                one(sftpServer).start(with(8091), with(aNull(File.class)), with(aNonNull(FileServer.class)));
                one(sftpServer).stop();

            }
        });

        ftpMockServer.start(8091);

        }

        @After
    public void tearDown() throws Exception {

        ftpMockServer.stop();
        //ftpMockServer.assertIsSatisfied();
        mockery.assertIsSatisfied();
        }
}