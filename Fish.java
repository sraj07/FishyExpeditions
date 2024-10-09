import mayflower.*;
//import java.util.*;

public class Fish extends MovableAnimatedActor
{
    private int currentFrame;
    private Animation walkRight;
    private Animation walkLeft;
    private Animation idle;
    private Animation idleLeft;
    private Animation fallRight;
    private Animation fallLeft;
    
    private boolean isLevel2;
    private boolean isBonusLevel;
    private boolean secretEnding;
    
    private int score;
    private int health;
    private int breath;
    
    private Timer breathTimer;
    
    public Fish(boolean iL2, boolean iBL, int prevScore)
    {
        score = prevScore;
        health = 3;
        if (!iBL)
            breath = 30;
        else
        {
            breath = 0;
        }
        
        isLevel2 = iL2;
        isBonusLevel = iBL;
        secretEnding = true;
        
        breathTimer = new Timer(1000000000);
        
        //walkRight
        String[] walkRightAnimation = new String[30];
        for (int i=0;i<30;i++)
        {
            walkRightAnimation[i] = "img/fish/New Piskel-" + (i+1) + ".png.png";
            //                                New Piskel-      1      .png.png
        }
        walkRight = new Animation(30, walkRightAnimation);
        walkRight.setBounds(7, 4, 20, 19);
        walkRight.scale(67,58);
        setAnimation(walkRight);
        setWalkRightAnimation(walkRight);
        
        //walkLeft
        String[] walkLeftAnimation = new String[30];
        for (int i=0;i<30;i++)
        {
            walkLeftAnimation[i] = "img/fish/New Piskel-" + (i+1) + ".png.png";
        }
        walkLeft = new Animation(30, walkLeftAnimation);
        walkLeft.setBounds(7, 4, 20, 19);
        walkLeft.scale(67,58);
        walkLeft.mirrorHorizontally();
        setWalkLeftAnimation(walkLeft);
        
        //idleLeft
        String[] idleLeftAnimation = new String[13];
        for (int i=17;i<30;i++)
        {
            idleLeftAnimation[i-17] = "img/fish/New Piskel-" + (i+1) + ".png.png";
        }
        idleLeft = new Animation(50, idleLeftAnimation);
        idleLeft.setBounds(7,9,20,15);
        idleLeft.scale(67,58);
        idleLeft.mirrorHorizontally();
        setIdleLeftAnimation(idleLeft);
        
        //idle (Right)
        String[] idleAnimation = new String[13];
        for (int i=17;i<30;i++)
        {
            idleAnimation[i-17] = "img/fish/New Piskel-" + (i+1) + ".png.png";
        }
        idle = new Animation(50, idleAnimation);
        idle.setBounds(7,9,20,15);
        idle.scale(67,58);
        setIdleAnimation(idle);
        
        //fallRight
        String[] fallRightAnimation = new String[17];
        for (int i=0;i<17;i++)
        {
            fallRightAnimation[i] = "img/fish/New Piskel-" + (i+1) + ".png.png";
        }
        fallRight = new Animation(17, fallRightAnimation);
        fallRight.setBounds(7, 4, 20, 19);
        fallRight.scale(67,58);
        setFallRightAnimation(fallRight);
        
        String[] fallLeftAnimation = new String[17];
        for (int i=0;i<17;i++)
        {
            fallLeftAnimation[i] = "img/fish/New Piskel-" + (i+1) + ".png.png";
        }
        fallLeft = new Animation(17, fallLeftAnimation);
        fallLeft.setBounds(7, 4, 20, 19);
        fallLeft.scale(67,58);
        fallLeft.mirrorHorizontally();
        setFallLeftAnimation(fallLeft);
    }
    
    public void act()
    {
        if (isTouching(Fishbowl1.class))
        {
            World w = getWorld();
            Mayflower.setWorld(new Level2(getScore()));
        }
        if (isTouching(Level2Checkpoint.class))
        {
            World w = getWorld();
            Mayflower.setWorld(new Level3(getScore()));
        }
        if (isTouching(Fishbowl3.class))
        {
            World w = getWorld();
            Mayflower.setWorld(new Victoryscreen());
            Mayflower.playSound("sfx/gamewin1.wav");
            Mayflower.playSound("sfx/gamewin2.wav");
        }
        if (breathTimer.isDone())
        {
            if (!isBonusLevel)
                decreaseBreath(1);
            else
                decreaseBreath(-1);
            breathTimer.reset();
        }
        super.act();
        
        if (isBonusLevel)
        {
            if (Mayflower.isKeyDown(Keyboard.KEY_SPACE)) //Allows fish to fly only during the bonus level
            {
                setLocation(getX(), getY()-15);
            }
            if (score >= 72 && secretEnding)
            {
                Mayflower.playSound("sfx/fish_bonus_1.wav");
                World w = new Secretscreen();
                Mayflower.setWorld(w);
                secretEnding = false;
            }
        }
    }
    
    public int getScore()
    {
        return score;
    }
    
    public int getHealth()
    {
        return health;
    }
    
    public int getBreath()
    {
        return breath;
    }
    
    public void increaseScore(int amount)
    {
        score += amount;
        updateText();
    }
    
    public void decreaseHealth(int amount)
    {
        health -= amount;
        updateText();
        if (health <= 0)
        {
            World w = new GameOverscreen();
            Mayflower.setWorld(w);
            Mayflower.playSound("sfx/gameover1.wav");
        }
        else
        {
            Mayflower.playSound("sfx/fish_hurt.wav");
        }
    }
    
    public void decreaseBreath(int amount)
    {
        breath -= amount;
        updateText();
        if (breath <= 0)
        {
            World w = new GameOverscreen();
            Mayflower.setWorld(w);
        }
    }
    
    public void updateText()
    {
        World w = getWorld();
        w.removeText(10,30);
        w.showText("Score: " + score + " Health: " + health + " Time: " + breath, 10, 30, Color.BLACK);
    }
}