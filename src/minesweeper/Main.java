package minesweeper;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;


public class Main extends Application {
    
    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Minesweeper");
        
        Model model = new Model(Model.Difficulty.INTERMEDIATE);
        Controller cont = new Controller(model);
        View root = new View(model, cont);
        
        primaryStage.setScene(new Scene(root));
        primaryStage.initStyle(StageStyle.DECORATED);
        primaryStage.sizeToScene();
        primaryStage.setResizable(false);
        primaryStage.show();
    }


    public static void main(String[] args) {
        launch(args);
    }
}
