package view.panel;

import controller.GameController;
import view.constant.GraphicConstant;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class BadLoadView extends PanelView{

    private JPanel message;
    private JButton cancel;

    public BadLoadView(MainObserver mainObserver, GameController controller) {
        super(mainObserver, controller);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_BAD_LOAD, GraphicConstant.HEIGHT_BAD_LOAD)
        );

        this.setLayout(new BorderLayout());

        this.add(message, BorderLayout.CENTER);
        this.add(cancel, BorderLayout.SOUTH);

        buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
