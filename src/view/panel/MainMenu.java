package view.panel;

import controller.GameController;
import model.game.Game;
import view.constant.StringConstant;

import javax.swing.*;

/**
 * Main menu of the game, which includes buttons
 */
public class MainMenu implements PanelView{

    private GameController controller;


    public MainMenu(GameController controller) {
        this.controller = controller;
    }

    @Override
    public void update(Object arg) {

    }
}
