package logger;

import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.Semaphore;

public class Logger {
    private FileWriter file;
    private Queue<String> writeQueue;
    public Logger(String filePath){
        try {
            this.file = new FileWriter(filePath, true);
            this.writeQueue = new ConcurrentLinkedQueue<>();
        }catch(IOException ioe){
            System.err.println(ioe.toString());
        }
    }

    public void log(String loggedInfo) {
        Date date = new Date();
        String logMessage = date.toString() + " " + loggedInfo + " \n";
        writeQueue.add(logMessage);
        try {
            flush();
        }catch (IOException ioe){
            System.err.println(ioe);
        }

    }

    private void flush() throws IOException {
        for (String str : writeQueue) {
            file.append(str);
            file.flush();
        }
        writeQueue.clear();

    }


}
