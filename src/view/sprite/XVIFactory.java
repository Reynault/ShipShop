package view.sprite;

import javax.imageio.ImageIO;
import java.awt.*;
import java.io.IOException;
import java.io.InputStream;

public class XVIFactory implements SpriteFactory {
    @Override
    public Image getCruiser() {
        Image image = null;
        try {
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/cruiser-XVI.png");
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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/submarine-XVI.png");
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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/torpedo-XVI.png");
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
            InputStream inputStream = getClass().getClassLoader().getResourceAsStream("sprite/aircraft-XVI.png");
            image = ImageIO.read(inputStream);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return image;
    }
}
