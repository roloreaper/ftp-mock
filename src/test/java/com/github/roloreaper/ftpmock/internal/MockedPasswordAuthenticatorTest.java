package com.github.roloreaper.ftpmock.internal;

import com.github.roloreaper.ftpmock.FileServer;
import org.apache.sshd.server.session.ServerSession;
import org.jmock.Expectations;
import org.jmock.Mockery;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/18.
 */
public class MockedPasswordAuthenticatorTest {

    private Mockery mockery;
    private FileServer fileServer;
    private MockedPasswordAuthenticator mockedPasswordAuthenticator;

    @Before
    public void setUp() throws Exception {
        mockery = new Mockery();
        fileServer = mockery.mock(FileServer.class);
        mockedPasswordAuthenticator = new MockedPasswordAuthenticator(fileServer);
    }

    @After
    public void tearDown() throws Exception {
        if (mockery!=null) {
            mockery.assertIsSatisfied();
        }
    }

    @Test
    public void testAuthenticateFailsForInvalidMockery() throws Exception {
        ServerSession serverSession = mockery.mock(ServerSession.class);
        mockery.checking(new Expectations() {
            {
                            }
        });
        mockery=null;
        assertThat(mockedPasswordAuthenticator.authenticate("user", "password", serverSession), is(false));

    }

    @Test
    public void testAuthenticateFailsForValidMock() throws Exception {
        ServerSession serverSession = mockery.mock(ServerSession.class);
        mockery.checking(new Expectations() {
            {
                one(fileServer).loginViaPassword("user", "password");
                will(returnValue(false));
            }
        });

        assertThat(mockedPasswordAuthenticator.authenticate("user", "password", serverSession), is(false));

    }

    @Test
    public void testAuthenticateOncePasses() throws Exception {
        ServerSession serverSession = mockery.mock(ServerSession.class);
        mockery.checking(new Expectations() {
            {
                one(fileServer).loginViaPassword("user", "password");
                will(returnValue(true));
            }
        });

        assertThat(mockedPasswordAuthenticator.authenticate("user", "password", serverSession), is(true));

    }
}