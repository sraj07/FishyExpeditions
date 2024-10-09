import mayflower.*;
public class MovableAnimatedActor extends AnimatedActor
{
    private Animation walkRight;
    private Animation walkLeft;
    private Animation idle;
    private Animation idleLeft;
    private Animation fallRight;
    private Animation fallLeft;
    private String currentAction;
    private String direction;

    private boolean isJumping;
    private int verticalVelocity;
    private final int jumpStrength;
    private boolean climbing;
    
    int count;

    public MovableAnimatedActor()
    {   
        super();
        direction = "right";
        isJumping = false;
        verticalVelocity = 0;
        climbing = false;
        jumpStrength = 80;
    }

    public void act()
    {
        String newAction = null;
        int x = getX();
        int y = getY();
        int w = getWidth();
        int h = getHeight();
        int speed = 5;
        
        if (Mayflower.isKeyDown( Keyboard.KEY_RIGHT ))
        {
            newAction = "walkRight";
            direction = "right";
            setLocation(x+speed,y);
            if (isBlocked())
                setLocation(x-1,y);
            else
                newAction = "fallRight";
            if (!isFalling() && count > 0)
            {
                setLocation(x,y-5);
                count--;
            }
        }

        else if (Mayflower.isKeyDown( Keyboard.KEY_LEFT ))
        {
            newAction = "walkLeft";
            direction = "left";
            setLocation(x-speed,y);
            if (isBlocked())
                setLocation(x+1,y);
            else
                newAction = "fallLeft";
            if (!isFalling() && count > 0)
            {
                setLocation(x,y-5);
                count--;
            }
        }
        
        if (Mayflower.isKeyDown( Keyboard.KEY_UP ))
        {
            if (!isFalling())
                count = 10;
            if (isTouching(Ladder.class))
            {
                if (direction.equals("left"))
                    newAction = "walkLeft";
                else if (direction.equals("right"))
                    newAction = "walkRight";
                if (Mayflower.isKeyDown(Keyboard.KEY_UP)) {
                    setLocation(x, y - 2*speed);  // Move up the ladder
                }
            }
        }
        else
        {
            if (direction.equals("left"))
                newAction = "idleLeft";
            else if (direction.equals("right"))
                newAction = "idle";
        }
        
        if (count > 0)
        {
            setLocation(x,y-20);
            count--;
        }
        
        if (x>=(1600-w))
        {
            setLocation(800-w-1,y);
        }
        if (x<=0)
        {
            setLocation(1,y);
        }
        if (y<=0)
        {
            setLocation(x,1);
        }
        if (y>=(900-h))
        {
            setLocation(x,600-h-1);
        }
        super.act();
        if (newAction!=null && !newAction.equals(currentAction))
        {
            if (newAction.equals("idleLeft"))
                setAnimation(idleLeft);
            else if (newAction.equals("idle"))
                setAnimation(idle);
            else if (newAction.equals("walkRight"))
                setAnimation(walkRight);
            else if (newAction.equals("walkLeft"))
                setAnimation(walkLeft);
            else if (newAction.equals("fallRight"))
                setAnimation(fallRight);
            else if (newAction.equals("fallLeft"))
                setAnimation(fallLeft);
        }
    }
    
    public void jump()
    {
        int velocity = 7;
        for (int i=0;i<30;i++)
        {
            setLocation(getX(),getY()-velocity);
        }
    }

    public boolean isClimbing()
    {
        // Check if the cat is touching a ladder and the UP or DOWN keys are pressed
        if (isTouching(Ladder.class) && (Mayflower.isKeyDown(Keyboard.KEY_UP) || Mayflower.isKeyDown(Keyboard.KEY_DOWN))) {
            climbing = true;  // Climbing
            return true;
        }
        else {
            climbing = false;  // Not climbing
            return false;
        }

    }

    public boolean isBlocked(){
        if(isTouching(Block.class))
            return true;
        return false;
    }

    public void setWalkRightAnimation(Animation ani)
    {
        walkRight = ani;
    }

    public void setWalkLeftAnimation(Animation ani)
    {
        walkLeft = ani;
    }

    public void setIdleAnimation(Animation ani)
    {
        idle = ani;
    }

    public void setIdleLeftAnimation(Animation ani)
    {
        idleLeft = ani;
    }

    public void setFallRightAnimation(Animation ani)
    {
        fallRight = ani;
    }

    public void setFallLeftAnimation(Animation ani)
    {
        fallLeft = ani;
    }

    public void setAnimation(Animation a)
    {
        super.setAnimation(a);
    }
}
