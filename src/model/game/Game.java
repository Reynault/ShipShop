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

    public static int GRID_WIDTH;
    public static int GRID_HEIGHT;
    private int currentPlayer;
    private Era era;
    private final Player p1;
    private final Player p2;
    private Player[] players;


    public Game(Era era, Player p1, Player p2) {
        this.era = era;
        this.p1 = p1;
        this.p2 = p2;
        this.players = new Player[2];
        this.players[0] = this.p1;
        this.players[1] = this.p2;
    }

    public void setTactic(int player, Tactic tactic){
        players[player].setTactic(tactic);
    }

    public Image drawShip(ShipType type){
        return era.drawShip(type);
    }

    public void endPlaceShip(){
        if (p1.isHuman() && p1.isReady()){
            //TODO: faut revoir ce qu'on fait ici
        }
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
