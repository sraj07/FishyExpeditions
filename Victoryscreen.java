import mayflower.*;
public class Victoryscreen extends World
{
    public Victoryscreen()
    {
        setBackground("img/BG/victoryscreen.png");
    }

    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE))
            Mayflower.setWorld(new BonusLevel());
        if (Mayflower.isKeyDown(Keyboard.KEY_R))
            Mayflower.setWorld(new Level1());
    }
}
