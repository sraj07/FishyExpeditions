import mayflower.*;

public class BonusLevel extends World {
    private String[][] tiles;
    
    private Cat cat0;
    private Cat cat1;
    private Block block;
    private Block block1;
    private Fish fish;
    
    public BonusLevel() 
    {
        setBackground("img/BG/bonuslevel.png");
        
        tiles = new String[7][12];
        buildWorld();
        
        //Mayflower.showBounds(true);
        
        fish = new Fish(false, true, 0);
        addObject(fish, 475, 598);
        
        showText("Score: " + fish.getScore() + " Health: " + fish.getHealth() + " Time: " + fish.getBreath(), 10, 30, Color.BLACK);
        showText("PRESS [SPACE] TO FLY", 400, 300, Color.ORANGE);
    }

    public void buildWorld()
    {
        for (int i=0;i<tiles.length;i++)
        {
            for (int j=0;j<tiles[0].length;j++)
            {
                tiles[i][j] = "";
            }
        }

        for (int k=0;k<tiles[0].length;k++)
        {
            tiles[tiles.length-1][k] = "ground";
        }
        
        for (int i=0;i<6;i++)
        {
            for (int j=0;j<12;j++)
            {
                tiles[i][j] = "coin";
            }
        }
        
        for (int l=0;l<tiles.length;l++)
        {
            for (int m=0;m<tiles[0].length;m++)
            {
                if (tiles[l][m].equals("ground"))
                    addObject(new WaterBlock(), m*100, l*100+90);
                if (tiles[l][m].equals("coin"))
                    addObject(new Coin(), m*100, l*100);
            }
        }
    }
    
    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_R))
            Mayflower.setWorld(new Level1());
        if (Mayflower.isKeyPressed(Keyboard.KEY_SPACE))
            removeText(400,300);
    }
}