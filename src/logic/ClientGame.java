package logic;

import logger.Logger;
import netParts.PlayerMessage;
import netParts.old.Client;

import java.io.*;

public class ClientGame extends Game implements IRunOver{
    private Client client;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Logger logger = new Logger("Client_Server.log");
    private boolean playerUpdated;
    private int playerId;

    public ClientGame(String host, int port){
        try {
            //this.bots = new ArrayList<>();
            //this.creatures = new ArrayList<>();
            this.client = new Client(host, port);
            this.inputStream = client.getInputStream();
            this.outputStream = client.getOutputStream();
            this.progressBar = 0;
            playerUpdated = true;

            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                IMessage message = (IMessage) ois.readObject();
                message.run(this);
                System.out.printf("I'v got id: %d \n", this.playerId);
            } catch (ClassNotFoundException ignored) {
                System.out.println(ignored.toString());
            }
        }catch(IOException ioe){
            this.client.closeConnection();
        }
    }
    public void registerSelf(NetSectorNet sectors, NetPlayer player, int playerId){
        this.playerId = playerId;
        setSectorNet(sectors);
    }
//    public void setPlayer(NetPlayer player)
//    {
//        this.player = player;
//    }
    public void setSectorNet(NetSectorNet sectors)
    {
        this.curSectors = sectors;
        this.player = sectors.getPlayers().get(this.playerId);
    }

    @Override
    public void update(){
        try {
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                logger.log("Sending player: " + ((NetPlayer) player).getId() + " position: " + player.absPosition);
                oos.writeObject(new PlayerMessage((NetPlayer) player));
                oos.flush();
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                IMessage message = (IMessage) ois.readObject();
                message.run(this);
                logger.log("player income: " + ((NetPlayer) player).getId() + " position: " + player.absPosition);

            }catch(IOException ioe){
                client.closeConnection();
                System.out.println(ioe.toString());
            }catch(ClassNotFoundException ignored){
                System.out.println(ignored.toString());
            }
    }
}
