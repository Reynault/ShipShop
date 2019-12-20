package model;

import java.util.UUID;

public class Move {

    int x, y;
    DirectionConstant direction;
    ShipType type;
    UUID ship;

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

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public DirectionConstant getDirection() {
        return direction;
    }

    public void setDirection(DirectionConstant direction) {
        this.direction = direction;
    }

    public ShipType getType() {
        return type;
    }

    public void setType(ShipType type) {
        this.type = type;
    }

    public UUID getShip() {
        return ship;
    }

    public void setShip(UUID ship) {
        this.ship = ship;
    }

}
