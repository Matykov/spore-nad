package netParts;

import logger.Logger;

import java.io.*;
import java.net.Socket;

public class ClientWorker implements Runnable {
    static String closeConnectionMessage = "CloseConnection";
    private Socket clientSession;
    private BufferedReader bufferedReader;
    private InputStream inputStream;
    private OutputStream outputStream;
    private IServerWorker serverWorker;
    private long sleepyTime;
//    private ISocketReader sockReader;
//    private ISocketWriter sockWriter;
    private Logger logger = new Logger("server_log.txt");
    public ClientWorker(Socket clientSession,
                        IServerWorker serverWorker, long sleepyTime){
        try {
            this.clientSession = clientSession;
            this.inputStream = clientSession.getInputStream();
            this.bufferedReader = new BufferedReader(
                    new InputStreamReader(this.inputStream));
            this.outputStream = clientSession.getOutputStream();
            this.sleepyTime = sleepyTime;
//            this.sockReader = sockReader;
//            this.sockWriter = sockWriter;
            this.serverWorker = serverWorker;
        }catch(IOException ioe) {
            String message = "Client thread err: " +
                    ioe.toString() +
                    "\n\tClient info: " +
                    clientSession.toString();
            logger.log(message);
            System.out.println(message);
        }
    }
    @Override
    public void run() {
        try{
            if(!clientSession.isClosed()){
                if(bufferedReader.ready())
                    serverWorker.onConnectRead(inputStream);
                serverWorker.onConnectWrite(outputStream);
            }
            while(!clientSession.isClosed()){
                try {
                    if (bufferedReader.ready()) {
                        if (bufferedReader.ready())
                            serverWorker.read(inputStream);
//                        try {
//                            Thread.sleep(sleepyTime);
//                        }catch(InterruptedException ignored){
//                            System.out.println(ignored.toString());
//                        }
                        serverWorker.write(outputStream);
                    }
                }catch(IOException ioe){
                    bufferedReader.close();
                    outputStream.close();
                    inputStream.close();
                    clientSession.close();
                    String message = "Close client connection: " + ioe.toString();
                    System.out.println(message);
                    logger.log(message);
                    return;
                }
                try {
                    Thread.sleep(1);
                }catch(InterruptedException ignored){}
            }

        }catch(IOException ioe){
            String message = "Client thread err: " +
                    ioe.toString() +
                    "\n\tClient info: " +
                    clientSession.toString();
            logger.log(message);
            System.out.println(message);
        }
    }
}
