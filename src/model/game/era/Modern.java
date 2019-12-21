package model.game.era;

import model.constant.EraConstant;
import model.constant.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.ModernFleet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class Modern extends Era implements Serializable {

    public FleetFactory getFleetFactory(){
        return new ModernFleet(2,1,2,1);
    }

    @Override
    public EraConstant getName() {
        return EraConstant.MODERN;
    }


}
