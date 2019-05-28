package engine;


import logic.Sector;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Level implements Serializable
{
    private Player player;
    public final int completedFattiness;

    public final int avgPiecesCount = 7;
    public final int avgSectorFoodCount = 2;
    public final int avgSectorBotCount = 1;

    protected Level()
    {
        this.completedFattiness = 1000;
    }
    public Level(Player player, int completedFattiness)
    {
        this.player = player;
        this.completedFattiness = completedFattiness;
    }

//    public Sector generateSector()
//    {
//        var s = new Sector();
//
//        Random r = new Random();
//        var curFoodCount = generateCurValue(r, avgSectorFoodCount);
//        var curSectorBotCount = generateCurValue(r, avgSectorBotCount);
//
//
//        //generate sector food
//        for (var i = 0; i < curFoodCount; i++)
//        {
//            var position = new Point(r.nextInt(s.size.x), r.nextInt(s.size.y));
//            var count = generateCurValue(r, avgPiecesCount);
//
//            s.food.add(new Food(position, count));
//        }
//
//        //generate sector bots
//        for (var i = 0; i < curSectorBotCount; i++)
//        {
//            var position = new Point(r.nextInt(s.size.x), r.nextInt(s.size.y));
//            var speed = generateCurValue(r, player.getSpeed());
//            var agility = generateCurValue(r, player.getAgility());
//            var fattiness = generateCurValue(r, player.getFattiness());
//
//            s.creatures.add(new Bot(position, speed, agility, fattiness));
//            System.out.println("added bot " + position.toString());
//        }
//
//        return s;
//    }
//
//    private int generateCurValue(Random r, int num)
//    {
//        var inaccuracy = num / 3 > 1 ? num / 3 : 1;
//        return r.nextInt(2 * inaccuracy) + num - inaccuracy;
//    }

    public Player getPlayer()
    {
        return player;
    }

    public int getCompletedFattiness()
    {
        return completedFattiness;
    }
}
