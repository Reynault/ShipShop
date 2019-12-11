package model.game.player;

import model.Move;
import model.game.grid.Grid;
import model.game.grid.GridFactory;
import model.game.player.tactic.Tactic;
import model.game.ship.FleetFactory;
import model.game.ship.Ship;

import java.util.UUID;

public abstract class Player {

    protected boolean ready;
    protected FleetFactory fleetFactory;
    protected Grid grid;
    protected  Tactic tactic;

    public Player(FleetFactory fleetFactory) {
        this.fleetFactory = fleetFactory;
        this.ready = false;
        this.grid = GridFactory.getSimpleGrid();
    }

    public void hit(int x, int y, int dmg){

    }

    public UUID placeShip(Move move){
        return grid.placeShip(move, fleetFactory);
    }

    public boolean isDefeated(){
        return false;
    }

    public boolean canAttack(UUID id){
        if(grid.getShip(id).canAttack())
            return true;
        else
            return false;
    }

    public abstract boolean isHuman();

    public Move getBestMove(){
        return null;
    }

    public void setTactic(Tactic tactic){
        this.tactic = tactic;
    }

    public int getDmg(UUID id){
        return grid.getDmg(id);
    }

    public Ship getShip(UUID id){
        return grid.getShip(id);
    }

    public boolean isReady(){
        return this.ready;
    }

    public void setReady(boolean val){
        this.ready = val;
    }

}
