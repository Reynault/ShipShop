package model;

import model.constant.EraConstant;
import model.constant.ShipType;
import model.constant.UpdateObserver;
import model.game.Game;
import model.game.era.Era;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;
import model.informations.Attack;
import model.informations.Move;
import model.informations.Review;

import java.awt.*;
import java.rmi.RemoteException;
import java.util.Observable;
import java.util.UUID;

import static model.constant.UpdateObserver.*;

public class LiaisonRMI extends Observable {

    private Review turnReview;
    private ShipShopInterface interfaceRMI;

    public LiaisonRMI(ShipShopInterface interfaceRMI) {
        this.interfaceRMI = interfaceRMI;
    }

    public void createGame(Era era, Tactic tactic, boolean humanFirst)  throws RemoteException {
        interfaceRMI.createGame(era, tactic, humanFirst);

        this.setChanged();
        this.notifyObservers(CREATE_GAME);
    }

    public UUID placeShip(Move move)  throws RemoteException{
        UUID uuid = interfaceRMI.placeShip(move);

        setChanged();
        notifyObservers(PLACE_SHIP);

        return uuid;
    }

    public void setTactic(int player, Tactic tactic)  throws RemoteException{
        interfaceRMI.setTactic(player, tactic);
        setChanged();
        notifyObservers(CHANGE_TACTIC);
    }

    public void play(Attack attack)  throws RemoteException{
        turnReview = interfaceRMI.play(attack);

        setChanged();
        if (turnReview.isEnd()) {
            notifyObservers(END_GAME);
        } else if (turnReview.ifCanAttack()) {
            notifyObservers(END_TURN);
        } else {
            notifyObservers(UpdateObserver.CAN_NOT_ATTACK);
        }
    }

    /**
     * EndPlaceShip is a method that verify is the place
     * step is finished, if it is, it updates its observers
     */
    public void endPlaceShip()  throws RemoteException{
        if (interfaceRMI.endPlaceShip()) {
            this.setChanged();
            this.notifyObservers(LAUNCH);
        }
    }

    public void save(Game game)  throws RemoteException{
        interfaceRMI.save(game);

        this.setChanged();
        this.notifyObservers(SAVE);
    }

    public void load()  throws RemoteException{
        boolean res = interfaceRMI.load();

        if (res) {
            setChanged();
            notifyObservers(LOAD);
        } else {
            setChanged();
            notifyObservers(BAD_LOAD);
        }
    }

    /**
     * Function used for get the Game
     *
     * @return
     */
    public Game getGame()  throws RemoteException{
        return interfaceRMI.getGame();
    }

    public Ship getShip(UUID uuid)  throws RemoteException{
        return interfaceRMI.getShip(uuid);
    }

    public int getSize(ShipType type) throws RemoteException {
        return interfaceRMI.getSize(type);
    }

    public int getNbShip(ShipType cruiser) throws RemoteException {
        return interfaceRMI.getNbShip(cruiser);
    }

    public int getLife(int num)  throws RemoteException{
//        System.out.println("VIE : "+ game.getLife());
        return interfaceRMI.getLife(num);
    }

    public void getShipInformations(UUID currentShip) {
        setChanged();
        notifyObservers(GET_SHIP_INFO);
    }

    public Review getTurnReview() {
        return turnReview;
    }

    public EraConstant getEra() throws RemoteException {
        return interfaceRMI.getEra();
    }
}
