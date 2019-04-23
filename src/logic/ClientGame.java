package logic;

import engine.Game;
import engine.Level;
import netParts.old.Client;

import java.io.*;

public class ClientGame extends Game {
    private Client client;
    private InputStream inputStream;
    private OutputStream outputStream;
//    private ObjectInputStream ois;
//    private ObjectOutputStream oos;
    public ClientGame(String host, int port){
        try {
            this.client = new Client(host, port);
            this.inputStream = client.getInputStream();
            this.outputStream = client.getOutputStream();
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
    @Override
    public void update(){
        try {
            ObjectOutputStream oos = new ObjectOutputStream(outputStream);
            oos.writeObject((NetPlayer)Player);
            oos.flush();
            ObjectInputStream ois = new ObjectInputStream(inputStream);
            Level = (Level) ois.readObject();
            //System.out.println("Creature: " +Player.getPosition().toString()+  " " + Player.getFattiness());
            Player = Level.getPlayer(((NetPlayer) Player).getId());
        }catch(IOException ioe){
            client.closeConnection();
            System.out.println(ioe.toString());
        }catch(ClassNotFoundException ignored){
            System.out.println(ignored.toString());
        }
        //tryToFeedCreatures();
    }
}
