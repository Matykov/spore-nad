package gui;

import engine.*;
import logic.*;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ServerWindow extends GameWindow {

    public ServerWindow(JFrame frame, ServerGame game) {
        //super(frame, game);
        this.frame = frame;
        this.game = game;
        this.MapShift = new Point(0,0);
    }


    @Override
    protected void drawSector(Graphics2D g, AffineTransform oldForm, Sector sector)
    {

        AffineTransform mapAT = (AffineTransform) (oldForm.clone());
        mapAT.translate(MapShift.x, MapShift.y);
        g.setTransform(mapAT);
        drawBackground(g, sector);
        drawFood(g, sector);


        for (Creature creature : sector.creatures) {
            drawCreature(g, creature, sector);
            drawEye(g, mapAT, creature, sector);
        }

        g.setTransform(oldForm);
    }

    @Override
    protected void drawPlayer(Graphics g) {

    }

    @Override
    protected void drawGame(Graphics2D g) {
        AffineTransform origXform = g.getTransform();
        var sectors = game.getSectorNet().getSectors();
        for (var sector: sectors)
        {
            drawSector(g, origXform, sector);
        }

    }
}
