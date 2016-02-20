package com.github.roloreaper.ftpmock.internal;

import org.apache.sshd.server.Command;
import org.apache.sshd.server.Environment;
import org.apache.sshd.server.ExitCallback;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/18.
 */
public class WrappedCommand implements Command {
    private Command command ;
    public WrappedCommand(Command command) {
        this.command=command;
    }

    public void setInputStream(InputStream in) {
        command.setInputStream(in);
    }

    public void setOutputStream(OutputStream out) {
        command.setOutputStream(out);
    }

    public void setErrorStream(OutputStream err) {
        command.setErrorStream(err);
    }

    public void setExitCallback(ExitCallback callback) {
        command.setExitCallback(callback);
    }

    public void start(Environment env) throws IOException {
        command.start(env);
    }

    public void destroy() {
        command.destroy();
    }
}
