package minesweeper;

import javafx.scene.layout.Pane;

public class Controller {
    private Model model;
    private View view;
    public Controller(Model m) {
        model = m;
    }
    
    //TODO better name
    public void clickPrimary(int i, int j) {
        model.dig(i, j);
        
        view.paintGame();
    }
    
    public void assignView(View v) {
        view = v;
    }
}
