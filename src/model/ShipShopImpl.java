package model;

import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class ShipShopImpl extends UnicastRemoteObject implements ShipShopInterface{

    private ShipShop obj;

    public ShipShopImpl(ShipShop shipShop) throws RemoteException {
        obj = shipShop;
    }

    public ShipShop getObj(){
        return obj;
    }
}
