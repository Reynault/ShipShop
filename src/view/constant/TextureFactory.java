package view.constant;

import model.constant.EraConstant;
import view.sprite.ModernFactory;
import view.sprite.SpriteFactory;
import view.sprite.XVIFactory;

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

    public SpriteFactory getSpriteFactory(EraConstant eraConstant){
        SpriteFactory spriteFactory;
        switch (eraConstant){
            case XVI:
                spriteFactory = new XVIFactory();
                break;
            case MODERN:
                spriteFactory = new ModernFactory();
                break;
            default:
                spriteFactory = new ModernFactory();
                break;
        }
        return spriteFactory;
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
