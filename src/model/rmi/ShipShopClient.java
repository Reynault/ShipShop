package model.rmi;

import controller.GameController;
import model.LiaisonRMI;
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
        LiaisonRMI liaisonRMI = new LiaisonRMI(shipshop);

        // Initialise controller
        GameController controller = new GameController(liaisonRMI);

        // Initialise views
        MainObserver mainObserver = new MainObserver(controller);

        // Adding controller
        liaisonRMI.addObserver(mainObserver);
    }

}
