package minesweeper;

import java.util.Random;

/**
 * Created by kasra on 8/12/2016.
 */
public class Model {
    
    private Box[][] grid;
    
    private Difficulty diff = Difficulty.INTERMEDIATE;
    private int bombCount;
    
    public Model(Difficulty d) {
        diff = d;
        bombCount = diff.bomb;
        generateGrid(diff.size);
        //TODO create two constructors with default constructor
    }
    
    private void generateGrid(int size) {
        grid = new Box[size][size];
        int randomCount = diff.bomb;
        //there is a diff.bomb/(size*size) chance that a bomb is where we're looking happens
        Random random = new Random();
        for (int i = 0; i < size; ++i) {
            for (int j = 0; j < size; ++j) {
                int check = random.nextInt(1000);
                if (check < (1000*diff.bomb)/(size*size)) {
                    grid[i][j] = new Box(true);
                    randomCount--;
                } else {
                    grid[i][j] = new Box(false);
                }
            }
        }
        
        //calculate Box-ey things
        Box.frequencyCheck(grid);
    }
    
    public Difficulty getDiff() {
        return diff;
    }
    
    public int getBombCount() {
        return bombCount;
    }
    
    public Box get(int i, int j) {
        return grid[i][j];
    }
    
    public void dig(int i, int j) {
        get(i, j).dig();
    }
    
    enum Difficulty {
        BEGINNER(8, 10), INTERMEDIATE(16, 40), EXPERT(25, 100);
        int size;
        int bomb;
        Difficulty(int s, int b){
            size = s;
            bomb = b;
        }
    }
}
