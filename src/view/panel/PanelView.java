package view.panel;

import controller.GameController;
import view.constant.StringConstant;

import javax.swing.*;
import java.util.Observable;

public abstract class PanelView extends JPanel {
    private JFrame frame;
    private MainObserver mainObserver;
    private GameController controller;

    public PanelView(MainObserver mainObserver, GameController controller) {
        this.mainObserver = mainObserver;
        this.frame = new JFrame(StringConstant.APP_TITLE);
        this.controller = controller;
    }

    public void open() {
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
    }

    public void close() {
        frame.setVisible(false);
    }

    void buildFrame() {
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        JPanel contentPane = (JPanel) frame.getContentPane();

        contentPane.setPreferredSize(
                this.getPreferredSize()
        );

        contentPane.add(this);

        frame.pack();
    }

    public abstract void update(Observable o, Object arg);

    public MainObserver getMainObserver() {
        return mainObserver;
    }
}
