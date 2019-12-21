package model.informations;

import java.util.UUID;

public class Attack {

    private int x;
    private int y;
    private UUID ship;

    public Attack(int x, int y, UUID ship) {
        this.x = x;
        this.y = y;
        this.ship = ship;
    }

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
