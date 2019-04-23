package netParts;

import logger.Logger;

import java.io.IOException;
import java.io.InputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server extends Thread{
    static int threadsCount = 10;
    private ServerSocket socket;
    private Logger logger = new Logger("server_log.txt");
    static ExecutorService executeIt = Executors.newFixedThreadPool(threadsCount);
    private long sleepyTime;
//    private ISocketReader sockReader;
//    private ISocketWriter sockWriter;
    private IServerWorker serverWorker;
    public Server(int socketPort,
                  IServerWorker serverWorker, long sleepyTime){
        try {
            this.socket = new ServerSocket(socketPort);
            this.sleepyTime = sleepyTime;
//            this.sockWriter = sockWriter;
//            this.sockReader = sockReader;
            this.serverWorker = serverWorker;
            String message = "Server created: " + socket.toString();
            logger.log(message);
            System.out.println(message);
        }catch(IOException ioe){
            String message = "Creation error: " + ioe.toString();
            logger.log(message);
            System.out.println(message);
        }

    }
    @Override
    public void run(){
        try {
            while (!socket.isClosed()) {
                Socket client = socket.accept();
                executeIt.execute(new ClientWorker(client, serverWorker, sleepyTime));
                String message = "New Client connected: " + client.toString();
                logger.log(message);
                System.out.println(message);
            }
        }catch(IOException ioe){
            String message = "Connection error: " + ioe.toString();
            logger.log(message);
            System.out.println(message);
        }
    }
}
