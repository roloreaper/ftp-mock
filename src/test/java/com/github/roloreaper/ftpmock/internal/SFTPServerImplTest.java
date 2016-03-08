package com.github.roloreaper.ftpmock.internal;

import com.github.roloreaper.ftpmock.FileServer;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/15.
 */
public class SFTPServerImplTest {

    private SftpServerImpl sftpServerImpl;
    private File file;
    private Mockery mockery;
    private FileServer fileServer;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        sftpServerImpl = new SftpServerImpl();
        file = new File("./hostKey.ser");
        fileServer = mockery.mock(FileServer.class);
    }

    @After
    public void tearDown() throws Exception {
        sftpServerImpl.stop();
        mockery.assertIsSatisfied();
    }

    @Test
    public void testStart() throws Exception {
       // SshClient sshClient = SshClient.setUpDefaultClient();
        int port = 8081;
        //sshClient.start();
        //sshClient.connect("testUser", "127.0.0.1", port);

        //ClientSessionImpl clientSession = new ClientSessionImpl(sshClient,);
        //ScpClient scpClient = new DefaultScpClient(clientSession);

        mockery.checking(new Expectations(){{
            //one(fileServer).loginViaPassword("roloreaper","test");
            //will(returnValue(true));
        }});

        sftpServerImpl.start(port, file, fileServer);


        Thread.sleep(100);
    }

    @Test
    public void testStartIfAlreadyStarted() {
        sftpServerImpl.start(8081,file,fileServer);
        sftpServerImpl.start(8081,file,fileServer);
    }
}
