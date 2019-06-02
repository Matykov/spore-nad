package creatureParts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Eye extends CreaturePart
{
    public Eye()
    {
        this.angleOffset = 0.0;
        try {
            this.skins = new ArrayList<BufferedImage>();
            //TODO: rework eye skin
            skins.add(ImageIO.read(new File("src/skins/eye.png")));
        }catch(IOException ioe){
            System.out.println("Can't load eye skin");
        }
    }
}
