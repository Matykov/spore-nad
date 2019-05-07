package netParts.Async.Server;
import logic.IRunOver;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.AsynchronousServerSocketChannel;


public class AsyncServer extends Thread {
    private AsynchronousServerSocketChannel server;
    private InetSocketAddress address;
    private AttachmentServer attachment;
    private IServerConnectionHandler connectionHandler;
    private IRunOver runOver;
    public AsyncServer(int port, IServerConnectionHandler connectionHandler){
        try {
            this.server = AsynchronousServerSocketChannel.open();
            this.address = new InetSocketAddress("localhost", port);
            server.bind(address);
            System.out.format("Server is listening at %s%n", address);
            this.attachment = new AttachmentServer(server);
            this.connectionHandler = connectionHandler;
        }catch(IOException ioe){
            System.out.println(ioe.toString());
        }
    }
    public AsyncServer(int port, IServerConnectionHandler connectionHandler, IRunOver runOver){
        try {
            this.runOver = runOver;
            this.server = AsynchronousServerSocketChannel.open();
            this.address = new InetSocketAddress("localhost", port);
            server.bind(address);
            System.out.format("Server is listening at %s%n", address);
            this.attachment = new AttachmentServer(server);
            this.connectionHandler = connectionHandler;
        }catch(IOException ioe){
            System.out.println(ioe.toString());
        }
    }
    @Override
    public void run(){
        server.accept(attachment, connectionHandler);
        try {
            Thread.currentThread().join();
        }catch(InterruptedException ie){
            System.out.println(ie.toString());
        }
    }
}