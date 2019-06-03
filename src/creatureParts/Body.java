package creatureParts;

import engine.Player;

import javax.imageio.ImageIO;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;


public class Body extends CreaturePart {
    String skin;
    public Body()
    {
        BufferedReader reader;
        try {
            FileReader file = new FileReader(Player.CurrentDir() + "\\colors.txt");
            reader = new BufferedReader(file);
            if (reader.readLine().startsWith("1")) {
                skin = "src/skins/eye.png";
            }
            else{
                skin = "src/skins/body.png";
            }
        }
        catch(IOException ioe) {
            System.err.println(ioe.toString());
        }

        try {
            this.skins.add(ImageIO.read(new File(skin)));
        }catch(IOException ioe){
            System.out.println("can't load body sprite");
        }
    }
}
