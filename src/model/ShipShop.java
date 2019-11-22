package model;

import model.game.Game;
import model.game.era.Era;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;

import java.awt.*;
import java.io.File;
import java.util.Observable;
import java.util.UUID;

public class ShipShop extends Observable {

    protected UUID requestedShip;
    protected Attack requestAttack;
    protected GameFactory gameFactory;
    protected Game game;

    public ShipShop(UUID requestedShip, Attack requestAttack, GameFactory gameFactory, Game game) {
        this.requestedShip = requestedShip;
        this.requestAttack = requestAttack;
        this.gameFactory = gameFactory;
        this.game = game;
    }

    public void createGame(Era era, Tactic tactic, boolean humanFirst){
        if(humanFirst){
            game = GameFactory.getPVEGame(era, tactic);
            game.setTactic(2, tactic);
        }else{
            game = GameFactory.getEVPGame(era, tactic);
            game.setTactic(1, tactic);
        }
    }

    public UUID placeShip(Move move){
        return null;
    }

    public void setTactic(int player, Tactic tactic){

    }

    public Image drawShip(ShipType type){
        return null;
    }

    public void play(Attack attack){

    }

    public void endPlacingShip(){

    }

    public void save(File file){

    }

    public Game load(File file){
        return null;
    }

    public Ship getShip(UUID uuid){
        return null;
    }


}
