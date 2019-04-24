package logic;

import engine.Level;
import logger.Logger;
import netParts.old.Client;

import java.io.*;
import java.util.ArrayList;

public class ClientGame extends Game {
    private Client client;
    private InputStream inputStream;
    private OutputStream outputStream;
    private Logger logger = new Logger("Client_Server.log");
    private boolean playerUpdated;
//    private ObjectInputStream ois;
//    private ObjectOutputStream oos;
    public ClientGame(String host, int port){
        try {
            this.Bots = new ArrayList<>();
            this.Creatures = new ArrayList<>();
            this.client = new Client(host, port);
            this.inputStream = client.getInputStream();
            this.outputStream = client.getOutputStream();
            this.ProgressBar = 0;
            playerUpdated = true;
//            this.oos = new ObjectOutputStream(outputStream);
//            this.ois = new ObjectInputStream(inputStream);

            try {
                ObjectInputStream ois = new ObjectInputStream(inputStream);
                this.Level = (engine.Level) ois.readObject();
                //ois = new ObjectInputStream(inputStream);
                this.Player = (NetPlayer) ois.readObject();
            } catch (ClassNotFoundException ignored) {
                System.out.println(ignored.toString());
            }
        }catch(IOException ioe){
            this.client.closeConnection();
        }
    }
//    public void setPlayerUpdated(){
//        playerUpdated = true;
//        System.out.println("updated");
//    }
    @Override
    public void update(){
        try {
            //if (playerUpdated) {
                //System.out.println("updated");
                ObjectOutputStream oos = new ObjectOutputStream(outputStream);
                logger.log("Sending Player: " + ((NetPlayer) Player).getId() + " position: " + Player.getPosition());
                oos.writeObject((NetPlayer) Player);
                oos.flush();

                ObjectInputStream ois = new ObjectInputStream(inputStream);
                Level = (Level) ois.readObject();
                //System.out.println("Creature: " +Player.getPosition().toString()+  " " + Player.getFattiness());
                Player = Level.getPlayer(((NetPlayer) Player).getId());
                logger.log("Player income: " + ((NetPlayer) Player).getId() + " position: " + Player.getPosition());
                //System.out.println("UUUUpdate");
                //playerUpdated = false;
            //}
            }catch(IOException ioe){
                client.closeConnection();
                System.out.println(ioe.toString());
            }catch(ClassNotFoundException ignored){
                System.out.println(ignored.toString());
            }
        //tryToFeedCreatures();
    }
}
