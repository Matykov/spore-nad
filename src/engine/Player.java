package engine;

import java.awt.*;
import java.io.Serializable;

public class Player extends Creature implements Serializable
{
    public Player(Point position, int speed, int agility, int fattiness)
    {
        super(true, position, speed, agility, fattiness);
    }


}
