package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedList;

public class Sector implements Serializable
{
    public LinkedList<Creature> creatures;
//    public ArrayList<Creature> bots;
    public LinkedList<Food> food;
    public Player player;

    public static final Point size = new Point(300, 300);
    public Point location;

    public Sector()
    {
        creatures = new LinkedList<>();
//        bots = new ArrayList<>();
        food = new LinkedList<Food>();
    }

    public LinkedList<Creature> getCreatures()
    {
        if (player == null)
            return creatures;
        var l = ((LinkedList)creatures.clone());
        if (player != null)
            l.add(player);
        return l;
    }

    public void removeCreature(ArrayList<Creature> creatures)
    {
        creatures.removeAll(creatures);
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
