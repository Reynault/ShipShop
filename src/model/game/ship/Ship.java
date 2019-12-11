package model.game.ship;

import model.DirectionConstant;
import model.ShipType;

/**
 * Ship is a model class that represent a ship and its properties
 */
public class Ship {
    private int hp;
    private int ammo;
    private int dmg;
    private int nbTiles;

    /**
     * DirectionConstant is giving the direction of the ship (UP, LEFT ...)
     */
    private DirectionConstant direction;

    /**
     * ShipType is a parameter that indicate the type of the ship (Submarine, cruiser etc...)
     */
    private ShipType shipType;

    /**
     * Default constructor for Ship with all properties provided
     * @param hp the number of health point remaining
     * @param ammo the number of ammunition
     * @param dmg the damage done by the ship
     * @param nbTiles the number of tiles took by the ship
     * @param direction the direction toward the ship is looking
     * @param shipType the ship type
     */
    public Ship(int hp, int ammo, int dmg, int nbTiles, DirectionConstant direction, ShipType shipType) {
        this.hp = hp;
        this.ammo = ammo;
        this.dmg = dmg;
        this.nbTiles = nbTiles;
        this.direction = direction;
        this.shipType = shipType;
    }

    public void place(DirectionConstant constant) {
        this.direction = constant;
    }

    public boolean hasSunk(){
        return hp <= 0;
    }

    public boolean canShoot(){
        return ammo > 0;
    }

    public boolean canAttack(){
        return !hasSunk() && canShoot();
    }

    public int getAmmo() {
        return ammo;
    }

    public int getHp() {
        return hp;
    }

    public void hit(int dmg){
        hp=-dmg;
    }
}
