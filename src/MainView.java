import controller.GameController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import model.ShipShop;
import view.MainObserver;

public class MainView extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception{
        Parent root = FXMLLoader.load(getClass().getResource("sample.fxml"));
        primaryStage.setTitle("Hello World");
        primaryStage.setScene(new Scene(root, 300, 275));
        primaryStage.show();
    }

    public static void main(String[] args) {
        ShipShop shipShop = new ShipShop();
        GameController controller = new GameController(shipShop);
        MainObserver mainObserver = new MainObserver(controller);

        shipShop.addObserver(mainObserver);

        launch(args);
    }
}
