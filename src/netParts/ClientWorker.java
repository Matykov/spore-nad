package netParts;

import logger.Logger;

import java.io.*;
import java.net.Socket;

public class ClientWorker implements Runnable {
    static String closeConnectionMessage = "CloseConnection";
    private Socket clientSession;
    private BufferedReader inStream;
    private BufferedWriter outStream;
    private ISocketReader sockReader;
    private ISocketWriter sockWriter;
    private Logger logger = new Logger("server_log.txt");
    public ClientWorker(Socket clientSession,
                        ISocketWriter sockWriter,
                        ISocketReader sockReader){
        try {
            this.clientSession = clientSession;
            this.inStream = new BufferedReader(
                    new InputStreamReader(clientSession.getInputStream()));
            this.outStream = new BufferedWriter(
                    new OutputStreamWriter(clientSession.getOutputStream()));
            this.sockReader = sockReader;
            this.sockWriter = sockWriter;
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
            while(!clientSession.isClosed()){
                if(inStream.ready()){
                    String entry = inStream.readLine();
                    if (entry.equalsIgnoreCase(closeConnectionMessage)){
                        inStream.close();
                        outStream.close();
                        clientSession.close();
                        String message = "Client exits: " + clientSession.toString();
                        logger.log(message);
                        System.out.println(message);
                        return;
                    }
                    sockReader.read(entry);
                    String sendMsg = sockWriter.write();
                    if(sendMsg != null) {
                        outStream.write(sendMsg);
                        outStream.flush();
                    }
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
