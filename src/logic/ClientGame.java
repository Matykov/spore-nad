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
            } catch (ClassNotFoundException ignored) {
                System.out.println(ignored.toString());
            }
        }catch(IOException ioe){
            this.client.closeConnection();
        }
    }
    public void registerSelf(SectorNet sectors, NetPlayer player){
        this.curSectors = sectors;
        this.player = player;
    }
    public void setSectorNet(SectorNet sectors)
    {
        this.curSectors = sectors;
        for(int i=0; i<NetSectorNet.netSize; i++){
            for(int j=0; j<NetSectorNet.netSize; j++){
                for(var player:curSectors.sectors[i][j].getCreatures()){
                    if(((NetPlayer)player).getId() == ((NetPlayer)player).getId()) {
                        this.player = (NetPlayer) player;
                        return;
                    }
                }
            }
        }
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
