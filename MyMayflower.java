import mayflower.*;

public class MyMayflower extends Mayflower
{
    //Constructor
    public MyMayflower()
    {
        super("Fishy Expeditions", 1200, 700);
    }
    
    public void init()
    {
        //Change this to true to run this program in fullscreen mode
        World w =  new Startscreen();
        Mayflower.setWorld(w);
    }
}
