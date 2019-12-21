package model;

import model.constant.EraConstant;
import model.constant.ShipType;
import model.game.Game;
import model.game.GameFactory;
import model.game.era.Era;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;
import model.informations.Attack;
import model.informations.Move;
import model.informations.Review;

import java.awt.*;
import java.io.*;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.UUID;

public class ShipShop extends UnicastRemoteObject implements ShipShopInterface {
    private String SAVE_PATH = "save.ser";

    private UUID requestedShip;
    private Review turnReview;
    private GameFactory gameFactory;
    protected Game game;

    public ShipShop() throws RemoteException {
    }

    public void createGame(Era era, Tactic tactic, boolean humanFirst)  throws RemoteException{
        if (humanFirst) {
            game = GameFactory.getPVEGame(era);
            game.setTactic(1, tactic);
        } else {
            game = GameFactory.getEVPGame(era);
            game.setTactic(0, tactic);
        }
    }

    public UUID placeShip(Move move)  throws RemoteException{
        UUID uuid = game.placeShip(move);
        return uuid;
    }

    public void setTactic(int player, Tactic tactic)  throws RemoteException{
        game.setTactic(player, tactic);
    }

    public Review play(Attack attack) throws RemoteException {
        return game.play(attack);
    }

    /**
     * EndPlaceShip is a method that verify is the place
     * step is finished, if it is, it updates its observers
     */
    public boolean endPlaceShip()  throws RemoteException{
        return game.endPlaceShip();
    }

    public void save(Game game) throws RemoteException {
        File file = new File(SAVE_PATH);
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            FileOutputStream stream = new FileOutputStream(file);
            ObjectOutputStream object = new ObjectOutputStream(stream);
            object.writeObject(game);
            object.close();
            stream.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean load()  throws RemoteException{
        try {
            FileInputStream stream = new FileInputStream(new File(SAVE_PATH));
            ObjectInputStream object = new ObjectInputStream(stream);
            game = (Game) object.readObject();
            object.close();
            stream.close();

            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Function used for get the Game
     *
     * @return
     */
    public Game getGame()  throws RemoteException{
        return this.game;
    }

    @Override
    public String toString() {
        return "ShipShop{" +
                "game=" + game +
                '}';
    }

    public Ship getShip(UUID uuid)  throws RemoteException{
        return game.getShip(uuid);
    }

    public int getSize(ShipType type)  throws RemoteException{
        return game.getSize(type);
    }

    public int getNbShip(ShipType cruiser)  throws RemoteException{
        return game.getNbShip(cruiser);
    }

    public int getLife(int num)  throws RemoteException{
//        System.out.println("VIE : "+ game.getLife());
        return game.getLife(num);
    }

    public Review getTurnReview()  throws RemoteException{
        return turnReview;
    }

    @Override
    public EraConstant getEra() throws RemoteException {
        return game.getEra();
    }
}
