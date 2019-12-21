package view.sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class ModernFactory implements SpriteFactory {
    @Override
    public Image getCruiser() {
        Image image = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/cruiser.png");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public Image getSubmarine() {
        Image image = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/submarine.png");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public Image getTorpedo() {
        Image image = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/torpedo.png");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }

    @Override
    public Image getAircraft() {
        Image image = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/aircraft.png");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
