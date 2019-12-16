package controller;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import view.panel.PanelView;

import java.io.IOException;
import java.util.HashMap;

public class ScreenController {
    public static String MAIN_MENU = "main_menu";
    public static String ERA_VIEW = "era_view";
    public static String TACTIC_VIEW = "tactic_view";

    private HashMap<String, FXMLLoader> panes;
    private Stage primaryStage;

    public ScreenController(Stage primaryStage) {
        panes = new HashMap<>();
        prepareViews();
        this.primaryStage = primaryStage;
    }

    public void load(String name, PanelView mainMenu) {
        try {
            if (panes.containsKey(name)) {
                FXMLLoader loader = panes.get(name);
                loader.setControllerFactory(IC -> mainMenu);
                Parent node = loader.load();
                primaryStage.setScene(new Scene(node));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void prepareViews() {
        createNewPane("fxml/mainMenu.fxml", MAIN_MENU);
        createNewPane("fxml/chooseEra.fxml", ERA_VIEW);
    }

    private void createNewPane(String url, String view) {
        try {
            panes.put(view, new FXMLLoader(ClassLoader.getSystemResource(url)));
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}