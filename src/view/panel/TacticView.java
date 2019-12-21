package view.panel;

import controller.GameController;
import view.constant.GraphicConstant;
import view.constant.StringConstant;
import view.constant.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Observable;

public class TacticView extends PanelView{

    private String chosenTactic;

    private JLabel title;
    private JComboBox<String> choices;
    private JButton cancel;
    private JButton validate;

    public static final String RANDOM = "Random";
    public static final String LINEAR = "Linear";

    public TacticView(MainObserver mainObserver, GameController controller) {
        super(mainObserver, controller);

        // Setting prefered size
        this.setPreferredSize(
                new Dimension(GraphicConstant.WIDTH_ERA_TACTIC, GraphicConstant.HEIGHT_ERA_TACTIC)
        );

        // Setting title
        this.title = new JLabel(StringConstant.TACTIC_TITLE, SwingConstants.CENTER);
        title.setFont(new Font("Dialog", Font.PLAIN, 35));
        title.setBorder(new EmptyBorder(10,10,40,10));

        // Setting JComboBox
        String[] choicesString = {RANDOM, LINEAR};
        choices = new JComboBox<>(choicesString);

        // List renderer
        DefaultListCellRenderer renderer = new DefaultListCellRenderer();
        renderer.setHorizontalAlignment(DefaultListCellRenderer.CENTER);
        choices.setRenderer(renderer);

        // Setting cancel button
        cancel = new JButton(StringConstant.CANCEL_BUTTON);
        cancel.setPreferredSize(new Dimension(50,50));
        cancel.addActionListener(new TacticView.CancelListener());

        // Setting validate button
        validate = new JButton(StringConstant.VALIDATE_BUTTON);
        validate.addActionListener(new TacticView.ValidateListener());

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
            if(mainObserver.isCurrentView(Views.MAIN)){
                mainObserver.closeView(Views.TACTIC);
            }else {
                mainObserver.setCurrent(Views.ERA);
            }
        }
    }

    class ValidateListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            chosenTactic = (String) choices.getSelectedItem();
            mainObserver.setChosenTactic(chosenTactic);

            if(mainObserver.isCurrentView(Views.MAIN)){
                controller.setTactic(chosenTactic);
                mainObserver.closeView(Views.TACTIC);
            }else {
                mainObserver.setCurrent(Views.MAIN);
                controller.createGame(mainObserver.getChosenEra(), chosenTactic);
            }
        }
    }
}
