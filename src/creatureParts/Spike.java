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

public class Spike extends CreaturePart {
    String skin;
    public Spike(int left)
    {
        if(left == 0)
            this.angleOffset = -Math.PI/4;
        else
            this.angleOffset = Math.PI/4;
        this.posMultiplier = new Point(1, 2);
        this.skins = new ArrayList<>();
        BufferedReader reader;
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "spikes.txt");
            reader = new BufferedReader(file);
            skin = reader.readLine().replaceAll("but", "");
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        try {
            this.skins.add(new ImageIcon(ImageIO.read(new File(skin))));
        }catch(IOException ioe){
            System.out.println("can't load spike sprite");
        }
    }
}
