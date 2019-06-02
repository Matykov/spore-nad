package creatureParts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Flagella extends CreaturePart{

    public Flagella()
    {
        this.skins = new ArrayList<BufferedImage>();
        try {
            skins.add(ImageIO.read(new File("src/skins/flagella1.png")));
            skins.add(ImageIO.read(new File("src/skins/flagella2.png")));
        }catch(IOException ioe){
            System.out.println("Can't flagella skin");
        }
    }
}
