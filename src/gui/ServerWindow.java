//package gui;
//
//import engine.*;
//import logic.*;
//
//import javax.swing.*;
//import java.awt.*;
//import java.awt.geom.AffineTransform;
//
//public class ServerWindow extends GameWindow {
//
//    public ServerWindow(JFrame frame, ServerGame game) {
//        //super(frame, game);
//        this.frame = frame;
//        this.game = game;
//    }
//
//    @Override
//    public void drawGame(Graphics2D g)
//    {
//        Graphics2D g2d = (Graphics2D)g;
//        for (var creature: ((ServerGame)game).getPlayers()){
//            drawCreature(g, creature);
//        }
//        drawFood(g);
//
//    }
//
//    @Override
//    protected void drawMap(Graphics2D g, AffineTransform oldForm)
//    {
//        for (Creature creature : game.getBots())
//        {
//            drawCreature(g, creature);
//            drawEye(g, oldForm, creature);
//        }
//    }
//
//    @Override
//    protected void drawPlayer(Graphics g)
//    {
//
//    }
//
//}
