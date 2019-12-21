package view.panel;

import controller.GameController;
import view.constant.GraphicConstant;
import view.constant.StringConstant;
import view.constant.Views;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class EndGameView extends PanelView {

    private JLabel message;
    private JButton exit;

    public EndGameView(MainObserver mainObserver, GameController controller) {
        super(mainObserver, controller);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_END_GAME, GraphicConstant.HEIGHT_END_GAME)
        );

        this.setLayout(new BorderLayout());

        message = new JLabel(StringConstant.END_GAME_TITLE+"win", SwingConstants.CENTER);
        message.setFont(new Font("Dialog", Font.PLAIN, 35));

        this.add(message, BorderLayout.CENTER);

        exit = new JButton(StringConstant.EXIT_BUTTON);
        exit.addActionListener(new ExitListener());

        this.add(exit, BorderLayout.SOUTH);

        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public class ExitListener implements ActionListener{

        @Override
        public void actionPerformed(ActionEvent e) {
            mainObserver.setCurrent(Views.MENU);
        }
    }
}
