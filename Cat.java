import mayflower.*;

public class Cat extends AutomaticAnimatedActor
{
    private int currentFrame;
    private Animation walkRight;
    private Animation walkLeft;
    private Animation runRight;
    private Animation runLeft;
    private int leftLimit;
    private int rightLimit;
    
    public Cat(int lL, int rL, int s)
    {
        super(lL, rL, s);
        
        //walkRight
        String[] walkRightAnimation = new String[10];
        for (int i=0;i<10;i++)
        {
            walkRightAnimation[i] = "img/cat/Walk (" + (i+1) + ").png";
        }
        walkRight = new Animation(50, walkRightAnimation);
        walkRight.scale(100,87);
        walkRight.setBounds(18, 5, 54, 80);
        setAnimation(walkRight);
        setWalkRightAnimation(walkRight);
        
        //walkLeft
        String[] walkLeftAnimation = new String[10];
        for (int i=0;i<10;i++)
        {
            walkLeftAnimation[i] = "img/cat/Walk (" + (i+1) + ").png";
        }
        walkLeft = new Animation(50, walkLeftAnimation);
        walkLeft.mirrorHorizontally();
        walkLeft.scale(100,87);
        walkLeft.setBounds(28,5,54,80);
        setWalkLeftAnimation(walkLeft);
        
        //runRight
        String[] runRightAnimation = new String[8];
        for (int i=0;i<8;i++)
        {
            runRightAnimation[i] = "img/cat/Run (" + (i+1) + ").png";
        }
        runRight = new Animation(8, runRightAnimation);
        runRight.scale(100,87);
        runRight.setBounds(18, 5, 64, 80);
        setAnimation(runRight);
        setRunRightAnimation(runRight);
        
        //runLeft
        String[] runLeftAnimation = new String[8];
        for (int i=0;i<8;i++)
        {
            runLeftAnimation[i] = "img/cat/Run (" + (i+1) + ").png";
        }
        runLeft = new Animation(8, runLeftAnimation);
        runLeft.scale(100,87);
        runLeft.setBounds(14, 5, 64, 80);
        runLeft.mirrorHorizontally();
        setAnimation(runLeft);
        setRunLeftAnimation(runLeft);
    }
    
    public void act()
    {
        if (isTouching(Fish.class))
        {
            Object a = getOneIntersectingObject(Fish.class);
            Fish f = (Fish) a;
            World w = getWorld();
            f.decreaseHealth(1);
            f.setLocation(0,500);
        }
        super.act();
    }
}