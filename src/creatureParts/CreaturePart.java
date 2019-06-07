package creatureParts;

import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class CreaturePart implements Serializable {
    protected ArrayList<ImageIcon> skins = new ArrayList<>();
    private int curSkin = 0;
    protected Double angleOffset = 0.0;
    public Image getSkin(){
        curSkin++;
        if(curSkin >= skins.size())
            curSkin = 0;
        return skins.get(curSkin).getImage();
    }
    public Double getAngleOffset(){
        return angleOffset;
    }
}
