package logic;

import engine.Creature;
import engine.Level;

import java.util.ArrayList;

public class AsyncClientGame extends Game implements IRunOver{
    private ArrayList<Creature> creatures;
    private int onlinePlayers;
    public AsyncClientGame(int port, Level level)
    {
        this.level = level;
        this.creatures = new ArrayList<Creature>(level.getCreatures());
        bots = new ArrayList<Creature>();
    }
    public void setLevel(Level level){
        this.level = level;
    }
}
