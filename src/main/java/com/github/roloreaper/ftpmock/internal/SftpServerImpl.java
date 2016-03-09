package com.github.roloreaper.ftpmock.internal;

import com.github.roloreaper.ftpmock.FileServer;
import org.apache.log4j.Logger;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;

import java.io.File;
import java.io.IOException;
import java.net.BindException;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/15.
 */
public class SftpServerImpl implements SftpServer {
    private static final Logger logger = Logger.getLogger(SftpServerImpl.class);
    private SshServer sshd;

    public void start(int port, File keystorePath, FileServer fileServer) {

        logger.info("Starting SFTP server on port " + port + " keystorePath: " + keystorePath);

        sshd = SshServer.setUpDefaultServer();
        sshd.setPort(Integer.valueOf(port));
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(keystorePath));
        sshd.setPasswordAuthenticator(new MockedPasswordAuthenticator(fileServer));
        sshd.setCommandFactory(new ScpCommandFactory.Builder().build());


        try {
            sshd.start();
        } catch (BindException b) {
            logger.error(b.getMessage(), b);
            throw new DuplicateServerError(port,b);
        } catch (IOException e) {
            logger.error(e);
        }
    }

    public void stop() {
        try {
            sshd.stop();
        } catch (IOException e) {
            logger.error(e);
        }
    }

}
