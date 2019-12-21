package model.game.player;

import model.Move;
import model.ShipType;
import model.game.grid.Grid;
import model.game.grid.GridFactory;
import model.game.player.tactic.Tactic;
import model.game.ship.FleetFactory;
import model.game.ship.Ship;

import java.io.Serializable;
import java.util.UUID;

public abstract class Player implements Serializable {

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
        grid.hit(x, y, dmg);
    }

    public UUID placeShip(Move move){
        UUID uuid =  grid.placeShip(move, fleetFactory);
        System.out.println("PlaceShip Player : " +uuid);
        return uuid;
    }

    public boolean isDefeated(){
        return false;
    }

    public boolean canAttack(UUID id){
        return grid.getShip(id).canAttack();
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

    public Grid getGrid(){
        return grid;
    }


    /**
     * Function used for decrease the ammo after a fire
     * @param ship
     */
    public void decreaseAmmo(UUID ship) {
        grid.decreaseAmmo(ship);
    }


    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////
    //////////////////////////////////////////////////////////////////////

    /**
     * A debug Function used for get the ammo left of a Ship
     * @param ship
     * @return
     */
    public int getAmmo(UUID ship){
        return grid.getAmmo(ship);
    }

    public int getSize(ShipType type) {
        return fleetFactory.getSize(type);
    }

    public int getNbShip(ShipType cruiser) {
        return fleetFactory.getNbShip(cruiser);
    }

    public int getLife() {
        int res = grid.getLife();
        return res;
    }
}
