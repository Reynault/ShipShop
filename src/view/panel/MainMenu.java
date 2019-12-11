package view.panel;

import controller.GameController;
import controller.ScreenController;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import model.ShipShop;
import view.ViewFactory;

import java.util.Observable;
import java.util.ResourceBundle;

/**
 * Main menu of the game, which includes buttons
 */
public class MainMenu implements PanelView {

    private GameController controller;
    private ShipShop model;
    private ScreenController screen;

    @FXML
    private Button exit;

    @FXML
    private Button newGame;

    @FXML
    private Button loadGame;

    public MainMenu(ShipShop model, GameController controller, ScreenController screen) {
        this.controller = controller;
        this.model = model;
        this.screen = screen;
    }

    @Override
    public void initialize(ResourceBundle resources) {

    }

    @FXML
    public void newGameBehavior(){
        PanelView view = ViewFactory.getEraView(controller, model, screen);
        screen.load(ScreenController.ERA_VIEW, view);
    }

    @FXML
    public void exitBehavior(){
        System.exit(0);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
