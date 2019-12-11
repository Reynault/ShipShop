package view;

import controller.GameController;
import javafx.stage.Stage;
import model.ShipShop;
import view.panel.*;

/**
 * Factory that create all the views
 */
public class ViewFactory {

    public static PanelView getMainMenu(GameController controller, ShipShop shipShop, Stage primaryStage) {
        return new MainMenu(controller, shipShop, primaryStage);
    }

    public static PanelView getEraView(GameController controller, ShipShop model, Stage primaryStage) {
        return new EraView(controller, model, primaryStage);
    }

    public static PanelView getGameView(GameController controller) {
        return new GameView();
    }

    public static PanelView getLoadView(GameController controller) {
        return new LoadView();
    }

    public static PanelView getTacticView(GameController controller) {
        return new TacticView();
    }
}
