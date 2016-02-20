package com.github.roloreaper.ftpmock.internal;

import com.github.roloreaper.ftpmock.FileServer;
import org.apache.log4j.Logger;
import org.apache.sshd.common.scp.ScpTransferEventListener;
import org.apache.sshd.server.Command;
import org.apache.sshd.server.SshServer;
import org.apache.sshd.server.command.ScpCommandFactory;
import org.apache.sshd.server.keyprovider.SimpleGeneratorHostKeyProvider;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.attribute.PosixFilePermission;
import java.util.Set;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/15.
 */
public class SftpServerImpl implements SftpServer {
    private static final Logger logger = Logger.getLogger(SftpServerImpl.class);
    private SshServer sshd;
    private ScpTransferEventListener scpTransferEventListener = new ScpTransferEventListener() {
        public void startFileEvent(FileOperation op, Path file, long length, Set<PosixFilePermission> perms) {
            System.out.println(file);
        }

        public void endFileEvent(FileOperation op, Path file, long length, Set<PosixFilePermission> perms, Throwable thrown) {
            System.out.println(file);
        }

        public void startFolderEvent(FileOperation op, Path file, Set<PosixFilePermission> perms) {
            System.out.println(file);
        }

        public void endFolderEvent(FileOperation op, Path file, Set<PosixFilePermission> perms, Throwable thrown) {
            System.out.println(file);
        }
    };

    public void start(int port, File keystorePath, FileServer fileServer) {

        logger.info("Starting SFTP server on port " + port + " keystorePath: " + keystorePath);

        sshd = SshServer.setUpDefaultServer();
        sshd.setPort(Integer.valueOf(port));
        sshd.setKeyPairProvider(new SimpleGeneratorHostKeyProvider(keystorePath));
        sshd.setPasswordAuthenticator(new MockedPasswordAuthenticator(fileServer));
        sshd.setCommandFactory(new ScpCommandFactory.Builder().addEventListener(this.scpTransferEventListener).build());


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
