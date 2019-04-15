package netParts;

import java.io.BufferedWriter;
import java.io.IOException;

public class SendingThread extends Thread{
    private BufferedWriter out;
    //private Socket socket;
    private String message;

    public SendingThread(BufferedWriter out, String message) {
            this.out = out;
            this.message = message;
            start();
    }
    @Override
    public void run(){
        try {
            out.write(message);
            out.flush();
        } catch(IOException ignored){}
    }

}
