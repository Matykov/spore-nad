package netParts;

import logger.Logger;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {
    static int threadsCount = 10;
    private ServerSocket socket;
    private Logger logger = new Logger("server_log.txt");
    static ExecutorService executeIt = Executors.newFixedThreadPool(threadsCount);
    private ISocketReader sockReader;
    private ISocketWriter sockWriter;
    public Server(int socketPort,
                  ISocketWriter sockWriter,
                  ISocketReader sockReader){
        try {
            this.socket = new ServerSocket(socketPort);
            this.sockWriter = sockWriter;
            this.sockReader = sockReader;
            String message = "Server created: " + socket.toString();
            logger.log(message);
            System.out.println(message);
        }catch(IOException ioe){
            String message = "Creation error: " + ioe.toString();
            logger.log(message);
            System.out.println(message);
        }

    }
    public void run(){
        try {
            while (!socket.isClosed()) {
                Socket client = socket.accept();
                executeIt.execute(new ClientWorker(client, sockWriter, sockReader));
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
