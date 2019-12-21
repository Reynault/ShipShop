package model.rmi;

import controller.GameController;
import model.ShipShop;
import model.ShipShopInterface;
import view.panel.MainObserver;

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ShipShopClient {
    public static void main(String[] args) throws NamingException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);

        ShipShopInterface shipshop = (ShipShopInterface) registry.lookup("shipshop");

        // Initialise model
        ShipShop jeu = shipshop.getObj();

        // Initialise controller
        GameController controller = new GameController(jeu);

        // Initialise views
        MainObserver mainObserver = new MainObserver(controller);

        // Adding controller
        jeu.addObserver(mainObserver);
    }

}
