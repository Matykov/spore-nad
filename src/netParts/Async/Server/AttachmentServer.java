package netParts.Async.Server;

import logic.IRunOver;

import java.net.SocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.AsynchronousServerSocketChannel;
import java.nio.channels.AsynchronousSocketChannel;

public class AttachmentServer {
    public AsynchronousServerSocketChannel server;
    public AsynchronousSocketChannel client;
    public ByteBuffer buffer;
    public SocketAddress clientAddr;
    public boolean isRead;
    public IRunOver runOver;
    public AttachmentServer(AsynchronousServerSocketChannel server){
        this.server = server;
    }
    public AttachmentServer(){
    }
}
