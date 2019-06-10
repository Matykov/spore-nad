package creatureParts;

import engine.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;


public class Body extends CreaturePart {
    String skin;
    public Body(boolean isPlayer)
    {
        BufferedReader reader;
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "colors.txt");
            reader = new BufferedReader(file);
            skin = reader.readLine().replaceAll("but", "");
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        try {
            this.skins.add(new ImageIcon(ImageIO.read(new File(skin))));
        }catch(IOException ioe){
            System.out.println("can't load body sprite");
        }
    }
    public Body()
    {
        Random r = new Random();
        int bodyId = r.nextInt(4) + 1;
        skin = String.format("src\\skins\\body%d.png", bodyId);
        try {
            this.skins.add(new ImageIcon(ImageIO.read(new File(skin))));
        }catch(IOException ioe){
            System.out.println("can't load body sprite");
        }
    }
}
