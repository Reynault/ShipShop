package model.game.era;

import model.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.XVIFleet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class XVIEra extends Era implements Serializable {

    public FleetFactory getFleetFactory(){
        return new XVIFleet(2,1,2,1);
    }

    public Image drawShip(ShipType type){
        try {
            Image ship = null;
            InputStream inputStream;
            switch (type){
                case CRUISER:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/cruiser-XVI.png");
                    ship = ImageIO.read(inputStream);
                    break;
                case TORPEDO:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/torpedo-XVI.png");
                    ship = ImageIO.read(inputStream);
                    break;
                case AIRCRAFT:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/aircraft-XVI.png");
                    ship = ImageIO.read(inputStream);
                    break;
                case SUBMARINE:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/submarine-XVI.png");
                    ship = ImageIO.read(inputStream);
                    break;
                default:
                    throw new IllegalStateException("Unexpected value: " + type);
            }
            return ship;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
