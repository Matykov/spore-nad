package creatureParts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Spike extends CreaturePart {

    public Spike()
    {
        this.skins = new ArrayList<>();
        try {
            skins.add(new ImageIcon(ImageIO.read(new File("src/skins/spikeRight.png"))));
        }catch(IOException ioe){
            System.out.println("Can't spike skin");
        }

    }
}
