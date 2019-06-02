package creatureParts;

import java.awt.image.BufferedImage;
import java.util.ArrayList;

public abstract class CreaturePart {
    //private Color color;
    protected ArrayList<BufferedImage> skins = new ArrayList<>();
    private int curSkin = 0;
//    public Color getColor(){
//        return color;
//    }
    public BufferedImage getSkin(){
        curSkin++;
        if(curSkin >= skins.size())
            curSkin = 0;
        return skins.get(curSkin);
    }
}
