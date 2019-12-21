package model;

import java.rmi.Remote;
import java.rmi.RemoteException;

public interface ShipShopInterface extends Remote {
    ShipShop getObj() throws RemoteException;
}
