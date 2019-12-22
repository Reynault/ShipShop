package view.sprite;

import model.constant.ShipType;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ModernFactory implements SpriteFactory {

    @Override
    public Image getSprite(ShipType shipType) {
        Image image = null;
        InputStream inputStream;
        try {
            switch (shipType){
                case SUBMARINE:
                    inputStream = getClass().getClassLoader().getResourceAsStream("sprite/submarine.png");
                    image = ImageIO.read(inputStream);
                    break;
                case AIRCRAFT:
                    inputStream = getClass().getClassLoader().getResourceAsStream("sprite/aircraft.png");
                    image = ImageIO.read(inputStream);
                    break;
                case CRUISER:
                    inputStream = getClass().getClassLoader().getResourceAsStream("sprite/cruiser.png");
                    image = ImageIO.read(inputStream);
                    break;
                case TORPEDO:
                    inputStream = getClass().getClassLoader().getResourceAsStream("sprite/torpedo.png");
                    image = ImageIO.read(inputStream);
                    break;
                    default:
                        break;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
