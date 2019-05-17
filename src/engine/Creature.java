package engine;

import java.awt.*;
import java.io.Serializable;

public class Creature implements Serializable
{
    public boolean IsDead = false;
    private int Speed;
    private int Agility;
    private int Fattiness;
    public int Damage;

    private Point absolutePosition;
    private Point sectorPosition;
    public Point MapLocation;
    private double Direction;

    public Creature(Point position, int speed, int agility, int fattiness)
    {
        absolutePosition = position;
        Speed = speed;
        Agility = agility;
        Fattiness = fattiness;
        Direction = 0;

        recountMapLocation();
    }

    public Creature()
    {
        absolutePosition = new Point(50, 50);
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
    public Point getAbsolutePosition() {
        return absolutePosition;
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



    public void die()
    {
        IsDead = true;
    }

    public void eat(Food food, Point position)
    {
        for (var i = 0; i < food.getCount(); i++)
        {
            putOnWeight(food.getPieces().get(position));
        }
    }

    public int eat(Creature prey)
    {
        if (prey.getFattiness() < Fattiness)
        {
            putOnWeight(prey.getFattiness() - 3);
            prey.die();
            return prey.getFattiness() - 3;
        }
        return 0;
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

    public void turn(double angle)
    {
        Direction += angle;

        if (Math.abs(Direction) >= Math.PI * 2)
        {
            Direction -= Math.signum(Direction) * Math.PI * 2;
        }
    }

    public double getDirection()
    {
        return Direction;
    }

    private void recountMapLocation()
    {
        double dist = absolutePosition.distance(0, 0);
        double origAngle = Math.acos(absolutePosition.x / dist);

        int x = (int)(dist * Math.cos(-origAngle - Direction + Math.PI / 2));
        int y = (int)(dist * Math.sin(-origAngle - Direction + Math.PI / 2));

        MapLocation = new Point(y - Fattiness, x - Fattiness);
    }

    public Point move(int shift)
    {
        Point newShift = new Point((int)(-shift * Math.abs((Math.sin(Direction))) * Math.signum(Math.cos(Direction + 3 * Math.PI / 2))),
                (int)(-shift * Math.abs((Math.cos(Direction))) * Math.signum(Math.sin(Direction + 3 * Math.PI / 2))));

        absolutePosition.x += newShift.x;
        absolutePosition.y += newShift.y;

        return newShift;
    }

}
