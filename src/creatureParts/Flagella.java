package creatureParts;

import engine.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Random;

public class Flagella extends CreaturePart{
    String skin;
    public Flagella(Double anglOffset)
    {
        this.angleOffset = anglOffset;
        this.posMultiplier = new Point(1,  2);
        this.skins = new ArrayList<ImageIcon>();
        BufferedReader reader;
        try {
            FileReader file = new FileReader(Player.CurrentDir()+"flagella.txt");
            reader = new BufferedReader(file);
            skin = reader.readLine().replaceAll("but", "");
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        try {
            var skin1 =skin;
            var skin2 = skin.replaceAll("A", "B");
            for(int i=0;i<10;i++)
                this.skins.add(new ImageIcon(ImageIO.read(new File(skin1))));
            for(int i=0;i<10;i++)
                this.skins.add(new ImageIcon(ImageIO.read(new File(skin2))));
        }catch(IOException ioe){
            System.out.println("can't load flagella sprite");


        }
    }
    public Flagella()
    {
        this.angleOffset = Math.PI;
        this.posMultiplier = new Point(1,  2);
        this.skins = new ArrayList<ImageIcon>();
        Random r = new Random();
        int flagellaIndex = r.nextInt(4) + 1;
        String flagellaSkinNameA = String.format("src\\skins\\flagella%d%s.png", flagellaIndex, "A");
        String flagellaSkinNameB = String.format("src\\skins\\flagella%d%s.png", flagellaIndex, "B");

        try
        {
            for(int i=0;i<10;i++)
                this.skins.add(new ImageIcon(ImageIO.read(new File(flagellaSkinNameA))));
            for(int i=0;i<10;i++)
                this.skins.add(new ImageIcon(ImageIO.read(new File(flagellaSkinNameB))));
        }catch(IOException ioe){
            System.out.println("can't load flagella sprite");
        }
    }
}
