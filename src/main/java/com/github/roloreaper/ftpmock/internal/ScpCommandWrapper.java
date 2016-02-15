package com.github.roloreaper.ftpmock.internal;

import org.apache.sshd.common.scp.ScpTransferEventListener;
import org.apache.sshd.server.command.ScpCommand;

import java.util.concurrent.ExecutorService;

/**
 * Created by
 * User :roloreaper
 * Date :2016/02/15.
 */
public class ScpCommandWrapper extends ScpCommand {

    /**
     * @param command         The command to be executed
     * @param executorService An {@link ExecutorService} to be used when
     *                        {@link #start(Environment)}-ing execution. If {@code null} an ad-hoc
     *                        single-threaded service is created and used.
     * @param shutdownOnExit  If {@code true} the {@link ExecutorService#shutdownNow()}
     *                        will be called when command terminates - unless it is the ad-hoc
     *                        service, which will be shutdown regardless
     * @param sendSize        Size (in bytes) of buffer to use when sending files
     * @param receiveSize     Size (in bytes) of buffer to use when receiving files
     * @param eventListener   An {@link ScpTransferEventListener} - may be {@code null}
     * @see ThreadUtils#newSingleThreadExecutor(String)
     * @see ScpHelper#MIN_SEND_BUFFER_SIZE
     * @see ScpHelper#MIN_RECEIVE_BUFFER_SIZE
     */
    public ScpCommandWrapper(String command, ExecutorService executorService, boolean shutdownOnExit, int sendSize, int receiveSize, ScpTransferEventListener eventListener) {
        super(command, executorService, shutdownOnExit, sendSize, receiveSize, eventListener);
    }
}
