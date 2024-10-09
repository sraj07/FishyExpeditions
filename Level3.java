import mayflower.*;
import java.util.Random;

public class Level3 extends World {
    private String[][] tiles;
    
    private Cat cat0;
    private Cat cat1;
    private Block block;
    private Block block1;
    private Fish fish;
    private Ladder ladder;
    
    public Level3(int prevScore) 
    {
        setBackground("img/BG/BG-3.png");
        
        tiles = new String[7][12];
        buildWorld();
        
        //Mayflower.showBounds(true);
        
        fish = new Fish(false, false, prevScore);
        addObject(fish, 0, 500);
        
        cat0 = new Cat(150, 1075, 6);
        addObject(cat0, 500, 500);
        
        cat1 = new Cat(150, 1075, 6);
        addObject(cat1, 1050, 500);
        
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
        
        tiles[4][1] = "ladder";
        tiles[4][9] = "ladder";
        
        tiles[4][2] = "islandL";
        tiles[4][3] = "islandR";
        
        tiles[3][11] = "islandL";
        tiles[2][11] = "fishbowl";
        
        for (int l=0;l<tiles.length;l++)
        {
            for (int m=0;m<tiles[0].length;m++)
            {
                if (tiles[l][m].equals("ground"))
                    addObject(new Block(), m*100, l*100);
                if (tiles[l][m].equals("fishbowl"))
                    addObject(new Fishbowl3(), m*100, l*112);
                if (tiles[l][m].equals("ladder"))
                    addObject(new Ladder(), m*100+18, l*100-15);
                if (tiles[l][m].equals("islandL"))
                    addObject(new IslandLeft(), m*100, l*100);
                if (tiles[l][m].equals("islandR"))
                    addObject(new IslandRight(), m*100+18, l*100);
                if (tiles[l][m].equals("island"))
                    addObject(new Island(), m*100+18, l*100);
            }
        }
        addObject(new Ladder(), 900+18,400-15-160);
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
        int cat0X = 500 / 100;
        int cat1X = 1050 / 100;

        // Check if the coin is placed near the cats' paths
        if (col >= cat0X - 1 && col <= cat0X + 1 && row == 5) {
            return false; // Close to cat0's initial path
        }
        if (col >= cat1X - 1 && col <= cat1X + 1 && row == 5) {
            return false; // Close to cat1's initial path
        }

        // The position is valid if it's not too close to the cats' paths or ground
        return true;
    }
    
    public void act()
    {
        if (Mayflower.isKeyDown(Keyboard.KEY_R))
            Mayflower.setWorld(new Level1());
    }
}