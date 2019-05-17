package logic;

import engine.Food;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NetSectorNet extends SectorNet {
    private NetPlayer[] players;
    private static int netSize = 12;
    public static final int avgPiecesCount = 7;
    private static final int avgSectorFoodCount = 2;
    private static final int avgSectorBotCount = 0;

    public NetSectorNet(int nPlayerCount) {
        sectors = new Sector[netSize][netSize];
        for (int i = 0; i < netSize; i++)
        {
            for (int j = 0; j < netSize; j++)
            {
                sectors[i][j] = generateSector();
            }
        }
        players = new NetPlayer[nPlayerCount];
        for(int i=0; i<nPlayerCount; i++)
        {
            int x = nPlayerCount - i;
            int y = nPlayerCount - i;
            var playerPoint = new Point((Sector.size.x/2)*x, (Sector.size.y/2)*y);
            var player = new NetPlayer(playerPoint,  100, 50, 25, i);
            sectors[x][y].creatures.add(player);
            this.players[i] = (player);
        }
    }

    private int generateCurValue(Random r, int num) {
        var inaccuracy = num / 3 > 1 ? num / 3 : 1;
        return r.nextInt(2 * inaccuracy) + num - inaccuracy;
    }

    private Sector generateSector()
    {
        var s = new Sector();

        Random r = new Random();
        var curFoodCount = generateCurValue(r, avgSectorFoodCount);

        //generate sector food
        for (var i = 0; i < curFoodCount; i++) {
            var position = new Point(r.nextInt(s.size.x), r.nextInt(s.size.y));
            var count = generateCurValue(r, avgPiecesCount);

            s.food.add(new Food(position, count));
        }
        return s;
    }

}
