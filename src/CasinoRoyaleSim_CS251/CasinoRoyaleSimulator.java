package CasinoRoyaleSim_CS251;

import javafx.application.Application;
import javafx.stage.Stage;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.*;
import javafx.scene.control.Button;

public class CasinoRoyaleSimulator extends Application {
    private Button pokerButton;
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        pokerButton = new Button();
        pokerButton.setText("Poker");
    }
}
