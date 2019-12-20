import controller.GameController;
import model.ShipShop;
import view.panel.MainObserver;

public class TestView {
    public static void main(String[] args) {
        // Initialise model
        ShipShop shipShop = new ShipShop();

        // Initialise controller
        GameController controller = new GameController(shipShop);

        // Initialise views
        MainObserver mainObserver = new MainObserver(controller);

        // Adding controller
        shipShop.addObserver(mainObserver);

    }
}
