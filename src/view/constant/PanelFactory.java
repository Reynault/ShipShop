package view.constant;

import view.panel.MainObserver;
import view.panel.MenuView;
import view.panel.PanelView;

public class PanelFactory {
    public static PanelView MAIN_MENU;

    public static PanelView getMainMenu(MainObserver mainObserver){
        if(MAIN_MENU == null){
            MAIN_MENU = new MenuView(mainObserver);
        }
        return MAIN_MENU;
    }
}
