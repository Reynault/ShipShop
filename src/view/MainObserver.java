package view;

import controller.GameController;
import view.constant.GraphicConstant;
import view.constant.StringConstant;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;
import java.util.Observer;

/**
 * MainObserver contains the main frame of the application,
 * its the observer of ShipShop
 */
public class MainObserver implements Observer {


    // Controller
    private GameController controller;


    public MainObserver(GameController controller) {
        this.controller = controller;
        ViewFactory.getMainMenu(controller);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
