package model;

import model.game.ship.Ship;

import java.util.UUID;

public class Attack {

    private int x;
    private int y;
    private UUID ship;


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public UUID getShip() {
        return ship;
    }
}
