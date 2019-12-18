package view.panel;

import view.constant.GraphicConstant;
import view.constant.StringConstant;

import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.util.Observable;

public class EraView extends PanelView {

    private JLabel title;
    private JComboBox<String> choices;
    private JButton cancel;
    private JButton validate;

    public EraView(MainObserver mainObserver) {
        super(mainObserver);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_ERA_TACTIC, GraphicConstant.HEIGHT_ERA_TACTIC)
        );

        // Setting title
        this.title = new JLabel(StringConstant.ERA_TITLE);

        // Setting JComboBox
        String[] choix = {"Modern", "XIV"};
        this.choices = new JComboBox<>(choix);

        // Setting cancel button
        cancel = new JButton(StringConstant.CANCEL_BUTTON);

        // Setting validate button
        validate = new JButton(StringConstant.VALIDATE_BUTTON);

        // Setting layout
        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(choices, BorderLayout.CENTER);

        JPanel bottom = new JPanel();
        bottom.setLayout(new GridLayout(1, 2));
        this.add(bottom, BorderLayout.SOUTH);

        bottom.add(cancel);
        bottom.add(validate);

        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
