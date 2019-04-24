package logic;

import engine.Creature;
import netParts.IServerWorker;
import netParts.Server;

import java.io.*;
import java.util.ArrayList;

public class ServerGame extends Game implements IServerWorker, Serializable {
    private ArrayList<Creature> creatures;
    private int onlinePlayers;
    private Server server;
    //private boolean gameStateUpdated;
    //private Logger logger = new Logger("Server_Client.log");
    public ServerGame(int port, engine.Level level){
        this.Level = level;
        this.server = new Server(port, this, 0);
        server.start();
        Creatures = new ArrayList<Creature>(level.getCreatures());
        Bots = new ArrayList<Creature>();

        //gameStateUpdated = true;
    }
    @Override
    public void read(InputStream stream) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(stream);
        try {
            NetPlayer player = (NetPlayer) ois.readObject();
            if(player!=null) {
                Level.setPlayer(player);
                Creatures = new ArrayList<>(Level.getCreatures());
                //gameStateUpdated = true;
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
        //if(gameStateUpdated){
            ObjectOutputStream oos = new ObjectOutputStream(stream);
            oos.writeObject(Level);
            oos.flush();
            //gameStateUpdated = false;
        //}
    }

    @Override
    public void onConnectWrite(OutputStream stream) throws IOException {
        NetPlayer player = (NetPlayer) Level.getCreatures().get(onlinePlayers);
        onlinePlayers++;
        ObjectOutputStream oos = new ObjectOutputStream(stream);
//        logger.log("Sending Level: " + player.getId() + " position: " + player.getPosition());
        oos.writeObject(Level);
        oos.flush();
        oos.writeObject(player);
        oos.flush();
        //gameStateUpdated = true;
    }
    public void run(){
        while(true){
            update();
            Level.refreshPlayers();
        }
    }

    @Override
    public void onConnectionReset() {

    }
}
