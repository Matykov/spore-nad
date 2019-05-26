package gui;

import engine.*;
import logic.Game;
import logic.Sector;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

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
    protected abstract void drawPlayer(Graphics g);
    protected abstract void drawGame(Graphics2D g);

    protected int translateX(Sector sector, int sectorPosX)
    {
        return sectorPosX + sector.location.x;
    }

    protected int translateY(Sector sector, int sectorPosY)
    {
        return sectorPosY + sector.location.y;
    }

    protected void drawCreature(Graphics g, Creature creature, Sector sector)
    {
        g.setColor(new Color(0, 150, 200));
        g.fillOval(translateX(sector, creature.sectorPosition.x - creature.getFattiness()),
                translateY(sector, creature.sectorPosition.y - creature.getFattiness()),
                creature.getFattiness() * 2,
                creature.getFattiness() * 2);
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
        ArrayList<Food> food = sector.food;

        g.setColor(new Color(39, 200, 32));

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
                    -bi.getWidth(),
                    -creature.getFattiness(),
                    creature.getFattiness(),
                    creature.getFattiness(),
                    null);
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening player skin");
        }
        g.setTransform(oldForm);
    }

    protected void drawBackground(Graphics2D g)
    {
        BufferedImage bi;
        try{
            bi = ImageIO.read(new File("src/skins/background.jpg"));

            for (int m = -2; m < 3; m++)
            {
                for (int n = -2; n < 3; n++)
                {
                    g.drawImage(bi, m * bi.getWidth(), n * bi.getHeight(), null);
                }
            }
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening player skin");
        }
    }
}
