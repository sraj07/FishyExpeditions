import mayflower.*;
import java.util.Random;

public class Level2 extends World {
    private String[][] tiles;
    
    private Cat cat;
    private Block block;
    private Block block1;
    private Ladder ladder;
    private Fish fish;
    private Level2Checkpoint checkpoint;
    
    public Level2(int prevScore) 
    {
        setBackground("img/BG/BG-2.png");
        
        tiles = new String[7][12];
        buildWorld();
        
        //Mayflower.showBounds(true);
        
        fish = new Fish(true, false, prevScore);
        addObject(fish, 0, 500);
        
        cat = new Cat(370, 800, 6);
        addObject(cat, 500, 500);
        
        checkpoint = new Level2Checkpoint();
        addObject(checkpoint, 1190, 0);
        
        generateCoins(4);
        
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
        
        tiles[5][2] = "ground";
        tiles[5][3] = "ground";
        tiles[4][3] = "ground";
        
        tiles[5][8] = "ground";
        tiles[5][9] = "ground";
        tiles[4][9] = "ground";

        for (int l=0;l<tiles.length;l++)
        {
            for (int m=0;m<tiles[0].length;m++)
            {
                if (tiles[l][m].equals("ground"))
                {
                    addObject(new Block(), m*100, l*100);
                }
            }
        }
    }
    
    public void generateCoins(int coins) {
        Random random = new Random();
        int numCoins = coins;  // Adjust the number of coins to your needs

        for (int i = 0; i < numCoins; i++) {
            int row = random.nextInt(4) + 3; // Restrict to rows 3 and below (row 3 to row 6)
            int col = random.nextInt(tiles[0].length);  // Generate a random column

            // Ensure the coin doesn't overlap with ground or cat
            while (tiles[row][col].equals("ground") || tiles[row][col].equals("cat")) {
                row = random.nextInt(4) + 3; // Regenerate if overlapping
                col = random.nextInt(tiles[0].length);
            }

            // Add the coin to the grid at the selected row and column
            tiles[row][col] = "coin";
            addObject(new Coin(), col * 100, row * 100);  // Adjust coin size/position
        }
    }
    
    // Helper method to check if a position is valid for placing a coin
    public boolean isValidCoinPosition(int row, int col)
    {
        // Avoid placing coins near the initial positions of the cats
        int cat0X = 370 / 800; 

        // Check if the coin is placed near the cat's path
        if (col >= cat0X - 1 && col <= cat0X + 1 && row == 5) {
            return false; // Close to cat0's initial path
        }

        // The position is valid if it's not too close to the cat's path or ground
        return true;
    }

    
    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_R))
            Mayflower.setWorld(new Level1());
    }
}