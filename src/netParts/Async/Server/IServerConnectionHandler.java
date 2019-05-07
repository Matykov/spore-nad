package netParts.Async.Server;

import java.nio.channels.AsynchronousSocketChannel;
import java.nio.channels.CompletionHandler;

public interface IServerConnectionHandler extends
        CompletionHandler<AsynchronousSocketChannel, AttachmentServer> {
    @Override
    void completed(AsynchronousSocketChannel client, AttachmentServer attach);
//        try {
//            SocketAddress clientAddr = client.getRemoteAddress();
//            System.out.format("Accepted a  connection from  %s%n", clientAddr);
//            attach.server.accept(attach, this);
//            ReadWriteHandler rwHandler = new ReadWriteHandler();
//            AttachmentServer newAttach = new AttachmentServer();
//            newAttach.server = attach.server;
//            newAttach.client = client;
//            newAttach.buffer = ByteBuffer.allocate(2048);
//            newAttach.isRead = true;
//            newAttach.clientAddr = clientAddr;
//            client.read(newAttach.buffer, newAttach, rwHandler);
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

    @Override
    void failed(Throwable e, AttachmentServer attach);
//        System.out.println("Failed to accept a  connection.");
//        e.printStackTrace();
}
