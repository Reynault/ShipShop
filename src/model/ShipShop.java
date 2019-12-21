package model;

import model.constant.ShipType;
import model.constant.UpdateObserver;
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
import java.io.File;
import java.util.Observable;
import java.util.UUID;

import static model.constant.UpdateObserver.*;

public class ShipShop extends Observable implements Serializable{
    private String SAVE_PATH = "save.ser";

    private UUID requestedShip;
    private Review turnReview;
    private GameFactory gameFactory;
    protected Game game;

    public void createGame(Era era, Tactic tactic, boolean humanFirst) {
        if (humanFirst) {
            game = GameFactory.getPVEGame(era);
            game.setTactic(1, tactic);
        } else {
            game = GameFactory.getEVPGame(era);
            game.setTactic(0, tactic);
        }

        this.setChanged();
        this.notifyObservers(CREATE_GAME);
    }

    public UUID placeShip(Move move) {
        UUID uuid = game.placeShip(move);
        setChanged();
        notifyObservers(PLACE_SHIP);
        return uuid;
    }

    public void setTactic(int player, Tactic tactic) {
        game.setTactic(player, tactic);
        setChanged();
        notifyObservers(CHANGE_TACTIC);
    }

    public Image drawShip(ShipType type) {
        return game.drawShip(type);
    }

    public void play(Attack attack) {
        turnReview = game.play(attack);

        setChanged();
        if(turnReview.isEnd()){
            notifyObservers(END_GAME);
        }else if(turnReview.ifCanAttack()) {
            notifyObservers(END_TURN);
        }else{
            notifyObservers(UpdateObserver.CAN_NOT_ATTACK);
        }
    }

    /**
     * EndPlaceShip is a method that verify is the place
     * step is finished, if it is, it updates its observers
     */
    public void endPlaceShip() {
        if (game.endPlaceShip()) {
            this.setChanged();
            this.notifyObservers(LAUNCH);
        }
    }

    public void save(Game game) {
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

        this.setChanged();
        this.notifyObservers(SAVE);
    }

    public void load() {
        Game game = null;
        try {
            FileInputStream stream = new FileInputStream(new File(SAVE_PATH));
            ObjectInputStream object = new ObjectInputStream(stream);
            game = (Game) object.readObject();
            object.close();
            stream.close();

            this.setChanged();
            this.notifyObservers(LOAD);
        } catch (Exception e) {
            this.setChanged();
            this.notifyObservers(BAD_LOAD);
        }
        this.game = game;
    }

    /**
     * Function used for get the Game
     * @return
     */
    public Game getGame() {
        return this.game;
    }

    @Override
    public String toString() {
        return "ShipShop{" +
                "game=" + game +
                '}';
    }

    public Ship getShip(UUID uuid) {
        return game.getShip(uuid);
    }

    public int getSize(ShipType type) {
        return game.getSize(type);
    }

    public int getNbShip(ShipType cruiser) {
        return game.getNbShip(cruiser);
    }

    public int getLife(int num) {
//        System.out.println("VIE : "+ game.getLife());
        return game.getLife(num);
    }

    public void getShipInformations(UUID currentShip) {
        setChanged();
        notifyObservers(GET_SHIP_INFO);
    }

    public Review getTurnReview() {
        return turnReview;
    }
}
