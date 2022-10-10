package util;

import messages.CommandMessage;
import messages.CommandResponse;
import run.Server;

import java.net.DatagramPacket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ForkJoinPool;

public class ConnectionHandler implements Runnable{
    private Server server;
    private CommandManager commanager;

    private DatagramPacket dp;
    private ExecutorService fixedPool = Executors.newFixedThreadPool(1);
    private ForkJoinPool forkPool = ForkJoinPool.commonPool();

    public ConnectionHandler(Server server, DatagramPacket dp, CommandManager commanager) {
        this.server = server;
        this.dp = dp;
        this.commanager = commanager;
    }


    @Override
    public void run() {
        CommandMessage request = null;
        CommandResponse response = null;
        boolean stop = false;

    }
}
