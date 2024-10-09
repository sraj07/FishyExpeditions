import mayflower.*;
public class Startscreen extends World
{
    public Startscreen()
    {
        setBackground("img/BG/startscreen.png");
    }

    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
            Mayflower.setWorld(new Level1());
    }
}
