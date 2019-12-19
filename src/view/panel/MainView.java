package view.panel;

import view.constant.StringConstant;
import view.constant.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

import static view.constant.GraphicConstant.HEIGHT_MAIN;
import static view.constant.GraphicConstant.WIDTH_MAIN;

public class MainView extends PanelView {
    // Data

    // Placement buttons
    private JButton[] shipPlacement;

    private JButton[][] ennemy;
    private JButton[][] player;
    private JPanel ennemyGrid;
    private JPanel playerGrid;
    private GridBagConstraints c;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem changeTactic;
    private JMenuItem save;
    private JMenuItem exit;

    // Constants
    private final int WIDTH_PANEL = 10;
    private final int HEIGHT_PANEL = 10;

    private final int WIDTH_SIDEBAR = 1;

    private final int CRUISER = 0;
    private final int SUBMARINE = 1;
    private final int TORPEDO = 2;
    private final int AIRCRAFT = 3;
    private final int READY = 4;

    public MainView(MainObserver mainObserver) {
        super(mainObserver);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(WIDTH_MAIN, HEIGHT_MAIN)
        );

        // Setting main layout
        this.setLayout(new BorderLayout());

        // Setting menu bar
        menuBar = new JMenuBar();
        menu = new JMenu(StringConstant.MENUBAR_TITLE);

        menuBar.add(menu);

        changeTactic = new JMenuItem(StringConstant.CHANGE_TACTIC);
        changeTactic.addActionListener(new ChangeTacticListener());
        save = new JMenuItem(StringConstant.SAVE_GAME);
        exit = new JMenuItem(StringConstant.EXIT_GAME);
        exit.addActionListener(new ExitListener());


        menu.add(changeTactic);
        menu.add(save);
        menu.add(exit);

        this.add(menuBar, BorderLayout.NORTH);

        JPanel global = new JPanel();
        global.setLayout(new GridLayout(1, 2));
        this.add(global, BorderLayout.CENTER);

        JPanel left = new JPanel();
        JPanel right = new JPanel();

        left.setLayout(new BorderLayout());
        right.setLayout(new BorderLayout());

        global.add(left);
        global.add(right);

        // Grid bag constraint
        c = new GridBagConstraints();

        // Setting ennemy grid
        ennemyGrid = new JPanel();
        ennemyGrid.setLayout(new GridBagLayout());

        right.add(ennemyGrid, BorderLayout.CENTER);


        // Filling in ennemy grid
        ennemy = new JButton[HEIGHT_PANEL][WIDTH_PANEL];

        int width_cell = 50;
        int height_cell = 50;

        for (int i = 0; i < HEIGHT_PANEL; i++) {
            for (int j = 0; j < WIDTH_PANEL; j++) {
                ennemy[i][j] = new JButton();
                ennemy[i][j].setPreferredSize(new Dimension(
                        width_cell, height_cell
                ));

                c.gridx = i;
                c.gridy = j;

                ennemyGrid.add(ennemy[i][j], c);
            }
        }

        // Setting player grid
        playerGrid = new JPanel();
        playerGrid.setLayout(new GridBagLayout());

        left.add(playerGrid, BorderLayout.CENTER);
        left.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Filling player grid
        player = new JButton[HEIGHT_PANEL][WIDTH_PANEL];

        for (int i = 0; i < HEIGHT_PANEL; i++) {
            for (int j = 0; j < WIDTH_PANEL; j++) {
                player[i][j] = new JButton();
                player[i][j].setPreferredSize(new Dimension(
                        width_cell, height_cell
                ));

                c.gridx = i;
                c.gridy = j;

                playerGrid.add(player[i][j], c);
            }
        }

        // Setting ship placement buttons
        shipPlacement = new JButton[5];

        shipPlacement[CRUISER] = new JButton("0");
        shipPlacement[CRUISER].addActionListener(new ShipListener());

        shipPlacement[SUBMARINE] = new JButton("1");
        shipPlacement[SUBMARINE].addActionListener(new ShipListener());

        shipPlacement[AIRCRAFT] = new JButton("2");
        shipPlacement[AIRCRAFT].addActionListener(new ShipListener());

        shipPlacement[TORPEDO] = new JButton("3");
        shipPlacement[TORPEDO].addActionListener(new ShipListener());

        shipPlacement[READY] = new JButton(StringConstant.READY_BUTTON);
        shipPlacement[READY].addActionListener(new ReadyListener());

        // Setting side bar with ship placement
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(shipPlacement.length, WIDTH_SIDEBAR));

        left.add(sidebar, BorderLayout.WEST);

        // Adding buttons
        for (JButton button : shipPlacement) {
            sidebar.add(button);
        }

        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public class ReadyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ShipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }


    public class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().setCurrent(Views.MENU);
        }
    }

    public class ChangeTacticListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().openView(Views.TACTIC);
        }

    }

}
