package view.sprite;

import model.constant.ShipType;

import java.awt.*;
import java.awt.image.BufferedImage;

public interface SpriteFactory {
    Image getSprite(ShipType shipType);
}
