package model.game.era;

import model.ShipType;
import model.game.ship.FleetFactory;

import java.awt.*;

public abstract class Era {

    public abstract FleetFactory getFleetFactory();

    public abstract Image drawShip(ShipType type);

}
