package view.constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextureFactory {
    private static TextureFactory instance = new TextureFactory();

    private BufferedImage cross_player;
    private BufferedImage flag_player;

    private BufferedImage cross_ennemy;
    private BufferedImage flag_ennemy;


    private TextureFactory(){
        try {
            cross_player = ImageIO.read(ClassLoader.getSystemResource("sprite/cross-player.png"));
            flag_player = ImageIO.read(ClassLoader.getSystemResource("sprite/flag-player.png"));
            cross_ennemy = ImageIO.read(ClassLoader.getSystemResource("sprite/cross-ennemy.png"));
            flag_ennemy = ImageIO.read(ClassLoader.getSystemResource("sprite/flag-ennemy.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TextureFactory getInstance(){
        return instance;
    }

    public BufferedImage getCross_player() {
        return cross_player;
    }

    public BufferedImage getFlag_player() {
        return flag_player;
    }

    public BufferedImage getCross_ennemy() {
        return cross_ennemy;
    }

    public BufferedImage getFlag_ennemy() {
        return flag_ennemy;
    }
}
