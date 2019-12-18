package trash.view;

import controller.GameController;
import trash.view.panel.MainMenu;
import model.ShipShop;
import trash.view.panel.*;

/**
 * Factory that create all the views
 */
public class ViewFactory {

    public static PanelView getMainMenu(ShipShop model, GameController controller, ScreenController screenController) {
        return new MainMenu(model, controller, screenController);
    }

    public static PanelView getEraView(GameController controller, ShipShop model, ScreenController primaryStage) {
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
