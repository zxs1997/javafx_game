package sample;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;

import java.util.Stack;

public class Main extends Application {



    @Override
    public void start(Stage primaryStage) throws Exception{

        MainCanvas mainCanvas = new MainCanvas();
        Pane pane = new Pane(mainCanvas);
        Scene scene = new Scene(pane);

        primaryStage.setTitle("Hello World");
        primaryStage.setScene(scene);
        primaryStage.show();


    }




    public static void main(String[] args) {
        launch(args);
    }
}
