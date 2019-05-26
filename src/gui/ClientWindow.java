package gui;

import engine.*;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.util.ArrayList;

public class ClientWindow extends GameWindow
{
    public ClientWindow(JFrame frame, Game game)
    {
        if(!(game instanceof ServerGame))
            MapShift = new Point(frame.getWidth() / 2 - game.getPlayer().sectorPosition.x,
                frame.getHeight() / 2 - game.getPlayer().sectorPosition.y);
        else
            MapShift = new Point(0,0);
        this.frame = frame;
        this.game = game;
        this.keyAdapter = new KeyAdapter()
        {
            public void keyPressed(KeyEvent e)
            {
                if (e.getKeyCode() == KeyEvent.VK_UP)
                {
                    var s = game.getPlayer().move(-5);
                    MapShift.x -= s.x;
                    MapShift.y -= s.y;
                    //game.update();
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    var s = game.getPlayer().move(5);
                    MapShift.x -= s.x;
                    MapShift.y -= s.y;
                    //game.update();
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    game.getPlayer().turn(Math.PI / 8);
                    //game.update();
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    game.getPlayer().turn(-Math.PI / 8);
                    //game.update();
                }
                repaint();
            }
        };
        if(!(game instanceof ServerGame))
            this.frame.addKeyListener(keyAdapter);
    }

    @Override
    protected void drawGame(Graphics2D g)
    {
        AffineTransform origXform = g.getTransform();
        var sectors = new ArrayList<>(game.getSectorNet().getSectors());
        for (var sector: sectors)
        {
            drawSector(g, origXform, sector);
        }

        if (!(game instanceof ClientGame) &&!(game instanceof ServerGame))
            drawProgressBar(g);
        if(!(game instanceof ServerGame)) {
            drawPlayer(g);

            drawEye(g, origXform, game.getPlayer(), null);
        }

        if (game.isLevelCompleted())
        {
            drawLevelCompletion(g);
        }

    }

    @Override
    protected void drawSector(Graphics2D g, AffineTransform oldForm, Sector sector)
    {

        AffineTransform mapAT = (AffineTransform) (oldForm.clone());
        mapAT.translate(MapShift.x, MapShift.y);
        g.setTransform(mapAT);
        drawBackground(g, sector);
        drawFood(g, sector);

        var creatures = new ArrayList<Creature>(sector.creatures);
        for (Creature creature : creatures) {
            drawCreature(g, creature, sector);
            drawEye(g, mapAT, creature, sector);
        }

        g.setTransform(oldForm);
    }

    @Override
    protected void drawPlayer(Graphics g)
    {
        g.setColor(new Color(0x000084));
        g.fillOval(frame.getWidth() / 2 - game.getPlayer().getFattiness(),
                frame.getHeight() / 2 - game.getPlayer().getFattiness(),
                game.getPlayer().getFattiness() * 2,
                game.getPlayer().getFattiness() * 2);
    }

    private void drawLevelCompletion(Graphics2D g)
    {
        g.setColor(new Color(0));
        g.drawRect(0, 0, frame.getWidth(), 500);
    }
}
