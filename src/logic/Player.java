package logic;

import java.awt.*;
import java.io.Serializable;

public class Player extends Creature implements Serializable
{
    public Player(Point position, int speed, int agility, int fattiness)
    {
        super(position, speed, agility, fattiness);
    }
}
