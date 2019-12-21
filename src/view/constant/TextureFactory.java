package view.constant;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class TextureFactory {
    private static TextureFactory instance = new TextureFactory();

    private BufferedImage crossPlayer;
    private BufferedImage flagPlayer;

    private BufferedImage crossEnnemy;
    private BufferedImage flagEnnemy;

    private BufferedImage plannedAttack;


    private TextureFactory() {
        try {
            crossPlayer = ImageIO.read(ClassLoader.getSystemResource("sprite/cross-player.png"));
            flagPlayer = ImageIO.read(ClassLoader.getSystemResource("sprite/flag-player.png"));
            crossEnnemy = ImageIO.read(ClassLoader.getSystemResource("sprite/cross-ennemy.png"));
            flagEnnemy = ImageIO.read(ClassLoader.getSystemResource("sprite/flag-ennemy.png"));
            plannedAttack = ImageIO.read(ClassLoader.getSystemResource("sprite/planned-attack.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static TextureFactory getInstance() {
        return instance;
    }

    public BufferedImage getCrossPlayer() {
        return crossPlayer;
    }

    public BufferedImage getFlagPlayer() {
        return flagPlayer;
    }

    public BufferedImage getCrossEnnemy() {
        return crossEnnemy;
    }

    public BufferedImage getFlagEnnemy() {
        return flagEnnemy;
    }

    public BufferedImage getPlannedAttack() {
        return plannedAttack;
    }
}
