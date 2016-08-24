package minesweeper;

import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * Created by kasra on 8/13/2016.
 */
public class Box {
    private boolean mine;
    private boolean flagged;
    private int frequency;
    private boolean dug;
    
    Box(boolean m) {
        mine = m;
        dug = false;
        flagged = false;
        frequency = 0;
    }
    
    public boolean isMine() {
        return mine;
    }
    
    public boolean isDug() {
        return dug;
    }
    
    public boolean isFlagged() {
        return flagged;
    }
    
    public void dig() {
        dug = true;
    }
    
    public int getFrequency() {
        return frequency;
    }
    
    
    
    private Text viewText;
    private Rectangle viewRect;
    
    public void setViewRect(Rectangle viewRect) {
        this.viewRect = viewRect;
    }
    
    public void setViewText(Text viewText) {
        this.viewText = viewText;
    }
    
    /**
     * Assumes input grid is square
     * @param input
     */
    public static void frequencyCheck(Box[][] input) {
        if (input.length != input[0].length) System.err.println("Input is not square.");
        
        int m = input.length - 1;
        for (int r = 0; r < input.length; ++r) {
            for (int c = 0; c < input[r].length; ++c) {
                int counter = 0;
                if (r != 0 && c != 0) counter += (input[r-1][c-1].mine)? 1: 0; //top left
                if (          c != 0) counter += (input[r  ][c-1].mine)? 1: 0; //left
                if (r != m && c != 0) counter += (input[r+1][c-1].mine)? 1: 0; //bottom left
                if (r != m)           counter += (input[r+1][c  ].mine)? 1: 0; //bottom
                if (r != m && c != m) counter += (input[r+1][c+1].mine)? 1: 0; //bottom right
                if (          c != m) counter += (input[r  ][c+1].mine)? 1: 0; //right
                if (r != 0 && c != m) counter += (input[r-1][c+1].mine)? 1: 0; //top right
                if (r != 0)           counter += (input[r-1][c  ].mine)? 1: 0; //top
                input[r][c].frequency = counter;
            }
        }
    }
}
