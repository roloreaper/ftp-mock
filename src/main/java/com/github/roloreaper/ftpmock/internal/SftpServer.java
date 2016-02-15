package com.github.roloreaper.ftpmock.internal;

import org.apache.log4j.Logger;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;
import org.apache.sshd.server.command.ScpCommandFactory;

import java.io.File;
import java.io.IOException;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/15.
 */
public class SftpServer {
    private static final Logger logger = Logger.getLogger(SftpServer.class);
    private SshServer sshd;

    public void start(int port,File keystorePath) {

        logger.info("Starting SFTP server on port " + port + " keystorePath: " + keystorePath);

        sshd = SshServer.setUpDefaultServer();
        sshd.setPort(Integer.valueOf(port));
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(keystorePath));

        sshd.setCommandFactory(new ScpCommandFactory());


        try {
            sshd.start();
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
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
