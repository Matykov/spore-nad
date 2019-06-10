package creatureParts;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

public class Eye extends CreaturePart
{
    public Eye()
    {
        //this.angleOffset = Math.PI/2;
        try {
            this.skins = new ArrayList<>();
            //TODO: rework eye skin
            skins.add(new ImageIcon(ImageIO.read(new File("src/skins/eye.png"))));
            //this.posMultiplier = new Point(0, 0);
        }catch(IOException ioe){
            System.out.println("Can't load eye skin");
        }
        //this.posOffset = new Point(-skins.get(0).getIconWidth()/2, -skins.get(0).getIconHeight()/2);
    }
}
