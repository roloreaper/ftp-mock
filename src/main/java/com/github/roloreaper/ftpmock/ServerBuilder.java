package com.github.roloreaper.ftpmock;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by
 * User :roloreaper
 * Date :2016/03/09.
 */
public class ServerBuilder {

    private Map<Integer,FTPMockServer> servers;
    private static ServerBuilder instance;

    public ServerBuilder() {
        servers=new HashMap<>();
    }

    public static ServerBuilder getInstance() {
        if (instance==null) {
            instance = new ServerBuilder();
        }
        return instance;
    }

    public FTPMockServer build(int port) {
        FTPMockServer mockServer =getServer(port);
        mockServer.start(port);
        return mockServer;
    }

    private FTPMockServer getServer(int port) {
        FTPMockServer mockServer = servers.get(port);
        if (mockServer==null) {
            mockServer = new FTPMockServer();
            servers.put(port,mockServer);
        }
        return servers.get(port);
    }
}
