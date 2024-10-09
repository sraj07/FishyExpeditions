import mayflower.*;
public class AutomaticAnimatedActor extends AnimatedActor
{
    private Animation walkRight;
    private Animation walkLeft;
    private Animation runRight;
    private Animation runLeft;
    private String direction;
    private int leftLimit;
    private int rightLimit;
    private int speed;
    private boolean isFast;

    public AutomaticAnimatedActor(int lL, int rL, int s)
    {   
        super();
        direction = "left";
        leftLimit = lL;
        rightLimit = rL;
        speed = s;
        
        if (speed>=6)
            isFast = true;
        else
            isFast = false;
    }

    public void act()
    {
        String newAction = null;
        int x = getX();
        int y = getY();
        int w = getWidth();
        
        //checks if the actor hits the right limit
        if (x>=rightLimit-w)
        {
            direction = "left"; //tells actor to turn around
        }
        //checks if the actor hits the left limit
        if (x<=leftLimit+w)
        {
            direction = "right"; //tells actor to turn around
        }
        
        //checks the current direction
        if (direction.equals("right"))
        {
            //if fast enough, the actor will run
            if (isFast)
                setAnimation(runRight);
            else
                setAnimation(walkRight);
            setLocation(x+speed,y); //movement
        }
        else
        {
            //same as right
            if (isFast)
                setAnimation(runLeft);
            else
                setAnimation(walkLeft);
            setLocation(x-speed,y); //movement
        }
        
        super.act();
    }

    public void setWalkRightAnimation(Animation ani)
    {
        walkRight = ani;
    }

    public void setWalkLeftAnimation(Animation ani)
    {
        walkLeft = ani;
    }

    public void setAnimation(Animation a)
    {
        super.setAnimation(a);
    }
    
    public void setRunRightAnimation(Animation ani)
    {
        runRight = ani;
    }
    
    public void setRunLeftAnimation(Animation ani)
    {
        runLeft = ani;
    }
}
