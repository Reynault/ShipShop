package model.game.era;

import model.ShipType;
import model.game.ship.FleetFactory;

import java.awt.*;

public abstract class Era {


    public Era() {

    }

    public FleetFactory getFleetFactory(){
        return null;
    }

    public Image drawShip(ShipType type){
        return null;
    }

}
