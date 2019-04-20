package logic;

import java.awt.*;
import java.io.Serializable;
import java.util.Random;

public class Creature implements Serializable
{
    private Random random = new Random();

    private int Speed;
    private int Agility;
    private Point Position;
    private int Fattiness;

    public int Damage;

    public Creature(Point position, int speed, int agility, int fattiness)
    {
        Position = position;
        Speed = speed;
        Agility = agility;
        Fattiness = fattiness;
    }

    public Creature()
    {
        Position = new Point(50, 50);
        Speed = 1;
        Agility = 1;
        Fattiness = 50;
    }

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
    public int getFattiness() {
        return Fattiness;
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

    public Food die()
    {
        return new Food(Position, Fattiness);
    }

    public void eat(Food food, Point position)
    {
        for (var i = 0; i < food.getCount(); i++)
        {
            putOnWeight(food.getPieces().get(position));
        }
    }

    public void getDamage(Creature enemy)
    {
        if (enemy.Damage >= Fattiness)
        {
            die();
        }
        else
        {
            Fattiness -= enemy.Damage;
            enemy.putOnWeight(enemy.Damage - 3);
        }
    }

    public void putOnWeight(int fat)
    {
        Fattiness += fat;
    }

}
