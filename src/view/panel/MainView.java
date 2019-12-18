package view.panel;

import java.util.Observable;

public class MainView extends PanelView{
    public MainView(MainObserver mainObserver) {
        super(mainObserver);
    }

    @Override
    public void update(Observable o, Object arg) {

    }
}
