package model.game;

import model.Attack;
import model.ShipType;
import model.game.era.Era;
import model.game.player.Player;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;

import java.awt.*;
import java.util.List;
import java.util.UUID;

public class Game {

    protected static int GRID_WIDTH;
    protected static int GRID_HEIGHT;
    protected int currentPlayer;
    protected Era era;
    protected Player[] players;


    public Game(Era era, Player p1, Player p2) {

    }

    public void setTactic(int player, Tactic tactic){
        players[player].setTactic(tactic);
    }

    public Image drawShip(ShipType type){
        return null;
    }

    public void endPlacing(){

    }

    public UUID placeShip(){
        return null;
    }

    public List<Attack> play(Attack attack){
        return null;
    }

    public Ship getship(UUID uuid){
        return null;
    }

    private void next(){

    }

}
