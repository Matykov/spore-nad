//package tests;
//
//import engine.*;
//import logic.NetSectorMap;
//import logic.Sector;
//
//import java.awt.*;
//import java.util.Random;
//
//public class EngineTests
//{
//
//
//    public static boolean test_creature_eat()
//    {
//        var c = new Creature(new Point(1, 1), 1, 1, 1);
//        c.eat(new Creature(new Point(2, 2), 1, 1, 1));
//        return true;
//    }
//    public void getDamage(Creature enemy)
//    {
//        if (enemy.Damage >= Fattiness)
//        {
//            die();
//        }
//        else
//        {
//            Fattiness -= enemy.Damage;
//            enemy.putOnWeight(enemy.Damage - 3);
//        }
//    }
//
//    public void putOnWeight(int fat)
//    {
//        Fattiness += fat;
//    }
//
//    public void turn(double angle)
//    {
//        Direction += angle;
//
//        if (Math.abs(Direction) >= Math.PI * 2)
//        {
//            Direction -= Math.signum(Direction) * Math.PI * 2;
//        }
//    }
//
//    public double getDirection()
//    {
//        return Direction;
//    }
//
////    private void recountMapLocation()
////    {
////
////
////        double dist = sectorPosition.distance(0, 0);
////        double origAngle = Math.acos(sectorPosition.x / dist);
////
////        int x = (int)(dist * Math.cos(-origAngle - Direction + Math.PI / 2));
////        int y = (int)(dist * Math.sin(-origAngle - Direction + Math.PI / 2));
////
////        MapLocation = new Point(y - Fattiness, x - Fattiness);
////    }
//
//    public Point move(int shift)
//    {
//        Point newShift = new Point((int)(-shift * Math.abs((Math.sin(Direction))) * Math.signum(Math.cos(Direction + 3 * Math.PI / 2))),
//                (int)(-shift * Math.abs((Math.cos(Direction))) * Math.signum(Math.sin(Direction + 3 * Math.PI / 2))));
//
//        sectorPosition.x += newShift.x;
//        sectorPosition.y += newShift.y;
//
//        return newShift;
//    }
//    public Sector getSector(NetSectorMap net)
//    {
//        if (sectorPosition.x >= Sector.netSize.x * NetSectorMap.netSize)
//            sectorPosition.x = 0;
//        if (sectorPosition.x <= 0)
//            sectorPosition.x = Sector.netSize.x * NetSectorMap.netSize;
//        if (sectorPosition.y >= Sector.netSize.y * NetSectorMap.netSize)
//            sectorPosition.y = 0;
//        if (sectorPosition.y <= 0)
//            sectorPosition.y = Sector.netSize.y * NetSectorMap.netSize;
//
//        return net.sectors[sectorPosition.x / Sector.netSize.x][sectorPosition.y / Sector.netSize.y];
//    }
//
//    public int destroyPiece(Point position)
//    {
//        var fat = Pieces.get(position);
//        Pieces.remove(position);
//
//        if (Pieces.isEmpty())
//            isEmpty = true;
//
//        return fat;
//    }
//
//    public Sector getSector(NetSectorMap net)
//    {
//        return net.sectors[FoodPosition.x / Sector.netSize.y][FoodPosition.y / Sector.netSize.y];
//    }
//
//    public Sector generateSector()
//    {
//        var s = new Sector();
//
//        Random r = new Random();
//        var curFoodCount = generateCurValue(r, avgSectorFoodCount);
//        var curSectorBotCount = generateCurValue(r, avgSectorBotCount);
//
//
//        //generate sector food
//        for (var i = 0; i < curFoodCount; i++)
//        {
//            var position = new Point(r.nextInt(s.netSize.x), r.nextInt(s.netSize.y));
//            var count = generateCurValue(r, avgPiecesCount);
//
//            s.food.add(new Food(position, count));
//        }
//
//        //generate sector bots
//        for (var i = 0; i < curSectorBotCount; i++)
//        {
//            var position = new Point(r.nextInt(s.netSize.x), r.nextInt(s.netSize.y));
//            var speed = generateCurValue(r, player.getSpeed());
//            var agility = generateCurValue(r, player.getAgility());
//            var fattiness = generateCurValue(r, player.getFattiness());
//
//            s.creatures.add(new Bot(position, speed, agility, fattiness));
//            System.out.println("added bot " + position.toString());
//        }
//
//        return s;
//    }
//
//    private int generateCurValue(Random r, int num)
//    {
//        var inaccuracy = num / 3 > 1 ? num / 3 : 1;
//        return r.nextInt(2 * inaccuracy) + num - inaccuracy;
//    }
//
//    public void activate(){
//        active = true;
//        live = System.currentTimeMillis();
//    }
//    public void deactivate(){
//        active = false;
//    }
//    public void checkLive(){
//        if(live >= ttl)
//            deactivate();
//    }
//}
