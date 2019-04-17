package logic;

import java.awt.*;
import java.util.ArrayList;
import java.util.Arrays;

public class Level
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

    public ArrayList<Creature> getCreatures()
    {
        return Creatures;
    }

    public Player getPlayer()
    {
        return Player;
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
