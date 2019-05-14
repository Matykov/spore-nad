//package logic;
//
//import engine.Creature;
//import engine.Level;
//import netParts.Async.Server.AsyncServer;
//import netParts.Async.Server.AttachmentServer;
//import netParts.Async.Server.IServerConnectionHandler;
//import netParts.Async.Server.IServerReadWriteHandler;
//
//
//import java.io.*;
//import java.net.SocketAddress;
//import java.nio.ByteBuffer;
//import java.nio.channels.AsynchronousSocketChannel;
//import java.util.ArrayList;
//
//
//public class AsyncServerGame extends Game implements IRunOver{
//    private ArrayList<Creature> creatures;
//    private int onlinePlayers;
//    private AsyncServer server;
//    public AsyncServerGame(int port, Level level)
//    {
//        this.level = level;
//        this.creatures = new ArrayList<Creature>(level.getCreatures());
//        bots = new ArrayList<Creature>();
//        this.server = new AsyncServer(port, new ConnectionHandler(this));
//        server.start();
//    }
//}
//class ConnectionHandler implements IServerConnectionHandler{
//    private IRunOver runOver;
//    public ConnectionHandler(IRunOver runOver){
//        this.runOver = runOver;
//    }
//    @Override
//    public void completed(AsynchronousSocketChannel client, AttachmentServer attach) {
//        try {
//            SocketAddress clientAddr = client.getRemoteAddress();
//            System.out.format("Accepted a  connection from  %s%n", clientAddr);
//            attach.server.accept(attach, this);
//            ReadWriteHandler rwHandler = new ReadWriteHandler(runOver);
//            AttachmentServer newAttach = new AttachmentServer();
//            newAttach.server = attach.server;
//            newAttach.client = client;
//            newAttach.buffer = ByteBuffer.allocate(2048);
//            newAttach.isRead = true;
//            newAttach.clientAddr = clientAddr;
//            client.read(newAttach.buffer, newAttach, rwHandler);
//        }catch(IOException ioe){
//            System.out.println("Connection fail");
//        }
//    }
//
//    @Override
//    public void failed(Throwable e, AttachmentServer attach) {
//
//    }
//}
//
//class ServerMessage implements IMessage
//{
//    private ByteBuffer level;
//    public ServerMessage(ByteBuffer level){
//        this.level = level;
//    }
//    @Override
//    public void run(IRunOver runOver)
//    {
//        if(runOver instanceof AsyncClientGame){
//            AsyncClientGame game = (AsyncClientGame) runOver;
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(level.array()));
//                Level inputLevel = (Level)ois.readObject();
//                game.setSectorNet(inputLevel);
//
//            }catch(IOException ioe){
//                System.out.println(ioe.toString());
//            }catch(ClassNotFoundException cnfe){
//                System.out.println(cnfe.toString());
//            }
//        }
//    }
//}
//
//class ReadWriteHandler implements IServerReadWriteHandler{
//    private IRunOver runOver;
//    public ReadWriteHandler(IRunOver runOver){
//        this.runOver = runOver;
//    }
//    @Override
//    public void completed(Integer result, AttachmentServer attach) {
//        if (result == -1) {
//            try {
//                attach.client.close();
//                System.out.format("Stopped   listening to the   client %s%n",
//                        attach.clientAddr);
//            } catch (IOException ex) {
//                ex.printStackTrace();
//            }
//            return;
//        }
//        if (attach.isRead) {
//            try {
//                ObjectInputStream ois = new ObjectInputStream(new ByteArrayInputStream(attach.buffer.array()));
//                IMessage message = (IMessage) ois.readObject();
//                message.run(runOver);
//                attach.isRead = false;
//            }catch (IOException ioe){
//                System.out.println(ioe.toString());
//            }catch(ClassNotFoundException cnfe){
//                System.out.println(cnfe.toString());
//            }
//        }
//        else {
//            Byte [] arr = new Byte[2048];
//            ObjectOutputStream oos = new ByteArrayObj
//            attach.buffer =
//            attach.client.write(attach.buffer, attach, this);
//            attach.isRead = true;
//            attach.buffer.clear();
//            attach.client.read(attach.buffer, attach, this);
//        }
//    }
//
//    @Override
//    public void failed(Throwable e, AttachmentServer attach) {
//
//    }
//}
