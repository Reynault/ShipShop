package model.rmi;

import model.ShipShop;
import model.ShipShopInterface;

import javax.naming.NamingException;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ShipShopClient2 {
    public static void main(String[] args) throws NamingException, RemoteException, NotBoundException {
        Registry registry = LocateRegistry.getRegistry(8080);

        ShipShopInterface shipshop = (ShipShopInterface) registry.lookup("shipshop");

        // Initialise model
        ShipShop jeu = shipshop.getObj();

        System.out.println("Life of the first player : " + jeu.getLife(0));
    }

}
