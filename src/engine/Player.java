package engine;
import java.io.*;
import java.awt.*;

public class Player extends Creature
{

    public Player(Point position, int speed, int agility, int fattiness)

    {
        super(position, speed, agility, fattiness);
            //g.setColor(new Color(0x842D4E));
        getNewColor();

    }
    public void getNewColor(){
        String color;
        BufferedReader reader;
        try {
            FileReader file = new FileReader("C:\\Users\\BigBird\\Desktop\\colors.txt");
            reader = new BufferedReader(file);

            color =  reader.readLine();
            System.out.println(color);
            if (color != null)
                switch (color) {
                    case "red":
                        bodyColor = Color.red;
                        break;
                    case "blue":
                        bodyColor = Color.blue;
                        break;
                    case "green":
                        bodyColor = Color.green;
                        break;
                    case "yellow":
                        bodyColor = Color.yellow;
                        break;
                    case "orange":
                        bodyColor = Color.orange;
                        break;
                    //default:
                    //  g.setColor(Color.red);

                }
            else
                //g.setColor(new Color(0x842D4E));
                bodyColor = Color.red;
            //g.setColor(Player.bodyColor);

        }catch(IOException ioe) {
            System.err.println(ioe.toString());

        }
        System.out.println(bodyColor);
    }
    public Color flagellaColor;
    public Color bodyColor;
    public Color spikesColor;
    public Color eyeColor;


}