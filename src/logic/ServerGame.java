package logic;

import netParts.IServerWorker;
import netParts.Server;

import java.awt.*;
import java.io.*;
import java.util.ArrayList;

public class ServerGame extends Game implements IServerWorker, Serializable {
    private ArrayList<Creature> creatures;
    private int onlinePlayers;
    public ServerGame(int port, Level level){
        this.Level = level;
        Server server = new Server(port, this);
        server.start();
    }
    @Override
    public void read(InputStream stream) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(stream);
        try {
            NetPlayer player = (NetPlayer) ois.readObject();
            if(player!=null) {
                Level.setPlayer(player);
            }
        }catch(ClassNotFoundException ignored){
            System.out.println(ignored.toString());
        }
    }

    @Override
    public void onConnectRead(InputStream stream) throws IOException {

    }

    @Override
    public void write(OutputStream stream) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(Level);
        oos.flush();
    }

    @Override
    public void onConnectWrite(OutputStream stream) throws IOException {
        NetPlayer player = (NetPlayer) Level.getCreatures().get(onlinePlayers);
        //Level.addPlayer(player);
        //onlinePlayers++;
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(Level);
        oos.flush();
        oos.writeObject(player);
        oos.flush();
    }
    public void run(){
        while(true){
            update();
            Level.refreshPlayers();
            onlinePlayers = Level.onlinePlayers();
        }
    }

    @Override
    public void onConnectionReset() {

    }
}
