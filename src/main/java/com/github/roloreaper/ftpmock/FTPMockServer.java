package com.github.roloreaper.ftpmock;

import com.github.roloreaper.ftpmock.internal.SftpServer;
import com.github.roloreaper.ftpmock.internal.SftpServerImpl;
import org.jmock.Expectations;
import org.jmock.Mockery;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/14.
 */
public class FTPMockServer implements MockServer {
    private boolean started=false;
    private Mockery mockery =new Mockery();
    private Expectations expectations = new Expectations(){};
    private FileServer fileServer=mockery.mock(FileServer.class);
    private SftpServer sftpServer = new SftpServerImpl();

    public void start(int port) {
        mockery.checking(expectations);
        sftpServer.start(port, null, fileServer);

        started=true;
    }

    public boolean isStarted() {
        return started;
    }

    public void addValidUser(String username, String password) {
        expectations.atLeast(1).of(fileServer).loginViaPassword(username, password);
        expectations.will(expectations.returnValue(true));
    }

    public void assertIsSatisfied() {
        mockery.assertIsSatisfied();
    }

    public void addInValidUser(String username, String password) {
        expectations.atLeast(1).of(fileServer).loginViaPassword(username, password);
        expectations.will(expectations.returnValue(false));
    }

    public void setSftpServer(SftpServer sftpServerImpl) {
        this.sftpServer = sftpServerImpl;
    }
}
