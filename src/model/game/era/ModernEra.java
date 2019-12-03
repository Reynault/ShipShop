package model.game.era;

import model.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.ModernFleet;
import model.game.ship.Ship;

import java.awt.*;

public class ModernEra extends Era{

    public FleetFactory getFleetFactory(){
        return new ModernFleet(2,1,2,1);
    }

    public Image drawShip(ShipType type){
        return null;
    }

}
