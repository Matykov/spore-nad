package logic;

import engine.*;

import java.awt.*;
import java.util.ArrayList;

public class Sector
{
    public ArrayList<Creature> creatures;
    public ArrayList<Creature> bots;
    public ArrayList<Food> food;
    public Player player;

    public static final Point size = new Point(300, 300);
    public Point location;

    public Sector()
    {
        creatures = new ArrayList<>();
        bots = new ArrayList<>();
        food = new ArrayList<Food>();
    }

    public ArrayList<Creature> getCreatures()
    {
        if (player == null)
            return creatures;
        var l = ((ArrayList<Creature>)creatures.clone());
        l.add(player);
        return l;
    }

    public void removeCreature(Creature creature)
    {
        creatures.remove(creature);
    }

    public void removeFood(ArrayList<Food> removedFood)
    {
//        var removedFood = new ArrayList<Food>();
//        for (Food f: food)
//        {
//            if (f.isEmpty)
//                removedFood.add(f);
//        }
        food.removeAll(removedFood);
    }
}
