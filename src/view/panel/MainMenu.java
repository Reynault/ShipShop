package view.panel;

import view.constant.StringConstant;

import javax.swing.*;

/**
 * Main menu of the game, which includes buttons
 */
public class MainMenu extends JPanel {

    private JButton[] buttons;
    private JLabel title;

    public MainMenu() {
        // Setting layout manager


        // Setting buttons
        buttons = new JButton[3];

        buttons[0] = new JButton(StringConstant.MAIN_MENU_NEW_GAME);
        buttons[1] = new JButton(StringConstant.MAIN_MENU_LOAD_GAME);
        buttons[2] = new JButton(StringConstant.MAIN_MENU_EXIT);

        // Adding buttons in the panel


        // Adding buttons behavior


        // Setting Label
        title = new JLabel(StringConstant.MAIN_MENU_TITLE);

        // Adding Label


        // Setting background

    }
}
