package model;

import model.game.Game;
import model.game.era.Era;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;

import java.awt.*;
import java.io.*;
import java.util.Observable;
import java.util.UUID;

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

    public void createGame(Era era, Tactic tactic, boolean humanFirst){
        if(humanFirst){
            game = GameFactory.getPVEGame(era, tactic);
            game.setTactic(1, tactic);
        }else{
            game = GameFactory.getEVPGame(era, tactic);
            game.setTactic(0, tactic);
        }
    }

    public UUID placeShip(Move move){
        return null;
    }

    public void setTactic(int player, Tactic tactic){

    }

    public Image drawShip(ShipType type){
        return game.drawShip(type);
    }

    public void play(Attack attack){

    }

    public void endPlaceShip(){
        game.endPlaceShip();
    }

    public void save(File file) throws IOException {
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(file));
        oos.writeObject(game);
        oos.close();
    }

    public Game load(File file) throws IOException, ClassNotFoundException {
        ObjectInputStream ois = new ObjectInputStream(new FileInputStream(file));
        Game g = (Game)ois.readObject();
        //TODO
        return null;
    }

    public Ship getShip(UUID uuid){
        return null;
    }


}
