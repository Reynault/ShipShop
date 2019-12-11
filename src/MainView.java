import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ShipShop;
import view.ViewFactory;
import view.panel.*;

public class MainView extends Application {

    static GameController controller;
    static ShipShop shipShop;

    @Override
    public void start(Stage primaryStage) throws Exception {
        // init Views
        FXMLLoader loader = new FXMLLoader();
        PanelView mainMenu = ViewFactory.getMainMenu(controller, shipShop, primaryStage);

        loader.setLocation(getClass().getResource("fxml/mainMenu.fxml"));
        loader.setControllerFactory(IC -> mainMenu);

        primaryStage.setTitle("ShipShop");
        primaryStage.setScene(new Scene(loader.load()));
        primaryStage.show();

        shipShop.addObserver(mainMenu);
    }

    public static void main(String[] args) {
        // Model initialisation
        shipShop = new ShipShop();
        controller = new GameController(shipShop);

        launch(args);
    }
}
