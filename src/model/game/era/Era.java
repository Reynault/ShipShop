package model.game.era;

import model.constant.EraConstant;
import model.game.ship.FleetFactory;

import java.io.Serializable;

public abstract class Era implements Serializable {

    public abstract FleetFactory getFleetFactory();

    public abstract EraConstant getName();
}
