package engine;

import creatureParts.Body;
import creatureParts.Eye;
import creatureParts.Flagella;
import creatureParts.Spike;

import java.io.*;
import java.awt.*;

public class Player extends Creature implements Serializable
{
    public static String CurrentDir(){
        //String path=System.getProperty("java.class.path");
        //String FileSeparator=(String)System.getProperty("file.separator");
        return /*path.substring(0, path.lastIndexOf(FileSeparator)+1)*/ "src\\playerIni\\";
    }
    public Player(Point position, int speed, int agility, int fattiness)

    {
        super(position, speed, agility, fattiness);
        System.out.println(CurrentDir());
        this.body = new Body(true);
        this.creatureParts.add(new Eye());
        this.creatureParts.add(new Flagella(Math.PI));
        this.creatureParts.add(new Spike(0, true));
        this.creatureParts.add(new Spike(1, true));
    }

}