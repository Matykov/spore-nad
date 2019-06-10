package engine;

import java.io.Serializable;

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

    public Player getPlayer()
    {
        return player;
    }

    public int getCompletedFattiness()
    {
        return completedFattiness;
    }
}
