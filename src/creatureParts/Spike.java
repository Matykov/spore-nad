package creatureParts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Spike extends CreaturePart {

    public Spike()
    {
        this.skins = new ArrayList<BufferedImage>();
        try {
            skins.add(ImageIO.read(new File("src/skins/spikeRight.png")));
        }catch(IOException ioe){
            System.out.println("Can't spike skin");
        }

    }
}
