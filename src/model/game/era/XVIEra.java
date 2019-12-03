package model.game.era;

import model.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.XVIFleet;

import java.awt.*;

public class XVIEra extends Era{


    public FleetFactory getFleetFactory(){
        return new XVIFleet(2,1,2,1);
    }

    public Image drawShip(ShipType type){
        return null;
    }


}
