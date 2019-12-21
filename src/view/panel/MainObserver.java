package view.panel;

import controller.GameController;
import view.constant.StringConstant;
import view.constant.Views;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class MainObserver implements Observer {
    private PanelView current;
    private GameController controller;
    private HashMap<Views, PanelView> views;

    private String chosenTactic;
    private String chosenEra;
    private String endMessage;

    public MainObserver(GameController controller) {
        this.controller = controller;

        this.chosenEra = "";
        this.chosenTactic = "";
        this.endMessage = StringConstant.WIN_MESSAGE;

        views = new HashMap<>();

        views.put(Views.MENU, new MenuView(this, controller));
        views.put(Views.ERA, new EraView(this, controller));
        views.put(Views.TACTIC, new TacticView(this, controller));
        views.put(Views.MAIN, new MainView(this, controller));
        views.put(Views.END_GAME, new EndGameView(this, controller));
        views.put(Views.BAD_LOAD, new BadLoadView(this, controller));

        setCurrent(Views.MENU);
    }

    public void setCurrent(Views current) {
        for (Views view : views.keySet()) {
            views.get(view).close();
        }

        if (this.views.containsKey(current)) {
            if (current == Views.MAIN) {
                this.views.put(Views.MAIN, new MainView(this, controller));
            }
            this.current = this.views.get(current);
            this.current.open();
        }
    }

    public void openView(Views view) {
        if (this.views.containsKey(view)) {
            this.views.get(view).open();
        }
    }

    public void closeView(Views view) {
        if (this.views.containsKey(view)) {
            this.views.get(view).close();
        }
    }

    public boolean isCurrentView(Views view) {
        return current == views.get(view);
    }

    @Override
    public void update(Observable o, Object arg) {
        if (this.current != null) {
            this.current.update(o, arg);
        }
    }

    public void setChosenTactic(String chosenTactic) {
        this.chosenTactic = chosenTactic;
    }

    public void setChosenEra(String chosenEra) {
        this.chosenEra = chosenEra;
    }

    public String getChosenTactic() {
        return chosenTactic;
    }

    public String getChosenEra() {
        return chosenEra;
    }

    public String getEndMessage() {
        return endMessage;
    }

    public void setEndMessage(String message) {
        endMessage = message;
    }
}
