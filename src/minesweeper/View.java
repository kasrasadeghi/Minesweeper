package minesweeper;

import javafx.geometry.HPos;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;


/**
 * Created by kasra on 8/9/2016.
 */
public class View extends GridPane {
    
    private Model model;
    private Controller controller;
    private Pane gamePane;
    private static final int side = 20;
    
    public View(Model _m, Controller _c) {
        
        model = _m;
        controller = _c;
        // we need 2 groups, one top group one bottom group.
        // the top group has: mine count, reset button (left click reset, right click options), timer
        // the bottom group has a canvas that paints the game.
        {
            int width = 100;
            int height = 50;
            Canvas mine = new Canvas();
            mine.setHeight(height);
            mine.setWidth(width);
            GraphicsContext minegc = mine.getGraphicsContext2D();
            
            //paintGame mine count
            minegc.setFill(Color.RED);
            minegc.fillRect(0, 0, mine.getWidth(), mine.getHeight());
            
            this.add(mine, 0, 0);
        }
        
        {
            BorderPane topmiddle = new BorderPane();
            topmiddle.setBackground(new Background(new BackgroundFill(Color.WHITE, null, null)));
            Button resetButton = new Button("Reset");
            
            topmiddle.setCenter(resetButton);
            setHgrow(topmiddle, Priority.ALWAYS);
            setVgrow(topmiddle, Priority.ALWAYS);
            
            this.add(topmiddle, 1, 0);
        }
        
        {
            int width = 100;
            int height = 50;
            Canvas timer = new Canvas();
            timer.setHeight(height);
            timer.setWidth(width);
            setHalignment(timer, HPos.RIGHT);
            GraphicsContext timergc = timer.getGraphicsContext2D();
            
            //paintGame timer
            timergc.setFill(Color.BLUE);
            timergc.fillRect(0, 0, timer.getWidth(), timer.getHeight());
            
            this.add(timer, 2, 0);
        }
    
        
        //set gamePane to instance var?
        gamePane = new Pane();
        {
            int size = model.getDiff().size;
            int width = size * side;
            int height = size * side;
            gamePane.setBackground(new Background(new BackgroundFill(Color.BLACK, null, null)));
            gamePane.setPrefHeight(height);
            gamePane.setPrefWidth(width);
            setHalignment(gamePane, HPos.CENTER);
        }
        
        controller.assignView(this);
        //region testcode
        paintGame(); //maybe "paintGame()", and code should resemble the nested for-loop below
        this.add(gamePane, 0, 1, 3, 1);
        //endregion
    }
    
    public void paintGame() {
        Image base = ResourceManager.getImage("base.png");
        Image dug = ResourceManager.getImage("dug.png");
        gamePane.getChildren().clear();
        for (int i = 0; i < model.getDiff().size; ++i) {
            for (int j = 0; j < model.getDiff().size; ++j) {
                final int ii = i;
                final int jj = j;
                Box box = model.get(i, j);
                
                //TODO reload images button
                //clear the hashmap and reloads everything in the keys
                ImageView iv = new ImageView();
                if (box.isDug()) {
                    iv.setImage(dug);
                } else {
                    iv.setImage(base);
                }
                    
                iv.setFitHeight(side);
                iv.setFitWidth(side);
                iv.setX(side * i);
                iv.setY(side * j);
                iv.setOnMouseClicked(e -> {
                    if (e.getButton() == MouseButton.PRIMARY
                            && !box.isDug()) {
                        controller.clickPrimary(ii, jj);
                    }
                });
                
                gamePane.getChildren().add(iv);
    
                Text text = new Text(side * i + side/2 - side/5, side * j + side/2 + side/4, "");
    
//                Rectangle rect = new Rectangle(side * i, side * j, side, side);
//                rect.setFill(box.isDug()? Color.AQUA : Color.GREY);
//
//                rect.setOnMouseClicked(e->
//                {
//                    if (e.getButton() == MouseButton.PRIMARY) {
//                        if (!box.isDug()) controller.rectMouseClick(ii, jj);
//                    }
//                });
//                box.setViewRect(rect);
                box.setViewText(text);
    
                gamePane.getChildren().add(text);
            }
        }
    }
}
