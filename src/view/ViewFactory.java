package view;

import view.panel.*;

import javax.swing.*;

/**
 * Factory that create all the views
 */
public class ViewFactory {
    private static JPanel mainMenu = new MainMenu();
    private static JPanel eraView = new EraView();
    private static JPanel loadView = new LoadView();
    private static JPanel gameView = new GameView();
    private static JPanel tacticView = new TacticView();

    public static JPanel getMainMenu() {
        return mainMenu;
    }

    public static JPanel getEraView() {
        return eraView;
    }

    public static JPanel getGameView() {
        return gameView;
    }

    public static JPanel getLoadView() {
        return loadView;
    }

    public static JPanel getTacticView() {
        return tacticView;
    }
}
