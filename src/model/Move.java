package model;

import java.util.UUID;

public class Move {

	final static UUID NONE_UUID = new UUID(0,0);
	int x, y;
	DirectionConstant direction;
	ShipType type;
	UUID ship;
}
