package com.github.roloreaper.ftpmock.internal;

import com.github.roloreaper.ftpmock.FileServer;
import org.apache.sshd.server.auth.password.PasswordAuthenticator;
import org.apache.sshd.server.session.ServerSession;
import org.jmock.api.ExpectationError;
import org.slf4j.LoggerFactory;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/16.
 */
public class MockedPasswordAuthenticator implements PasswordAuthenticator {
    private FileServer fileServer;
    private static final org.slf4j.Logger LOGGER = LoggerFactory.getLogger(MockedPasswordAuthenticator.class);

    public MockedPasswordAuthenticator(FileServer fileServer) {
        this.fileServer = fileServer;
    }

    public boolean authenticate(String username, String password, ServerSession session) {
        LOGGER.info("Loggin in user :" +username);
        try {

            return fileServer.loginViaPassword(username,password);
        }
        catch (ExpectationError e) {
            LOGGER.error("Missing loginViaPassword expectation for user : "+username ,e);
        }
        return false;
    }
}
