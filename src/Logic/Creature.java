package Logic;

import java.awt.*;
import java.util.Random;

public class Creature
{
    private int Speed;
    private int Agility;
    private Point Position;
    private int Fattiness;

    public Creature(Point position, int speed, int agility, int fattiness)
    {
        Position = position;
        Speed = speed;
        Agility = agility;
        Fattiness = fattiness;
    }

    public Creature() { }

    public int getSpeed()
    {
        return Speed;
    }
    public int getAgility() {
        return Agility;
    }
    public Point getPosition() {
        return Position;
    }

    public void changeSpeed(int newSpeed)
    {
        Speed = newSpeed;
    }
    public void changeAgility(int newAgility)
    {
        Agility = newAgility;
    }

    public void move(Point newPosition)
    {
        Position = newPosition;
    }
    public void moveX(int shift)
    {
        Position.x += shift;
    }
    public void moveY(int shift)
    {
        Position.y += shift;
    }

    public Food[] die()
    {
        Random random = new Random();
        Food[] res = new Food[Fattiness];
        for (var i = 0; i < Fattiness; i++)
        {
            res[i] = new Food(random.nextInt(2) + 1);
        }

        return res;
    }
}
