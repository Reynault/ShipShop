package model.game.era;

import model.constant.ShipType;
import model.game.ship.FleetFactory;

import java.awt.*;
import java.io.Serializable;

public abstract class Era implements Serializable {

    public abstract FleetFactory getFleetFactory();

    public abstract Image drawShip(ShipType type);
}
