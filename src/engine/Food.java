package engine;

import logic.NetSectorNet;
import logic.Sector;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

public class Food implements Serializable
{
    private int Count;
    private HashMap<Point, Integer> Pieces;
    private Point FoodPosition;
    public Sector parentSector;

    public boolean isEmpty = false;

    public final int PiecesRarity = 25;
    public final int MaxSize = 3;

    public Food(Point position, int count)
    {
        Random random = new Random();
        Pieces = new HashMap<>();
        for (var i = 0; i < count; i++)
        {
            var nutrition = random.nextInt(MaxSize) + 1;
            var piecePosition = new Point(-random.nextInt(PiecesRarity * 2) + position.x - PiecesRarity,
                    -random.nextInt(PiecesRarity * 2) + position.y - PiecesRarity);

            Pieces.put(piecePosition, nutrition);
        }

        FoodPosition = position;
        Count = count;
    }

    public HashMap<Point, Integer> getPieces()
    {
        return (HashMap<Point, Integer>)Pieces.clone();
    }

    public Point getPosition()
    {
        return FoodPosition;
    }

    public int getCount()
    {
        return Count;
    }

    public int destroyPiece(Point position)
    {
        var fat = Pieces.get(position);
        Pieces.remove(position);

        if (Pieces.isEmpty())
            isEmpty = true;

        return fat;
    }

    public Sector getSector(NetSectorNet net)
    {
        return net.sectors[FoodPosition.x / Sector.size.y][FoodPosition.y / Sector.size.y];
    }
}
