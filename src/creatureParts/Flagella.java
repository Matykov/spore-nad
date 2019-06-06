package creatureParts;

import engine.Player;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class Flagella extends CreaturePart{
    String skin;
    public Flagella()
    {
        this.angleOffset = Math.PI;
        this.skins = new ArrayList<>();

        BufferedReader reader;
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "\\flagella.txt");
            reader = new BufferedReader(file);
            skin = reader.readLine().replaceAll("but", "");
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        try {
            //this.skins.add(ImageIO.read(new File(skin)));
            var skin1 =new ImageIcon(ImageIO.read( new File(skin)));
            var skin2 =new ImageIcon(ImageIO.read( new File(skin.replaceAll("A", "B"))));

            for(int i=0;i<6;i++)
                skins.add(skin1);
            for(int i=0;i<6;i++)
                skins.add(skin2);
        }catch(IOException ioe){
            System.out.println("can't load body sprite");




        }
    }
}
