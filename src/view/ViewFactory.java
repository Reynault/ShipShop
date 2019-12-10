package view;

import controller.GameController;
import view.panel.*;

import javax.swing.*;

/**
 * Factory that create all the views
 */
public class ViewFactory {
    public static PanelView getMainMenu(GameController controller) {
        return new MainMenu(controller);
    }

    public static PanelView getEraView(GameController controller) {
        return new EraView();
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
