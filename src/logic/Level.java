package logic;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class Level implements Serializable
{
    private ArrayList<Creature> Creatures;
    private ArrayList<Food> Meals;
    private Player Player;

    public final int AverageFoodCount;

    public Level(Player player, Creature[] creatures, Point[] foodPoses, int averageFoodCount)
    {
        Player = player;
        Creatures = new ArrayList<>(Arrays.asList(creatures));
        AverageFoodCount = averageFoodCount;
        generateFood(foodPoses);
    }

    public void addPlayer(Player player){
        Creatures.add(player);
    }

    public void setPlayer(NetPlayer player){
        player.activate();
        Creatures.set(player.getId(), player);
    }

    public void refreshPlayers(){
        for(var creature:Creatures){
            if(creature instanceof NetPlayer)
                ((NetPlayer) creature).checkLive();
        }
    }

    public int onlinePlayers(){
        int count = 0;
        for(var creature:Creatures){
            if (creature instanceof NetPlayer && ((NetPlayer) creature).isActive())
                count++;
        }
        return count;
    }

    public ArrayList<Creature> getCreatures()
    {
        return Creatures;
    }

    public Player getPlayer()
    {
        return Player;
    }
    public NetPlayer getPlayer(int i){
        return (NetPlayer) Creatures.get(i);
    }

    public ArrayList<Food> getMeals()
    {
        return Meals;
    }

    private void generateFood(Point[] positions)
    {
        Meals = new ArrayList<>();
        for (var p = 0; p < positions.length; p++)
            Meals.add(new Food(positions[p], AverageFoodCount));
    }
}
