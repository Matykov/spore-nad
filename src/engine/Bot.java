package engine;

import creatureParts.Body;
import creatureParts.Eye;
import creatureParts.Flagella;
import creatureParts.Spike;

import java.awt.*;
import java.util.Random;

public class Bot extends Creature
{
    public Bot(Point position, int speed, int agility, int fattiness)
    {
        super(position, speed, agility, fattiness);
        Random r = new Random();
        this.body = new Body();
        this.creatureParts.add(new Eye());
        this.creatureParts.add(new Flagella());
        this.creatureParts.add(new Spike(0, false));
        this.creatureParts.add(new Spike(1, false));
    }
}
