package engine;

import creatureParts.Body;
import creatureParts.CreaturePart;
import logic.NetSectorMap;
import logic.Sector;

import java.awt.*;
import java.io.Serializable;
import java.util.ArrayList;

public class Creature implements Serializable
{
    public boolean IsDead = false;
    private int Speed;
    private int Agility;
    protected int Fattiness;
    public int Damage;
    public Sector parentSector;
    protected ArrayList<CreaturePart> creatureParts = new ArrayList<>();

    public Body getBody()
    {
        return body;
    }

    protected Body body;
    protected Color bodyColor = new Color(0, 150, 200);


    public ArrayList<CreaturePart> getCreatureParts()
    {
        return creatureParts;
    }

    public Color getBodyColor()
    {
        return bodyColor;
    }

    public Point absPosition;
    public Point sectorPosition;
    private double Direction;

    public Creature(Point position, int speed, int agility, int fattiness)
    {
        sectorPosition = position;
        Speed = speed;
        Agility = agility;
        Fattiness = fattiness;
        Direction = 0.0;
        this.creatureParts.add(new creatureParts.Eye());


        //recountMapLocation();
    }

    public Creature()
    {
        sectorPosition = new Point(50, 50);
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
    public Point getSectorPosition() {
        return sectorPosition;
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

//    private void recountMapLocation()
//    {
//
//
//        double dist = sectorPosition.distance(0, 0);
//        double origAngle = Math.acos(sectorPosition.x / dist);
//
//        int x = (int)(dist * Math.cos(-origAngle - Direction + Math.PI / 2));
//        int y = (int)(dist * Math.sin(-origAngle - Direction + Math.PI / 2));
//
//        MapLocation = new Point(y - Fattiness, x - Fattiness);
//    }

    public Point move(int shift)
    {
        Point newShift = new Point((int)(-shift * Math.abs((Math.sin(Direction))) * Math.signum(Math.cos(Direction + 3 * Math.PI / 2))),
                (int)(-shift * Math.abs((Math.cos(Direction))) * Math.signum(Math.sin(Direction + 3 * Math.PI / 2))));

        sectorPosition.x += newShift.x;
        sectorPosition.y += newShift.y;

        return newShift;
    }
    public Sector getSector(NetSectorMap net)
    {
        if (sectorPosition.x >= net.sectorSize.width * NetSectorMap.netSize - 1)
            sectorPosition.x = 2;
        if (sectorPosition.x <= 1)
            sectorPosition.x = net.sectorSize.width * NetSectorMap.netSize - 1;
        if (sectorPosition.y >= net.sectorSize.height * NetSectorMap.netSize - 1)
            sectorPosition.y = 2;
        if (sectorPosition.y <= 1)
            sectorPosition.y = net.sectorSize.height * NetSectorMap.netSize - 1;

        return net.sectors[sectorPosition.x / net.sectorSize.width][sectorPosition.y / net.sectorSize.height];
    }

}
