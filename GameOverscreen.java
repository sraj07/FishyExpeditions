import mayflower.*;
public class GameOverscreen extends World
{   
    public GameOverscreen()
    {
        setBackground("img/BG/gameoverscreen.png");
    }

    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_SPACE) || Mayflower.isKeyDown(Keyboard.KEY_R))
        {
            Mayflower.setWorld(new Level1());
        }
    }
}
