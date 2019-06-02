package creatureParts;

import javax.imageio.ImageIO;
import java.io.File;
import java.io.IOException;

public class Body extends CreaturePart {
    public Body()
    {
        try {
            this.skins.add(ImageIO.read(new File("src/skins/body.png")));
        }catch(IOException ioe){
            System.out.println("can't load body sprite");
        }
    }
}
