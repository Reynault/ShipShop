package model;

import model.game.Game;
import model.game.era.Era;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;

import java.awt.*;
import java.io.*;
import java.io.File;
import java.util.Observable;
import java.util.UUID;

import static model.UpdateObserver.*;

public class ShipShop extends Observable {

    protected UUID requestedShip;
    protected Attack requestAttack;
    protected GameFactory gameFactory;
    protected Game game;

    public ShipShop() {
    }

    public ShipShop(UUID requestedShip, Attack requestAttack, GameFactory gameFactory, Game game) {
        this.requestedShip = requestedShip;
        this.requestAttack = requestAttack;
        this.gameFactory = gameFactory;
        this.game = game;
    }

    public void createGame(Era era, Tactic tactic, boolean humanFirst) {
        if (humanFirst) {
            game = GameFactory.getPVEGame(era);
            game.setTactic(1, tactic);
        } else {
            game = GameFactory.getEVPGame(era);
            game.setTactic(0, tactic);
        }
    }

    public UUID placeShip(Move move) {
        UUID uuid = game.placeShip(move);
        setChanged();
        notifyObservers(PLACESHIP);
        return uuid;
    }

    public void setTactic(int player, Tactic tactic) {
        game.setTactic(player, tactic);
    }

    public Image drawShip(ShipType type) {
        return game.drawShip(type);
    }

    public void play(Attack attack){
        game.play(attack);
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

    //A faire
    public void save(File file) throws IOException {
        if(!file.exists()){
            file.createNewFile();
        }
        FileOutputStream fos = new FileOutputStream(file);
        ObjectOutputStream oos = new ObjectOutputStream(fos);
        oos.writeObject(game);
        oos.close();
        fos.close();
    }

    public Game load(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Game g = (Game)ois.readObject();
        setChanged();
        notifyObservers(LOAD);
        return g;
    }

    public Ship getShip(UUID uuid) {
        return null;
    }


}
