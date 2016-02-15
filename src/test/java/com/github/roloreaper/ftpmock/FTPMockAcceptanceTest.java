package com.github.roloreaper.ftpmock;

import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/14.
 */
public class FTPMockAcceptanceTest {
    @Test
    public void testServerStartsOnPort() throws Exception {
        MockServer mockServer= new FTPMockServer();
        mockServer.start(3334);
        assertThat(mockServer.isStarted(),is(true));
    }
}
