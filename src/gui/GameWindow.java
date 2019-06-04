package gui;

import creatureParts.CreaturePart;
import engine.*;
import logic.Game;
import logic.Sector;
import logic.ServerGame;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import java.util.LinkedList;

public abstract class GameWindow extends JPanel
{
    protected JFrame frame;
    protected Game game;
    protected KeyAdapter keyAdapter;
    protected Point MapShift;


    protected GameWindow()
    {
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        drawGame((Graphics2D)g);
    }
    protected abstract void drawSector(Graphics2D g, AffineTransform oldForm, Sector sector);
    protected abstract void drawPlayer(Graphics2D g);
    protected abstract void drawGame(Graphics2D g);

    protected int translateX(Sector sector, int sectorPosX)
    {
        return sectorPosX + sector.location.x;
    }

    protected int translateY(Sector sector, int sectorPosY)
    {
        return sectorPosY + sector.location.y;
    }

    protected void drawCreature(Graphics2D g, Creature creature, Sector sector)
    {
        var viewFattiness = (int)(creature.getFattiness() * game.scale);
        g.setColor(creature.getBodyColor());
        BufferedImage bodyImg = creature.getBody().getSkin();
        g.drawImage(bodyImg, translateX(sector, creature.sectorPosition.x - viewFattiness),
                translateY(sector, creature.sectorPosition.y - viewFattiness),
                viewFattiness * 2,
                viewFattiness * 2, null);
        var creatureParts = creature.getCreatureParts();
        for(var creaturePart:creatureParts)
        {
            var oldForm = g.getTransform();
            AffineTransform partsAT = (AffineTransform) (oldForm.clone());
            Point pos;
            Double angle;
            pos = new Point(translateX(sector, creature.sectorPosition.x),
                    translateY(sector, creature.sectorPosition.y));
            angle = creature.getDirection() + Math.PI;
            partsAT.rotate(angle, pos.x, pos.y);
            partsAT.translate(pos.x, pos.y);
            //g.drawOval(pos.x, pos.y, 7, 7);
            g.setTransform(partsAT);
            BufferedImage bi = creaturePart.getSkin();
            g.drawImage(bi,
                    (int)(-bi.getWidth() * game.scale),
                    (int)(-creature.getFattiness() * game.scale),
                    (int)(game.scale * creature.getFattiness()),
                    (int)(game.scale * creature.getFattiness()),
                    null);
            g.setTransform(oldForm);
        }
    }

    protected void drawProgressBar(Graphics g)
    {
        g.setColor(new Color(0x136B21));
        g.drawRect(frame.getWidth() / 4, 20, frame.getWidth() / 2, 30);

        var fullness = game.getPercentCompletion() > 1 ? 1 : game.getPercentCompletion();

        g.fillRect(frame.getWidth() / 4, 20, (int)(fullness * frame.getWidth() / 2), 30);
    }

    protected void drawFood(Graphics g, Sector sector)
    {
        LinkedList<Food> food = sector.food;

        g.setColor(new Color(39, 200, 32));

        for (var group = 0; group < food.size(); group++)
        {
            var pieces = food.get(group).getPieces();
            for (Point p: pieces.keySet())
            {
                g.fillOval(translateX(sector, p.x - pieces.get(p)),
                        translateY(sector, p.y - pieces.get(p)),
                        (int)(pieces.get(p) + 5),
                        (int)(pieces.get(p) + 5));
            }
        }

    }

    protected void drawEye(Graphics2D g, AffineTransform oldForm, Creature creature, Sector sector)
    {
        AffineTransform partsAT = (AffineTransform) (oldForm.clone());
        Point pos;
        Double angle;
        if (creature instanceof Player)
        {
            pos = new Point(frame.getWidth() / 2, frame.getHeight() / 2);
            angle = creature.getDirection();
        }
        else {
            pos = new Point(translateX(sector, creature.sectorPosition.x),
                    translateY(sector, creature.sectorPosition.y));
            angle = creature.getDirection() + Math.PI;
        }
        partsAT.rotate(angle, pos.x, pos.y);
        partsAT.translate(pos.x, pos.y);
        g.drawOval(pos.x, pos.y, 7, 7);
        g.setTransform(partsAT);
        try
        {
            BufferedImage bi = ImageIO.read(new File("src/skins/eye.png"));

            g.drawImage(bi,
                    (int)(-bi.getWidth() * game.scale),
                    (int)(-creature.getFattiness() * game.scale),
                    (int)(game.scale * creature.getFattiness()),
                    (int)(game.scale * creature.getFattiness()),
                    null);
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening player skin");
        }
        g.setTransform(oldForm);
    }

    protected void drawFlagella(Graphics2D g, AffineTransform oldForm, Creature creature, Sector sector)
    {
        AffineTransform partsAT = (AffineTransform) (oldForm.clone());
        Point pos = new Point(translateX(sector, creature.sectorPosition.x),
                translateY(sector, creature.sectorPosition.y));
        Double angle = creature.getDirection() + Math.PI;
        partsAT.rotate(angle, pos.x, pos.y);


        partsAT.translate(pos.x, pos.y);
        g.drawOval(pos.x, pos.y, 7, 7);
        g.setTransform(partsAT);
        try
        {
            long tick = Game.tick;
            if (tick % 2 == 0) {
                BufferedImage bi = ImageIO.read(new File("src/skins/flagella1.png"));
                g.drawImage(bi,
                        -bi.getWidth() + 10,
                        -creature.getFattiness() + 50,
                        (int)(game.scale * creature.getFattiness()),
                        (int)(game.scale * creature.getFattiness()),
                        null);
            }
            else
            {
                BufferedImage bi = ImageIO.read(new File("src/skins/flagella2.png"));
                g.drawImage(bi,
                        -bi.getWidth() + 10,
                        -creature.getFattiness() + 50,
                        (int)(game.scale * creature.getFattiness()),
                        (int)(game.scale * creature.getFattiness()),
                        null);
            }


        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening player skin");
        }
        g.setTransform(oldForm);
    }

    protected void drawSpike(Graphics2D g, AffineTransform oldForm, Creature creature, Sector sector)
    {
        AffineTransform partsAT = (AffineTransform) (oldForm.clone());
        Point pos;
        Double angle;
        if (creature instanceof Player)
        {
            pos = new Point(frame.getWidth() / 2, frame.getHeight() / 2);
            angle = creature.getDirection();
        }
        else {
            pos = new Point(translateX(sector, creature.sectorPosition.x),
                    translateY(sector, creature.sectorPosition.y));
            angle = creature.getDirection() + Math.PI;
        }
        partsAT.rotate(angle, pos.x, pos.y);
        partsAT.translate(pos.x, pos.y);
        g.drawOval(pos.x, pos.y, 7, 7);
        g.setTransform(partsAT);
        try
        {
            BufferedImage bi = ImageIO.read(new File("src/skins/spikeRight.png"));
            g.drawImage(bi,
                    -bi.getWidth() + 50,
                    -creature.getFattiness(),
                    (int)(game.scale * creature.getFattiness()),
                    (int)(game.scale * creature.getFattiness()),
                    null);

        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening spike skin");
        }
        try
        {
            BufferedImage bi = ImageIO.read(new File("src/skins/spikeLeft.png"));
            g.drawImage(bi,
                    -bi.getWidth() - 15,
                    -creature.getFattiness(),
                    (int)(game.scale * creature.getFattiness()),
                    (int)(game.scale * creature.getFattiness()),
                    null);

        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening spike skin");
        }
        g.setTransform(oldForm);
    }

    protected void drawBackground(Graphics2D g, Sector sector)
    {
        BufferedImage bi;
        try{
            bi = ImageIO.read(new File("src/skins/world/Sector.png"));
            g.drawImage(bi,
                    (int)(sector.location.x ),
                    (int)(sector.location.y ),
                    (int)(game.getSectorNet().sectorSize.width),
                    (int)(game.getSectorNet().sectorSize.height),
                    null);
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening background skin");
        }
    }


}
