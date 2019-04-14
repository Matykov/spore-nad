package NetParts;

import java.io.IOException;
import java.net.ServerSocket;

public class AcceptingThread extends Thread{
    private Server server;
    public AcceptingThread(Server server) throws IOException {
        this.server = server;
        start();
    }
    @Override
    public void run(){
        while(true){
            server.accept();
        }
    }
}
