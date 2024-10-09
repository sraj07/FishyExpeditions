import mayflower.*;
public class Coin extends AnimatedActor
{
    private Animation spin;
    
    public Coin()
    {
        String[] spinAnimation = new String[8];
        for (int i=0;i<8;i++)
        {
            spinAnimation[i] = "img/coin/frame_" + i + "_delay-0.1s.png";
        }
        spin = new Animation(8, spinAnimation);
        setAnimation(spin);
    }
    
    public void act ()
    {
        super.act();
        setLocation(getX(), getY()-4);
        
        if (isTouching(Fish.class))
        {
            Object a = getOneIntersectingObject(Fish.class);
            Fish f = (Fish) a;
            World w = getWorld();
            f.increaseScore(1);
            Mayflower.playSound("sfx/coin_collect.wav");
            setLocation(getX()+1000, getY()-1000);
        }
    }
}
