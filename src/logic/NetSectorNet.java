package logic;

import engine.Food;
import engine.NetPlayer;

import java.awt.*;
import java.util.ArrayList;
import java.util.Random;

public class NetSectorNet extends SectorNet {
    private ArrayList<NetPlayer> players;
    public static int netSize = 13;
    public static final int avgPiecesCount = 7;
    private static final int avgSectorFoodCount = 2;
    private static final int avgSectorBotCount = 0;
    private ArrayList<Food> foods = new ArrayList<Food>();

    public NetSectorNet(int nPlayerCount) {
        sectors = new Sector[netSize][netSize];
        for (int i = 0; i < netSize; i++)
        {
            for (int j = 0; j < netSize; j++)
            {
                sectors[i][j] = generateSector(i ,j);
                sectors[i][j].location = new Point(i, j);
            }
        }
        players = new ArrayList<>();
        for(int i=0; i<nPlayerCount; i++)
        {
            int x = nPlayerCount - i;
            int y = nPlayerCount - i;
            var playerPoint = new Point((Sector.size.x/2)*x, (Sector.size.y/2)*y);
            var player = new NetPlayer(playerPoint,  100, 50, 25, i);
            //sectors[x][y].creatures.add(player);
            player.parentSector = player.getSector(this);
            players.add(player);
        }
    }

    public ArrayList<NetPlayer> getPlayers()
    {
        return this.players;
    }

    private int generateCurValue(Random r, int num) {
        var inaccuracy = num / 3 > 1 ? num / 3 : 1;
        return r.nextInt(2 * inaccuracy) + num - inaccuracy;
    }

    public ArrayList<Food> getFoods(){return foods;}

    private Sector generateSector(int i, int j)
    {
        var s = new Sector();

        Random r = new Random();
        var curFoodCount = generateCurValue(r, avgSectorFoodCount);

        //generate sector food
        for (var ii = 0; ii < curFoodCount; ii++) {
            var position = new Point(Sector.size.x * i + r.nextInt(Sector.size.x),
                    Sector.size.y * j + r.nextInt(Sector.size.y));
            var count = generateCurValue(r, avgPiecesCount);
            var food = new Food(position, count);
            food.parentSector = s;
            foods.add(food);
        }
        return s;
    }

}
