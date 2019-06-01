package logic;

import engine.Food;
import engine.NetPlayer;
import logger.*;
import netParts.PlayerMessage;
import netParts.old.Client;

import java.io.*;
import java.util.ArrayList;

public class ClientGame extends Game implements IRunOver{
    private Client client;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Logger logger = new Logger("Client_Server.log");
    private int playerId;
    private boolean playerUpdated;
    private NetPlayer dummyPlayer;

    public NetSectorMap getSectorNet(){
        return (NetSectorMap)curSectors;
    }

    public ClientGame(String host, int port){
        try {
            this.client = new Client(host, port);
            this.inputStream = client.getInputStream();
            this.outputStream = client.getOutputStream();
            this.progressBar = 0;
            this.playerUpdated = false;
            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                System.out.println(ois.toString());
                IMessage message = (IMessage) ois.readObject();
                message.run(this);
                System.out.printf("I'v got id: %d \n", this.playerId);


                this.dummyPlayer = (NetPlayer) this.player;
            } catch (ClassNotFoundException ignored) {
                System.out.println(ignored.toString());
            }
        }catch(IOException ioe){
            this.client.closeConnection();
        }
    }
    public void registerSelf(NetSectorMap sectors, int playerId){
        this.playerId = playerId;
        setSectorNet(sectors);
    }

    public boolean getPlayerUpdated(){
        return playerUpdated;
    }

    public NetPlayer getDummyPlayer(){
        return dummyPlayer;
    }

    public ArrayList<NetPlayer> getPlayers(){
        return ((NetSectorMap)curSectors).getPlayers();
    }

    public ArrayList<Food> getFoods(){
        return ((NetSectorMap)curSectors).getFoods();
    }

    public void setSectorNet(NetSectorMap sectors)
    {
        this.curSectors = sectors;
        this.player = sectors.getPlayers().get(this.playerId);
        playerUpdated = false;
    }

    @Override
    public NetPlayer getPlayer(){
        return (NetPlayer)dummyPlayer;
    }

    @Override
    public void update(){
            try {
                playerUpdated = false;
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                System.out.println("Sending player: " + ((NetPlayer)dummyPlayer).getId() + " fattines: " + player.sectorPosition);
                oos.writeObject(new PlayerMessage((NetPlayer) dummyPlayer));
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                IMessage message = (IMessage) ois.readObject();
                message.run(this);
                System.out.println("player income: " + ((NetPlayer) player).getId() + " fattines: " + player.sectorPosition);
                dummyPlayer.setFattines(player.getFattiness());
                //observeCreatures();
                playerUpdated = true;
            } catch (IOException ioe) {
                client.closeConnection();
                System.out.println(ioe.toString());
            } catch (ClassNotFoundException ignored) {
                System.out.println(ignored.toString());
            }
    }
}
