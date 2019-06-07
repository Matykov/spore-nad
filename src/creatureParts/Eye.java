package creatureParts;

import javax.imageio.ImageIO;
import javax.swing.*;
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
            this.skins = new ArrayList<>();
            //TODO: rework eye skin
            skins.add(new ImageIcon(ImageIO.read(new File("src/skins/eye.png"))));
        }catch(IOException ioe){
            System.out.println("Can't load eye skin");
        }
    }
}
