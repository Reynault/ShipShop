package view.panel;

import view.constant.GraphicConstant;

import javax.swing.*;
import java.awt.*;
import java.util.Observable;

public class EraView extends PanelView {

    private JLabel title;

    public EraView(MainObserver mainObserver) {
        super(mainObserver);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_ERA_TACTIC, GraphicConstant.HEIGHT_ERA_TACTIC)
        );

        // Setting title


        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
