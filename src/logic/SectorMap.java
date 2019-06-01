package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

public class SectorMap implements Serializable
{
    public static final int netSize = 3;
    public Dimension sectorSize;

    public Sector[][] sectors;
    private Level level;

    protected SectorMap() {
        sectorSize = new Dimension(300, 300);
    }


    public SectorMap(Level level)
    {
        sectorSize = new Dimension(300, 300);
        sectors = new Sector[netSize][netSize];
        this.level = level;

        for (int j = 0; j < netSize; j++)
        {
            for (int i = 0; i < netSize; i++)
            {
                sectors[j][i] = generateSector(level.getPlayer());
                sectors[j][i].location = new Point((i - netSize / 2) * sectorSize.width,
                        (j - netSize / 2) * sectorSize.height);
            }
        }

        sectors[netSize / 2][netSize / 2].player = level.getPlayer();
    }

    public Sector generateSector(Player player)
    {
        var s = new Sector();

        Random r = new Random();
        var curFoodCount = generateCurValue(r, level.avgSectorFoodCount);
        var curSectorBotCount = generateCurValue(r, level.avgSectorBotCount);


        //generate sector food
        for (var i = 0; i < curFoodCount; i++)
        {
            var position = new Point(r.nextInt(sectorSize.width), r.nextInt(sectorSize.height));
            var count = generateCurValue(r, level.avgPiecesCount);

            s.food.add(new Food(position, count));
        }

        //generate sector bots
        for (var i = 0; i < curSectorBotCount; i++)
        {
            var position = new Point(r.nextInt(sectorSize.width), r.nextInt(sectorSize.height));
            var speed = generateCurValue(r, player.getSpeed());
            var agility = generateCurValue(r, player.getAgility());
            var fattiness = generateCurValue(r, player.getFattiness());

            s.creatures.add(new Bot(position, speed, agility, fattiness));
            System.out.println("added bot " + position.toString());
        }

        return s;
    }

    private int generateCurValue(Random r, int num)
    {
        var inaccuracy = num / 3 > 1 ? num / 3 : 1;
        return r.nextInt(2 * inaccuracy) + num - inaccuracy;
    }

    public void moveFocusLeft(Player player)
    {
        sectors[netSize / 2][netSize / 2].player = null;

        for (var y = 0; y < netSize; y++) {
            for (var x = netSize - 1; x > -1; x--) {
                if (x == 0) {
                    var newPos = new Point(sectors[y][0].location.x - sectorSize.width,
                            sectors[y][0].location.y);
                    sectors[y][x] = generateSector(player);
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y][x - 1];
                }
            }
        }

        sectors[netSize / 2][netSize / 2].player = player;
        System.out.println("moveFocusLeft");
    }

    public void moveFocusRight(Player player)
    {
        sectors[netSize / 2][netSize / 2].player = null;

        for (int y = 0; y < netSize; y++)
        {
            for (int x = 0; x < netSize; x++)
            {
                if (x == netSize - 1) {
                    var newPos = new Point(sectors[y][x].location.x + sectorSize.width,
                            sectors[y][x].location.y);
                    sectors[y][x] = generateSector(player);
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y][x + 1];
                }

            }
        }

        sectors[netSize / 2][netSize / 2].player = player;
        System.out.println("moveFocusRight");
    }

    public void moveFocusUp(Player player)
    {
        sectors[netSize / 2][netSize / 2].player = null;

        for (int y = netSize - 1; y > -1; y--)
        {
            for (int x = 0; x < netSize; x++)
            {
                if (y == 0) {
                    var newPos = new Point(sectors[y][x].location.x, sectors[y][x].location.y
                            - sectorSize.height);
                    sectors[y][x] = generateSector(player);
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y - 1][x];
                }

            }
        }

        sectors[netSize / 2][netSize / 2].player = player;
        System.out.println("moveFocusUp");
    }

    public void moveFocusDown(Player player)
    {
        sectors[netSize / 2][netSize / 2].player = null;

        for (int y = 0; y < netSize; y++)
        {
            for (int x = 0; x < netSize; x++)
            {
                if (y == netSize - 1) {
                    var newPos = new Point(sectors[y][x].location.x,
                            sectors[y][x].location.y  + sectorSize.height);
                    sectors[y][x] = generateSector(player);
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y + 1][x];
                }

            }
        }

        sectors[netSize / 2][netSize / 2].player = player;
        System.out.println("moveFocusDown");
    }

    public ArrayList<Sector> getSectors()
    {
        var l = new ArrayList<Sector>();
        for (var j = 0; j < netSize; j++)
        {
            l.addAll(Arrays.asList(sectors[j]));
        }

        return l;
    }
}
