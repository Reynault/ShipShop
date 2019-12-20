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

import static javax.swing.SwingConstants.CENTER;

public class BadLoadView extends PanelView{

    private JLabel message;
    private JButton cancel;

    public BadLoadView(MainObserver mainObserver, GameController controller) {
        super(mainObserver, controller);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_BAD_LOAD, GraphicConstant.HEIGHT_BAD_LOAD)
        );

        this.setLayout(new BorderLayout());

        message = new JLabel(StringConstant.BAD_LOAD, CENTER);
        message.setFont(new Font("Dialog", Font.PLAIN, 35));

        cancel = new JButton(StringConstant.EXIT_BUTTON);
        cancel.addActionListener(new CancelListener());

        this.add(message, BorderLayout.CENTER);
        this.add(cancel, BorderLayout.SOUTH);

        buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private class CancelListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {
            mainObserver.setCurrent(Views.MENU);
        }
    }
}
