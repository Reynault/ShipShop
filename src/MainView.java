import controller.GameController;
import controller.ScreenController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import model.ShipShop;
import view.ViewFactory;
import view.panel.PanelView;

public class MainView extends Application {

    static GameController controller;
    static ShipShop model;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // init Views
        primaryStage.setScene(new Scene(new Pane()));
        ScreenController screenController = new ScreenController(primaryStage);

        PanelView mainMenu = ViewFactory.getMainMenu(model, controller, screenController);
        screenController.load(ScreenController.MAIN_MENU, mainMenu);

        primaryStage.show();

//        FXMLLoader loader = new FXMLLoader();
//
//        ScreenController screenController = new ScreenController(
//                primaryStage.getScene(),
//                loader
//        );
//
//        screenController.addScreen(
//                ScreenController.MAIN_MENU,
//                loader.load(getClass().getResource("fxml/main_menu.fxml")),
//                new MainMenu(controller, model)
//        );
//
////        PanelView mainMenu = ViewFactory.getMainMenu(controller, model, primaryStage);
////
////        loader.setLocation(getClass().getResource("fxml/mainMenu.fxml"));
////        loader.setControllerFactory(IC -> mainMenu);
////
////        primaryStage.setTitle("ShipShop");
////        primaryStage.setScene(new Scene(loader.load()));
////        primaryStage.show();
////
////        model.addObserver(mainMenu);
////        FXMLLoader loader = new FXMLLoader();
////        loader.setLocation(getClass().getResource("fxml/mainMenu.fxml"));
////        primaryStage.setTitle("ShipShop");
////        primaryStage.setScene(new Scene(loader.load()));
////        primaryStage.show();
    }

    public static void main(String[] args) {
        // Model initialisation
        model = new ShipShop();
        controller = new GameController(model);

        // View initialisation
        launch();
    }
}
