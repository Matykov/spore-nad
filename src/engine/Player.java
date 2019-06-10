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
        //getNewColor();
            //g.setColor(new Color(0x842D4E));
        //getNewColor();

    }
//    public void getNewColor(){
//        String color;
//        BufferedReader reader;
//        try {
//            FileReader file = new FileReader(CurrentDir() + "\\colors.txt");
//            reader = new BufferedReader(file);
//
//            color =  reader.readLine();
//            System.out.println(color);
//            if (color != null)
//                switch (color) {
//                    case "red":
//                        bodyColor = Color.red;
//                        break;
//                    case "blue":
//                        bodyColor = Color.blue;
//                        break;
//                    case "green":
//                        bodyColor = Color.green;
//                        break;
//                    case "yellow":
//                        bodyColor = Color.yellow;
//                        break;
//                    case "orange":
//                        bodyColor = Color.orange;
//                        break;
//                    //default:
//                    //  g.setColor(Color.red);
//
//                }
//            else
//                //g.setColor(new Color(0x842D4E));
//                bodyColor = Color.red;
//            //g.setColor(Player.bodyColor);
//
//        }catch(IOException ioe) {
//            System.err.println(ioe.toString());
//
//        }
//        System.out.println(bodyColor);
//    }


}