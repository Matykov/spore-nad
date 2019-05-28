package gui;

import engine.Creature;
import engine.Food;
import engine.NetPlayer;
import logic.*;
import netParts.old.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;


public class ClientClientWindow extends ClientWindow {
    public ClientClientWindow(JFrame frame, ClientGame game)
    {
        MapShift = new Point(frame.getWidth() / 2 - game.getDummyPlayer().sectorPosition.x,
                frame.getHeight() / 2 - game.getDummyPlayer().sectorPosition.y);
        this.frame = frame;
        this.game = game;
        this.keyAdapter = new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    var s = ((ClientGame)game).getDummyPlayer().move(-5);
                    MapShift.x -= s.x;
                    MapShift.y -= s.y;
                    //game.update();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    var s = ((ClientGame)game).getDummyPlayer().move(5);
                    MapShift.x -= s.x;
                    MapShift.y -= s.y;
                    //game.update();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    ((ClientGame)game).getDummyPlayer().turn(Math.PI / 8);
                    //game.update();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    ((ClientGame)game).getDummyPlayer().turn(-Math.PI / 8);
                    //game.update();
                }
                repaint();
            }
        };
        this.frame.addKeyListener(keyAdapter);
    }

    @Override
    protected void drawGame(Graphics2D g)
    {
        AffineTransform origXform = g.getTransform();
        var sectors = new ArrayList<>(game.getSectorNet().getSectors());
        var playerSector = ((ClientGame)game).getDummyPlayer().getSector(((ClientGame) game).getSectorNet());
        for (var sector: sectors)
        {
            if((sector.location.x <= playerSector.location.x + 1 && sector.location.x >= playerSector.location.x - 1)
                    && sector.location.y <= playerSector.location.y + 1 && sector.location.y >= playerSector.location.y - 1)
                drawSector(g, origXform, sector);
        }

        if (!(game instanceof ClientGame) &&!(game instanceof ServerGame))
            drawProgressBar(g);

        drawPlayer(g);
        //drawFlagella(g, origXform, game.getPlayer(), null);
        drawEye(g, origXform, game.getPlayer(), null);
        //drawSpike(g, origXform, game.getPlayer(), null);

    }

    @Override
    protected void drawSector(Graphics2D g, AffineTransform oldForm, Sector sector)
    {

        AffineTransform mapAT = (AffineTransform) (oldForm.clone());
        mapAT.translate(MapShift.x, MapShift.y);
        g.setTransform(mapAT);
        drawBackground(g, sector);
        drawFood(g, sector);

        var creatures = ((ClientGame)game).getPlayers();
        for (Creature creature : creatures) {
            if(((NetPlayer)creature).isActive())
                drawCreature(g, creature);
            //drawEye(g, mapAT, creature, sector);
        }

        g.setTransform(oldForm);
    }

    @Override
    protected void drawBackground(Graphics2D g, Sector sector)
    {
        BufferedImage bi;
        try{
            bi = ImageIO.read(new File("src/skins/Sector.png"));
            g.drawImage(bi,sector.location.x * Sector.size.x, sector.location.y * Sector.size.y, null);
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening background skin");
        }
    }
    @Override
    protected void drawFood(Graphics g, Sector sector)
    {
        ArrayList<Food> foods = ((NetSectorNet)game.getSectorNet()).getFoods();

        g.setColor(new Color(39, 200, 32));
        ArrayList<Food> food = new ArrayList<Food>();
        for(var foody:foods){
            if (foody.parentSector.equals(sector))
                food.add(foody);
        }
        for (var group = 0; group < food.size(); group++)
        {
            var pieces = food.get(group).getPieces();
            for (Point p: pieces.keySet())
            {
                g.fillOval(translateX(sector, p.x - pieces.get(p)), translateY(sector, p.y - pieces.get(p)), pieces.get(p) + 5, pieces.get(p) + 5);
                //g.drawLine(p.x - pieces.get(p), p.y - pieces.get(p), game.getPlayer().getAbsolutePosition().x, game.getPlayer().getAbsolutePosition().y);
            }
        }

    }

    protected void drawCreature(Graphics g, Creature creature)
    {
        var sector = creature.getSector((NetSectorNet) game.getSectorNet());
        g.setColor(new Color(0, 150, 200));
        g.fillOval(translateX(sector, creature.sectorPosition.x - creature.getFattiness()),
                translateY(sector, creature.sectorPosition.y - creature.getFattiness()),
                creature.getFattiness() * 2,
                creature.getFattiness() * 2);
    }


}
