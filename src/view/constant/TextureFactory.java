package view.constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextureFactory {
    private static TextureFactory instance = new TextureFactory();

    private BufferedImage cross;
    private BufferedImage flag;

    private TextureFactory(){
        try {
            cross = ImageIO.read(ClassLoader.getSystemResource("sprite/cross.png"));
            flag = ImageIO.read(ClassLoader.getSystemResource("sprite/flag.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public TextureFactory getInstance(){
        return instance;
    }

    public BufferedImage getCross() {
        return cross;
    }

    public BufferedImage getFlag() {
        return flag;
    }
}
