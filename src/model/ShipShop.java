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

import static model.UpdateObserver.BAD_LOAD;
import static model.UpdateObserver.LAUNCH;
import static model.UpdateObserver.PLACESHIP;

public class ShipShop extends Observable {
    private String SAVE_PATH = "save.ser";

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

    public void play(Attack attack) {
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
    }

    public void load() {
        Game game = null;
        try {
            FileInputStream stream = new FileInputStream(new File(SAVE_PATH));
            ObjectInputStream object = new ObjectInputStream(stream);
            game = (Game) object.readObject();
            object.close();
            stream.close();
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
}
