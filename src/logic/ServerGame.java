package logic;

import logger.Logger;
import netParts.*;

import java.io.*;
import java.util.ArrayList;

public class ServerGame extends Game implements IServerWorker, Serializable, IRunOver {
    private int onlinePlayers;
    private Server server;
    private boolean readyToWrite = false;
    private Logger logger = new Logger("Server_Client.log");
    private ArrayList<NetPlayer> players = new ArrayList<NetPlayer>();

    public ServerGame(int port){
        this.server = new Server(port, this, 0);
        this.curSectors = new NetSectorNet(4);
        this.players = ((NetSectorNet)curSectors).getPlayers();

        server.start();

    }
    public ArrayList<NetPlayer> getCreatures(){
        return players;
    }

    public void setPlayer(NetPlayer player)
    {
        player.activate();
        players.set(player.getId(), player);
    }

    @Override
    public void read(InputStream stream) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(stream);
        try {
            IMessage message = (IMessage) ois.readObject();
            if(message!=null) {
                message.run(this);
                for (var player : players){
                    if(player.isActive())
                        System.out.printf("player id: %d in x: %d y: %d\n", player.getId(), player.absPosition.x, player.absPosition.y);
                }
                //readyToWrite = true;
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
            oos.writeObject(new LevelMessage((NetSectorNet) curSectors));
            oos.flush();
            //readyToWrite = false;
    }

    @Override
    public void onConnectWrite(OutputStream stream) throws IOException {

        NetPlayer player = players.get(onlinePlayers);
        System.out.printf("Registrating new player: id: %d x: %d y: %d\n", player.getId(),
                player.absPosition.x, player.absPosition.y);
        player.activate();
        onlinePlayers++;
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(new RegistrationMessage((NetSectorNet) curSectors, player));
        oos.flush();
    }

    public void run(){
        while(true){
            var start = System.nanoTime();
            update();
            var time = (System.nanoTime() - start)/1000000;
            //System.out.println(time);
            try {
                Thread.sleep(50 - time);
            }
            catch (InterruptedException ie){
                System.out.println("Interrupted");
            }
            readyToWrite = true;
        }
    }

    @Override
    public void onConnectionReset() {

    }

    @Override
    public boolean isReady() {
        return readyToWrite;
    }

    @Override
    public void update(){
        //level.refreshPlayers();
        this.observeCreatures();
    }
}