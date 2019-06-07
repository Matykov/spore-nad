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
        this.skins = new ArrayList<ImageIcon>();

        BufferedReader reader;
        try {
            FileReader file = new FileReader("src\\playerIni\\flagella.txt");
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
            System.out.println("can't load body sprite");


        }
    }
}
