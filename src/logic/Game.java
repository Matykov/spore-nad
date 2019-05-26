package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

public class Game implements Serializable
{
    protected Player player;
    protected Level level;
    protected int progressBar;
    protected SectorNet curSectors;

    public static long tick = 0;
    private boolean isLevelCompleted = false;


    public Game(Level level)
    {
        this.level = level;
        player = level.getPlayer();
        progressBar = 0;

        curSectors = new SectorNet(level);
    }

    protected Game()
    {
        this.curSectors = new NetSectorNet(4);
    }

    public Player getPlayer()
    {
        return player;
    }

    public Level getLevel() {
        return level;
    }


    public void observeCreatures()
    {
        for (var j = 0; j < SectorNet.size; j++)
        {
            for (var i = 0; i < SectorNet.size; i++)
            {
                var curSec = curSectors.sectors[j][i];
                for (Creature creature: curSec.getCreatures())
                {

                    creature.absPosition = new Point(creature.sectorPosition.x + curSec.location.x,
                            creature.sectorPosition.y + curSec.location.y);

                    //food eating
                    eatFood(curSec, creature);

                    //creature eating
                    eatCreatures(curSec, creature);

                    //move bot
                    if (creature instanceof Bot) {
                        if (tick == 10) {
                            moveBot((Bot) creature);
                            tick = 0;
                        }
                    }

                    //observe sector position
                    observeSectorPosition(i, j, creature);
                    tick++;
                }
            }
        }
    }

    private void eatCreatures(Sector curSec, Creature creature)
    {
        for (Creature preyCreature: curSec.getCreatures())
        {
            if (creature.getFattiness() > preyCreature.getFattiness() &&
                    dist(creature.sectorPosition, preyCreature.sectorPosition) <=
                            creature.getFattiness() - preyCreature.getFattiness())
            {
                var nutrition = creature.eat(preyCreature);
                if (creature instanceof Player)
                {
                    progressBar += nutrition;
                }
            }
        }
    }

    private void eatFood(Sector curSec, Creature creature)
    {
        var removedFood = new ArrayList<Food>();
        for (Food food : curSec.food)
        {
            var keys = food.getPieces().keySet();
            for (Point piecePosition : keys)
            {
                if (dist(creature.sectorPosition, piecePosition) <= creature.getFattiness() - food.MaxSize)
                {
                    int nutrition = food.destroyPiece(piecePosition);
                    creature.putOnWeight(nutrition);
                    if(food.isEmpty)
                        removedFood.add(food);
                    if (creature instanceof Player)
                    {
                        progressBar += nutrition;
                    }
                }
            }
        }
        curSec.removeFood(removedFood);
    }

    private void moveBot(Bot bot)
    {
        Random random = new Random();

        int changeAnglePossibility = random.nextInt(8);

        if (changeAnglePossibility == 0)
        {
            double angle = random.nextInt(17) * Math.PI / 8;
            bot.turn(angle);
        }

        bot.move(5);
    }

    public void tick()
    {
        tick++;
    }

    private void observeSectorPosition(int curXNet, int curYNet, Creature creature)
    {
        var creaturePosX = creature.sectorPosition.x;
        var creaturePosY = creature.sectorPosition.y;

        var curSec = curSectors.sectors[curYNet][curXNet];
        var isPlayer = curSec.player == creature;

        if (creaturePosX < 0)
        {
            if (isPlayer) {
                curSectors.moveFocusLeft(player);
            }
            else {
                //curSec.removeCreature(creature);
                //creature.die();
                var newX = curXNet - 1 >= 0 ? curXNet - 1 : SectorNet.size - 1;
                //curSectors.sectors[curYNet][newX].creatures.add(creature);
            }

            creature.sectorPosition.x = Sector.size.x;
        }
        else if (creaturePosX > curSectors.sectorSize.x)
        {
            if (isPlayer) {
                curSectors.moveFocusRight(player);
            }
            else
            {
                //curSec.removeCreature(creature);
                //creature.die();
                var newX = curXNet + 1 < SectorNet.size ? curXNet + 1 : 0;
                //curSectors.sectors[curYNet][newX].creatures.add(creature);
            }

            creature.sectorPosition.x = 0;
        }
        if (creaturePosY < 0)
        {
            if (isPlayer) {
                curSectors.moveFocusUp(player);
            }
            else
            {
                //curSec.removeCreature(creature);
                //creature.die();
                var newY = curYNet - 1 >= 0 ? curYNet - 1 : SectorNet.size - 1;
                //curSectors.sectors[newY][curXNet].creatures.add(creature);
            }

            creature.sectorPosition.y = Sector.size.y;
        }
        else if (creaturePosY > curSectors.sectorSize.y)
        {
            if (isPlayer) {
                curSectors.moveFocusDown(player);
            }
            else
            {
                //curSec.removeCreature(creature);
                //creature.die();
                var newY = curYNet + 1 < SectorNet.size ? curYNet + 1 : 0;
                //curSectors.sectors[newY][curXNet].creatures.add(creature);
            }
            creature.sectorPosition.y = 0;
        }

    }

    private double dist(Point p1, Point p2)
    {
        return Math.sqrt(Math.pow(p1.x - p2.x, 2) + Math.pow(p1.y - p2.y, 2));
    }

    public void update()
    {
        observeCreatures();
        if (getPercentCompletion() >= 1)
        {
            //System.out.println("level completed");
            isLevelCompleted = true;
        }
    }

    public double getPercentCompletion()
    {
        return (double) progressBar / level.getCompletedFattiness();
    }

    public boolean isLevelCompleted()
    {
        return isLevelCompleted;
    }

    public SectorNet getSectorNet()
    {
        return curSectors;
    }

}
