package logic;

import engine.Creature;
import engine.Food;
import engine.NetPlayer;
import logger.Logger;
import netParts.*;

import java.awt.*;
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
        this.curSectors = new NetSectorMap(4);
        this.players = ((NetSectorMap)curSectors).getPlayers();

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
                        System.out.printf("player id: %d in x: %d y: %d\n", player.getId(),
                                player.sectorPosition.x, player.sectorPosition.y);
                }
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
            oos.writeObject(new LevelMessage((NetSectorMap) curSectors));
            oos.flush();
            //readyToWrite = false;
    }

    @Override
    public void onConnectWrite(OutputStream stream) throws IOException {

        NetPlayer player = players.get(onlinePlayers);
        //System.out.printf("Registrating new player: id: %d x: %d y: %d\n", player.getId(),
        //        player.sectorPosition.x, player.sectorPosition.y);
        player.activate();
        onlinePlayers++;
        ObjectOutputStream oos = new ObjectOutputStream(stream);
        oos.writeObject(new RegistrationMessage((NetSectorMap) curSectors, player));
        oos.flush();
    }

    public void run(){
        while(true){
            //var start = System.nanoTime();
            update();
            //var time = (System.nanoTime() - start)/1000000;
            //System.out.println(time);
            try {
                Thread.sleep(100);
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
    protected void observeSectorPosition(int curXNet, int curYNet, Creature creature)
    {
        var creaturePosX = creature.sectorPosition.x;
        var creaturePosY = creature.sectorPosition.y;

        if (creaturePosX < 0)
        {
            var newX = curXNet - 1 >= 0 ? curXNet - 1 : NetSectorMap.netSize - 1;
            ((NetPlayer)creature).sectorPosition.x = curSectors.sectorSize.width;
        }
        else if (creaturePosX > curSectors.sectorSize.width) {
            var newX = curXNet + 1 < NetSectorMap.netSize ? curXNet + 1 : 0;
            ((NetPlayer)creature).sectorPosition.x = 0;
        }
        if (creaturePosY < 0) {
            var newY = curYNet - 1 >= 0 ? curYNet - 1 : NetSectorMap.netSize - 1;
            ((NetPlayer)creature).sectorPosition.y = curSectors.sectorSize.height;
        }
        else if (creaturePosY > curSectors.sectorSize.height) {
            var newY = curYNet + 1 < NetSectorMap.netSize ? curYNet + 1 : 0;
            ((NetPlayer)creature).sectorPosition.y = 0;
        }

    }

    @Override
    public void observeCreatures()
    {
        for (var player : players)
        {
            if(player.isActive())
            {
                var sector = player.getSector((NetSectorMap) curSectors);
                eatFood(sector, player);
                eatCreatures(sector, player);
                tick++;
            }
        }
    }

    @Override
    protected void eatFood(Sector curSec, Creature creature)
    {
        var removedFood = new ArrayList<Food>();
        var playerSector = ((NetPlayer)creature).getSector((NetSectorMap)curSectors);
        for (Food food : ((NetSectorMap)curSectors).getFoods())
        {
            if(playerSector.equals(food.getSector((NetSectorMap)curSectors))) {
                var keys = food.getPieces().keySet();
                for (Point piecePosition : keys) {
                    //System.out.printf("Creture: %d current weight: %d\n", ((NetPlayer)creature).getId(), creature.getFattiness());
                    //System.out.printf("creature pos : %d %d\n", ((NetPlayer)creature).sectorPosition.x, ((NetPlayer)creature).sectorPosition.y);
                    //System.out.printf("piece pos : %d %d\n", piecePosition.x, piecePosition.y);
                    if (dist(creature.sectorPosition, piecePosition) <= creature.getFattiness() - food.MaxSize) {
                        int nutrition = food.destroyPiece(piecePosition);
                        //System.out.printf("Creture: %d current weight: %d\n", ((NetPlayer)creature).getId(), creature.getFattiness());
                        creature.putOnWeight(nutrition);
                        //System.out.printf("Creture: %d weight after putOn: %d\n", ((NetPlayer)creature).getId(), creature.getFattiness());
                        if (food.isEmpty)
                            removedFood.add(food);
//                        if (creature instanceof Player) {
//                            progressBar += nutrition;
//                        }
                    }
                }
            }
        }
        curSec.removeFood(removedFood);
    }

    @Override
    protected void eatCreatures(Sector curSec, Creature creature)
    {
        for(var preyCreature : players)
        {
            if(preyCreature.isActive()) {
                if (creature.getFattiness() > preyCreature.getFattiness() &&
                        dist(creature.sectorPosition, preyCreature.sectorPosition) <=
                                creature.getFattiness() - preyCreature.getFattiness()) {
                    creature.eat(preyCreature);
                }
            }
        }
    }

    @Override
    public void update(){
        //level.refreshPlayers();
        this.observeCreatures();
    }
}