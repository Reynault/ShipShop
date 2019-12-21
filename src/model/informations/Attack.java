package model.informations;

import java.io.Serializable;
import java.util.UUID;

public final class Attack implements Serializable {

    private final int x;
    private final int y;
    private final UUID ship;

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
