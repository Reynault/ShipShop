package model;

import model.constant.EraConstant;
import model.constant.ShipType;
import model.game.Game;
import model.game.era.Era;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;
import model.informations.Attack;
import model.informations.Move;
import model.informations.Review;

import java.awt.*;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.UUID;

public interface ShipShopInterface extends Remote {
    void createGame(Era era, Tactic tactic, boolean humanFirst) throws RemoteException;

    UUID placeShip(Move move) throws RemoteException;

    void setTactic(int player, Tactic tactic) throws RemoteException;

    Review play(Attack attack) throws RemoteException;

    boolean endPlaceShip() throws RemoteException;

    void save(Game game) throws RemoteException;

    boolean load() throws RemoteException;

    Game getGame() throws RemoteException;

    Ship getShip(UUID uuid) throws RemoteException;

    int getSize(ShipType type) throws RemoteException;

    int getNbShip(ShipType cruiser) throws RemoteException;

    int getLife(int num) throws RemoteException;

    Review getTurnReview() throws RemoteException;

    EraConstant getEra() throws RemoteException;
}
