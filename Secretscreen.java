import mayflower.*;
public class Secretscreen extends World
{   
    public Secretscreen()
    {
        setBackground("img/BG/secretscreen.png");
    }

    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE) || Mayflower.isKeyDown(Keyboard.KEY_R))
        {
            Mayflower.setWorld(new Level1());
        }
    }
}
