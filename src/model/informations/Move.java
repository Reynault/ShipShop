package model.informations;

import model.constant.DirectionConstant;
import model.constant.ShipType;

import java.io.Serializable;
import java.util.UUID;

public final class Move implements Serializable {

    private final int x, y;
    private final DirectionConstant direction;
    private final ShipType type;
    private final UUID ship;

    public Move(int x, int y, DirectionConstant direction, ShipType shipType) {
        this.x = x;
        this.y = y;
        this.direction = direction;
        this.type = shipType;
        this.ship = new UUID(0, 0);
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public DirectionConstant getDirection() {
        return direction;
    }

    public ShipType getType() {
        return type;
    }

    public UUID getShip() {
        return ship;
    }

}
