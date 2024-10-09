import mayflower.*;

public class GravityActor extends Actor
{
    public int gravity;
    
    public GravityActor()
    {
        gravity = 4;
    }
    
    public void act()
    {
        setLocation(getX(), getY()+gravity);
        
        if (isBlocked())
            setLocation(getX(), getY()-gravity);
    }
    
    public boolean isBlocked()
    {
        return isTouching(Block.class);
    }
    
    public boolean isFalling()
    {
        boolean ret;
        setLocation(getX(), getY() + gravity);
        ret = isTouching(Block.class);
        setLocation(getX(), getY() - gravity);
        return !ret;
    }
}
