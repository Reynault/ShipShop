package view;

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

    // Current view
    private JPanel currentView;

    private JFrame frame;

    public MainObserver() {
        currentView = ViewFactory.getMainMenu();
        // Building frame
        frame = new JFrame(StringConstant.NAME_APPLICATION);
        buildFrame();
    }

    /**
     * BuildFrame is instantiating the main frame of the application
     * by adding the current view into it
     */
    private void buildFrame(){
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frame.getContentPane();

        // Setting dimensions
        frame.setPreferredSize(new Dimension(
                GraphicConstant.WIDTH,
                GraphicConstant.HEIGHT
        ));

        // Adding the current view to the frame
        contentPane.add(currentView);

        frame.pack();
        frame.setVisible(true);
    }

    public void changeCurrentView(JPanel view){
        currentView = view;
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
