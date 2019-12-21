package model.game.era;

import model.constant.EraConstant;
import model.game.ship.FleetFactory;
import model.game.ship.XVIFleet;

import java.io.Serializable;

public class XVI extends Era implements Serializable {

    public FleetFactory getFleetFactory() {
        return new XVIFleet(2, 1, 2, 1);
    }

    @Override
    public EraConstant getName() {
        return EraConstant.XVI;
    }


}
