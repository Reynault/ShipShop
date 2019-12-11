package view.panel;

import controller.GameController;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import model.ShipShop;
import view.ViewFactory;

import java.io.IOException;
import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Main menu of the game, which includes buttons
 */
public class MainMenu implements PanelView {

    private Stage primaryStage;

    private GameController controller;
    private ShipShop model;

    @FXML
    private Button exit;

    @FXML
    private Button newGame;

    @FXML
    private Button loadGame;


    public MainMenu(GameController controller, ShipShop model, Stage primaryStage) {
        this.controller = controller;
        this.model = model;
        this.primaryStage = primaryStage;
    }

    @Override
    public void initialize(ResourceBundle resources) {

    }

    @FXML
    public void newGameBehavior(){
        PanelView eraView = ViewFactory.getEraView(controller, model, primaryStage);

        try {
            primaryStage.setScene(new Scene(
                    FXMLLoader.load(getClass().getResource("fxml/loadView.fxml"))));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void exitBehavior(){
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
