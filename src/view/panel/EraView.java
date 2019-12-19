package view.panel;

import view.constant.GraphicConstant;
import view.constant.StringConstant;
import view.constant.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
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
        this.title = new JLabel(StringConstant.ERA_TITLE, SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.PLAIN, 35));
        title.setBorder(new EmptyBorder(10,10,40,10));

        // Setting JComboBox
        String[] choicesString = {"Modern", "XIV"};
        choices = new JComboBox<>(choicesString);

        // List renderer
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        choices.setRenderer(renderer);

        // Setting cancel button
        cancel = new JButton(StringConstant.CANCEL_BUTTON);
        cancel.setPreferredSize(new Dimension(50,50));
        cancel.addActionListener(new CancelListener());

        // Setting validate button
        validate = new JButton(StringConstant.VALIDATE_BUTTON);
        validate.addActionListener(new ValidateListener());

        // Setting layout
        this.setLayout(new BorderLayout());

        this.add(title, BorderLayout.NORTH);
        this.add(choices, BorderLayout.CENTER);

        JPanel bottom = new JPanel();

        bottom.setLayout(new GridLayout(1, 2));
        bottom.setBorder(new EmptyBorder(20,10,10,10));
        this.add(bottom, BorderLayout.SOUTH);

        bottom.add(cancel);
        bottom.add(validate);

        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    class CancelListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().setCurrent(Views.MENU);
        }
    }

    class ValidateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().setCurrent(Views.TACTIC);
        }
    }
}
