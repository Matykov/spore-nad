package logic;

import engine.*;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;

public class SectorNet implements Serializable
{
    public static final int size = 3;

    public Sector[][] sectors;
    private Level level;
    public final Point sectorSize;


    public SectorNet(Level level)
    {
        sectors = new Sector[size][size];
        this.level = level;

        for (int j = 0; j < size; j++)
        {
            for (int i = 0; i < size; i++)
            {
                sectors[j][i] = level.generateSector();
            }
        }

        sectors[size / 2][size / 2].player = level.getPlayer();
        sectorSize = Sector.size;
    }


    public void moveFocusLeft(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int j = 0; j < size; j++)
        {
            for (int i = 0; i < size; i++)
            {
                if (i == 0)
                    sectors[j][i] = level.generateSector();
                else
                    sectors[j][i] = sectors[j][i-1];

            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public void moveFocusRight(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int j = 0; j < 3; j++)
        {
            for (int i = 0; i < 3; i++)
            {
                if (i == 2)
                    sectors[j][i] = level.generateSector();
                else
                    sectors[j][i] = sectors[j][i + 1];

            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public void moveFocusUp(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int j = 0; j < 3; j++)
        {
            for (int i = 0; i < 3; i++)
            {
                if (j == 2)
                    sectors[j][i] = level.generateSector();
                else
                    sectors[j][i] = sectors[j+1][i];

            }
        }

        sectors[size / 2][size / 2].player = player;
    }

    public void moveFocusDown(Player player)
    {
        sectors[size / 2][size / 2].player = null;

        for (int j = 0; j < 3; j++)
        {
            for (int i = 0; i < 3; i++)
            {
                if (j == 0)
                    sectors[j][i] = level.generateSector();
                else
                    sectors[j][i] = sectors[j-1][i];

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

        return (Sector[]) l.toArray();
    }
}
