package creatureParts;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Flagella extends CreaturePart{
    public Flagella()
    {
        this.angleOffset = Math.PI;
        this.skins = new ArrayList<BufferedImage>();
        try {
            var skin1 =ImageIO.read( new File("src/skins/flagella1.png"));
            var skin2 =ImageIO.read( new File("src/skins/flagella2.png"));
            for(int i=0;i<6;i++)
                skins.add(skin1);
            for(int i=0;i<6;i++)
                skins.add(skin2);

        }catch(IOException ioe){
            System.out.println("Can't flagella skin");
        }
    }
}
