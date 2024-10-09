import mayflower.*;

public class AnimatedActor extends GravityActor
{
    private Animation animation;
    private Timer animationTimer;
    
    public AnimatedActor()
    {
        animationTimer = new Timer(20000000);
    }
    
    public void setAnimation(Animation a)
    {
        animation = a;
    }
    
    public void act()
    {
        if (animationTimer.isDone())
        {
            MayflowerImage nextFrame = animation.getNextFrame();
            setImage(nextFrame);
            animationTimer.reset();
        }
        super.act();
    }
}
