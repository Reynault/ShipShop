package model;

import java.util.UUID;

public class Move {

	final static UUID NONE_UUID = new UUID(0,0);
	int x, y;
	DirectionConstant direction;
	ShipType type;
	UUID ship;

	public Move(int x, int y, DirectionConstant direction, ShipType type) {
		this.x = x;
		this.y = y;
		this.direction = direction;
		this.type = type;
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
