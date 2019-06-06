package creatureParts;

import javax.swing.*;
import java.io.Serializable;
import java.util.ArrayList;

public abstract class CreaturePart implements Serializable {
    protected ArrayList<ImageIcon> skins = new ArrayList<>();
    private int curSkin = 0;
    public ImageIcon getSkin(){
        curSkin++;
        if(curSkin >= skins.size())
            curSkin = 0;
        return skins.get(curSkin);
    }
}
