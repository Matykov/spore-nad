package gui;

import com.sun.tools.javac.Main;
import engine.Creature;
import engine.Level;
import logic.ServerGame;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.AffineTransform;

public class ServerWindow extends MainWindow {

    protected ServerWindow(JFrame frame, ServerGame game) {
        super(frame, game);
    }

    @Override
    public void paintComponent(Graphics g)
    {
//        var father = this.getParent();
//        father.getParent().paintComponents(g);
        Graphics2D g2d = (Graphics2D)g;
        for (var creature: game.getLevel().getCreatures()){
            drawCreature(g, creature);
        }
        drawFood(g);
        //drawProgressBar(g);
    }
    public void drawCreature(Graphics g, Creature creature){
        g.setColor(new Color(0, 150, 200));
        g.fillOval(creature.getPosition().x - creature.getFattiness(),
                creature.getPosition().y - creature.getFattiness(),
                creature.getFattiness() * 2,
                creature.getFattiness() * 2);
    }
}
