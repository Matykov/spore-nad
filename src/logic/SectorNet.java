package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SectorNet implements Serializable
{
    public static final int size = 3;
    public final Point sectorSize;

    public Sector[][] sectors;
    private Level level;

    protected SectorNet() {
        sectorSize = Sector.size;
    }


    public SectorNet(Level level)
    {
        sectors = new Sector[size][size];
        this.level = level;
        sectorSize = Sector.size;

        for (int j = 0; j < size; j++)
        {
            for (int i=0; i < size; i++)
            {
                sectors[j][i] = level.generateSector();
                sectors[j][i].location = new Point((i - size / 2) * sectorSize.x, (j - size / 2) * sectorSize.y);
            }
        }

        sectors[size / 2][size / 2].player = level.getPlayer();
    }


    public void moveFocusLeft(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (var y = 0; y < size; y++) {
            for (var x = size - 1; x > -1; x--) {
                if (x == 0) {
                    var newPos = new Point(sectors[y][0].location.x - Sector.size.x, sectors[y][0].location.y);
                    sectors[y][x] = level.generateSector();
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y][x - 1];
                }
            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public void moveFocusRight(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                if (x == size - 1) {
                    var newPos = new Point(sectors[y][x].location.x + Sector.size.x, sectors[y][x].location.y);
                    sectors[y][x] = level.generateSector();
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y][x + 1];
                }

            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public void moveFocusUp(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int y = size - 1; y > -1; y--)
        {
            for (int x = 0; x < size; x++)
            {
                if (y == 0) {
                    var newPos = new Point(sectors[y][x].location.x, sectors[y][x].location.y  - Sector.size.y);
                    sectors[y][x] = level.generateSector();
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y - 1][x];
                }

            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public void moveFocusDown(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int y = 0; y < size; y++)
        {
            for (int x = 0; x < size; x++)
            {
                if (y == size - 1) {
                    var newPos = new Point(sectors[y][x].location.x, sectors[y][x].location.y  + Sector.size.y);
                    sectors[y][x] = level.generateSector();
                    sectors[y][x].location = newPos;
                }
                else {
                    sectors[y][x] = sectors[y + 1][x];
                }

            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public Sector[] getSectors()
    {
        var l = new ArrayList<Sector>();
        for (var j = 0;  j < size; j++)
        {
            l.addAll(Arrays.asList(sectors[j]));
        }

        return l.toArray(new Sector[l.size()]);
    }
}
