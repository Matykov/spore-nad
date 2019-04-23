package gui;

import engine.*;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class MainWindow extends JPanel implements ActionListener
{
    Timer timer = new Timer(10, this);

    protected JFrame frame;
    protected Game game;
    private KeyAdapter keyAdapter;

    private Point MapShift;

    protected MainWindow()
    {
    }

    public MainWindow(JFrame frame, Game game)
    {
        timer.start();
        game.update();
        MapShift = new Point(frame.getWidth() / 2 - game.getPlayer().getPosition().x, frame.getHeight() / 2 - game.getPlayer().getPosition().x);
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
                }
                if (e.getKeyCode() == KeyEvent.VK_DOWN)
                {
                    var s = game.getPlayer().move(5);
                    MapShift.x -= s.x;
                    MapShift.y -= s.y;
                }
                if (e.getKeyCode() == KeyEvent.VK_RIGHT)
                {
                    game.getPlayer().turn(Math.PI / 8);
                    System.out.println(game.getPlayer().getDirection());
                }
                if (e.getKeyCode() == KeyEvent.VK_LEFT)
                {
                    game.getPlayer().turn(-Math.PI / 8);
                    System.out.println(game.getPlayer().getDirection());
                }
                repaint();
            }
        };
        frame.addKeyListener(keyAdapter);
    }

    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);

        Graphics2D g2d = (Graphics2D)g;
        AffineTransform origXform = g2d.getTransform();

        drawMap(g2d, origXform);
        drawPlayer(g2d);
        drawEye(g2d, origXform);
        drawProgressBar(g2d);
    }

    public void drawCreature(Graphics g)
    {
        Creature creature = game.getPlayer();
        g.setColor(new Color(0, 150, 200));
        g.fillOval(creature.getPosition().x - creature.getFattiness(),
                creature.getPosition().y - creature.getFattiness(),
                creature.getFattiness() * 2,
                creature.getFattiness() * 2);
    }

    private void drawMap(Graphics2D g, AffineTransform oldForm)
    {
        AffineTransform mapAT = (AffineTransform) (oldForm.clone());
        mapAT.translate(MapShift.x, MapShift.y);
        g.setTransform(mapAT);
        drawFood(g);
        //true position
        g.drawOval(game.getPlayer().getPosition().x - game.getPlayer().getFattiness(),
                game.getPlayer().getPosition().y - game.getPlayer().getFattiness(),
                game.getPlayer().getFattiness() * 2,
                game.getPlayer().getFattiness() * 2);

        g.setTransform(oldForm);
    }

    private void drawPlayer(Graphics g)
    {
        g.setColor(new Color(40));
        g.fillOval(frame.getWidth() / 2 - game.getPlayer().getFattiness(),
                frame.getHeight() / 2 - game.getPlayer().getFattiness(),
                game.getPlayer().getFattiness() * 2,
                game.getPlayer().getFattiness() * 2);
    }

    public void drawProgressBar(Graphics g)
    {
        g.setColor(new Color(0x136B21));
        g.drawRect(frame.getWidth() / 4, 20, frame.getWidth() / 2, 30);
        g.fillRect(frame.getWidth() / 4, 20, (int)(game.getPercentCompletion() * frame.getWidth() / 2), 30);
    }

    protected void drawFood(Graphics g)
    {
        ArrayList<Food> food = game.getLevel().getMeals();

        g.setColor(new Color(39, 200, 32));

        for (var group = 0; group < food.size(); group++)
        {
            var pieces = food.get(group).getPieces();
            for (Point p: pieces.keySet())
            {
                g.fillOval(p.x - pieces.get(p), p.y - pieces.get(p), pieces.get(p) + 5, pieces.get(p) + 5);
                //g.drawLine(p.x - pieces.get(p), p.y - pieces.get(p), game.getPlayer().getPosition().x, game.getPlayer().getPosition().y);
            }
        }

    }

    private void drawEye(Graphics2D g, AffineTransform oldForm)
    {
        AffineTransform partsAT = (AffineTransform) (oldForm.clone());
        partsAT.rotate(game.getPlayer().getDirection(),
                frame.getWidth() / 2,
                frame.getHeight() / 2);
        partsAT.translate(frame.getWidth() / 2, frame.getHeight() /2);
        g.setTransform(partsAT);
        try
        {
            BufferedImage bi = ImageIO.read(new File("src/skins/eye.png"));
            g.drawImage(bi,
                    -bi.getWidth(),
                    -game.getPlayer().getFattiness(),
                    23,
                    23,
                    null);
        }
        catch (IOException ex)
        {
            System.out.println("Failed at opening player skin");
        }
        g.setTransform(oldForm);
    }

    @Override
    public void actionPerformed(ActionEvent e)
    {
    }
}
