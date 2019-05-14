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
    private ArrayList<NetPlayer> players;

    public ServerGame(int port, engine.Level level){
        this.level = level;
        this.server = new Server(port, this, 0);
        for(int i=0; i < SectorNet.size; i++){
            for(int j=0; j<SectorNet.size; j++){
                for(var player:curSectors.sectors[i][j].creatures)
                {
                    this.players.add((NetPlayer)player);
                }
            }
        }

        server.start();

    }
    public void setPlayer(NetPlayer player)
    {
        for(int i=0; i < SectorNet.size; i++){
            for(int j=0; j<SectorNet.size; j++){
                for(var inPlayer:curSectors.sectors[i][j].creatures)
                {
                    if(((NetPlayer)inPlayer).getId() == player.getId()){
                        inPlayer = player;
                        return;
                    }
                }
            }
        }
    }
//    public void renewCreatures(){
//        for(int i=0; i < SectorNet.size; i++){
//            for(int j=0; j<SectorNet.size; j++){
//                for(var player:curSectors.sectors[i][j].creatures)
//                {
//                    this.players.add((NetPlayer)player);
//                }
//            }
//        }
//    }
    @Override
    public void read(InputStream stream) throws IOException {
        ObjectInputStream ois = new ObjectInputStream(stream);
        try {
            IMessage message = (IMessage) ois.readObject();
            if(message!=null) {
                message.run(this);
                readyToWrite = true;
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
            oos.writeObject(new LevelMessage(curSectors));
            oos.flush();
            readyToWrite = false;
    }

    @Override
    public void onConnectWrite(OutputStream stream) throws IOException {
        NetPlayer player = (NetPlayer) players.get(onlinePlayers);
        onlinePlayers++;
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(new RegistrationMessage(curSectors, player));
        oos.flush();
    }
    public void run(){
        while(true){
            update();
            //level.refreshPlayers();
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
        super.update();
    }
}