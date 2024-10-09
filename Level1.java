import mayflower.*;

public class Level1 extends World {
    private String[][] tiles;
    
    private Cat cat;
    private Fish fish;
    
    public Level1() 
    {
        setBackground("img/BG/BG-1.png");
        
        tiles = new String[7][12];
        buildWorld();
        
        //Mayflower.showBounds(true);
        
        fish = new Fish(false, false, 0);
        addObject(fish, 0, 500);
        
        cat = new Cat(770, 1100, 2);
        addObject(cat, 1000, 500);
        
        showText("Score: " + fish.getScore() + " Health: " + fish.getHealth() + " Time: " + fish.getBreath(), 10, 30, Color.BLACK);
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
        
        for (int i=3;i<=5;i++)
        {
            tiles[tiles.length-2][i] = "ground";
            tiles[tiles.length-3][i] = "ground";
            tiles[tiles.length-4][i] = "ground";
        }
        
        for (int i=6; i<=7; i++)
        {
            tiles[tiles.length-2][i] = "ground";
            tiles[tiles.length-3][i] = "ground";
            tiles[tiles.length-4][i] = "ground";
            tiles[tiles.length-5][i] = "ground";
        }
        
        tiles[tiles.length-2][tiles[0].length-1] = "ground";
        tiles[tiles.length-3][tiles[0].length-1] = "ground";
        tiles[tiles.length-4][tiles[0].length-1] = "ground";
        //tiles[tiles.length-5][tiles[0].length-1] = "ground";
        
        tiles[2][11] = "fishbowl";
        
        tiles[2][4] = "coin";
        tiles[3][2] = "coin";
        tiles[1][9] = "coin";
        
        addObject(new Ladder(), 150, 200);
        addObject(new Ladder(), 150, 360);
        for (int l=0;l<tiles.length;l++)
        {
            for (int m=0;m<tiles[0].length;m++)
            {
                if (tiles[l][m].equals("ground"))
                    addObject(new Block(), m*100, l*100);
                if (tiles[l][m].equals("fishbowl"))
                    addObject(new Fishbowl1(), m*100, l*112);
                if (tiles[l][m].equals("coin"))
                    addObject(new Coin(), m*104, l*109);
            }
        }
    }
    
    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_R))
            Mayflower.setWorld(new Level1());
    }
}