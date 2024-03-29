package view.panel;

import controller.GameController;
import model.constant.UpdateObserver;
import view.constant.GraphicConstant;
import view.constant.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import static view.constant.StringConstant.*;

public class MenuView extends PanelView {
    private JButton[] buttons;
    private JLabel title;

    private final int NEW = 0;
    private final int LOAD = 1;
    private final int EXIT = 2;

    private final int NB_ROWS = 4;
    private final int NB_COL = 1;

    public MenuView(MainObserver mainObserver, GameController controller) {
        super(mainObserver, controller);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_MENU, GraphicConstant.HEIGHT_MENU)
        );

        // Setting buttons
        buttons = new JButton[3];

        buttons[NEW] = new JButton(NEW_BUTTON);
        buttons[LOAD] = new JButton(CONTINUE_BUTTON);
        buttons[EXIT] = new JButton(EXIT_BUTTON);

        buttons[NEW].addActionListener(new NewGameListener());
        buttons[LOAD].addActionListener(new ContinueListener());
        buttons[EXIT].addActionListener(new ExitListener());

        // Setting label
        title = new JLabel(MENU_TITLE, SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.PLAIN, 35));


        // Setting label
        this.setLayout(new GridLayout(NB_ROWS, NB_COL));

        this.add(title);

        for (int i = 0; i < buttons.length; i++) {
            this.add(buttons[i]);
        }

        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {
        UpdateObserver val = (UpdateObserver)arg;
        switch (val){
            case BAD_LOAD:
                mainObserver.setCurrent(Views.BAD_LOAD);
                break;
            case LOAD:{
                mainObserver.setCurrent(Views.MAIN);
                mainObserver.update(controller.getModel(), arg);
                break;
            }
        }
    }

    class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            System.exit(0);
        }
    }

    class NewGameListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainObserver.setCurrent(Views.ERA);
        }
    }

    class ContinueListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            controller.loadGame();
        }
    }
}
