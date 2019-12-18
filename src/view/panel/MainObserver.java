package view.panel;

import view.constant.GraphicConstant;
import view.constant.Views;

import java.util.HashMap;
import java.util.Observable;
import java.util.Observer;

public class MainObserver implements Observer {
    private PanelView current;

    private HashMap<Views, PanelView> views;

    public MainObserver() {
        views = new HashMap<>();

        views.put(Views.MENU, new MenuView(this));
        views.put(Views.ERA, new EraView(this));
        views.put(Views.TACTIC, new TacticView(this));
        views.put(Views.MAIN, new MainView(this));

//        setCurrent(views.get(Views.MENU));
    }

    public void setCurrent(PanelView current) {
        if(this.current != null) {
            this.current.close();
        }

        if(current != null) {
            this.current = current;
            this.current.open();
        }
    }

    @Override
    public void update(Observable o, Object arg) {
        if(current != null){
            current.update(o, arg);
        }
    }
}
