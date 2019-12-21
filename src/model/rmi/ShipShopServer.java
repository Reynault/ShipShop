package model.rmi;

import model.ShipShop;

import javax.naming.NamingException;
import java.rmi.AlreadyBoundException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;

public class ShipShopServer {
    public static void main(String[] args) throws RemoteException, NamingException, AlreadyBoundException {
        String name = "shipshop";

        System.out.println("Constructing server implementation...");

        ShipShop impl = new ShipShop();

        System.out.println("Binding server implementation to registry...");

        Registry registry = LocateRegistry.createRegistry(8080);
        registry.bind(name, impl);

        System.out.println("Waiting for invocations from clients...");

    }
}
