package model.game.era;

import model.constant.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.ModernFleet;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;

public class ModernEra extends Era implements Serializable {

    public FleetFactory getFleetFactory(){
        return new ModernFleet(2,1,2,1);
    }

    public Image drawShip(ShipType type){
        try {
            Image ship = null;
            InputStream inputStream;
            switch (type){
                case CRUISER:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/cruiser.png");
                        ship = ImageIO.read(inputStream);
                    break;
                case TORPEDO:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/torpedo.png");
                    ship = ImageIO.read(inputStream);
                    break;
                case AIRCRAFT:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/aircraft.png");
                    ship = ImageIO.read(inputStream);
                    break;
                case SUBMARINE:
                    inputStream
                            = ModernEra.class.getClassLoader().getResourceAsStream("sprite/submarine.png");
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
