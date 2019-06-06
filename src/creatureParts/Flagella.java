package creatureParts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Flagella extends CreaturePart{

    public Flagella()
    {
        this.skins = new ArrayList<>();
        try {
            skins.add(new ImageIcon(ImageIO.read(new File("src/skins/flagella1.png"))));
            skins.add(new ImageIcon(ImageIO.read(new File("src/skins/flagella2.png"))));
        }catch(IOException ioe){
            System.out.println("Can't flagella skin");
        }
    }
}
