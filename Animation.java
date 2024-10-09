import mayflower.*;

public class Animation
{
    private int currentFrame;
    private int frameRate;
    private MayflowerImage[] frames;
    
    public Animation(int frameRate, String[] arr)
    {
        frames = new MayflowerImage[arr.length];
        for (int i = 0; i < frames.length; i ++)
        {
            frames[i] = new MayflowerImage(arr[i]);
        }
        
        currentFrame = frameRate;
        currentFrame = 0;
    }
    
    public int getFrameRate()
    {
        return frameRate;
    }
   
    public MayflowerImage getNextFrame(){
        MayflowerImage nextFrame = frames[currentFrame];
        if (currentFrame < frames.length-1)
        {
            currentFrame++;
        }
        else
        {
            currentFrame = 0;
        }
        return nextFrame;
    }
    
    public void scale(int w, int h)
    {
        for (int i=0;i<frames.length;i++){
            frames[i].scale(w, h);
        }
    }
    
    public void setTransparency(int percent)
    {
        for (int i=0;i<frames.length;i++){
            frames[i].setTransparency(percent);
        }
    }
    
    public void mirrorHorizontally()
    {
        for (int i=0;i<frames.length;i++){
            frames[i].mirrorHorizontally();
        }
    }
    
    public void setBounds(int x, int y, int w, int h)
    {
        for (int i=0;i<frames.length;i++){
            frames[i].crop(x, y, w, h);
        }
    }
}

