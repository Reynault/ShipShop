package model.game.ship;

import model.DirectionConstant;
import model.ShipType;

import java.io.Serializable;

public class ModernFleet extends FleetFactory implements Serializable {

    private static int HP_SUBMARINE = 3;
    private static int HP_AIRCRAFT = 5;
    private static int HP_TORPEDO = 2;
    private static int HP_CRUISER = 4;


    private static int AMMO_SUBMARINE = 5;
    private static int AMMO_AIRCRAFT = 5;
    private static int AMMO_TORPEDO = 5;
    private static int AMMO_CRUISER = 5;


    private static int DMG_SUBMARINE = 2;
    private static int DMG_AIRCRAFT = 4;
    private static int DMG_TORPEDO = 1;
    private static int DMG_CRUISER = 3;


    private static int NBTILES_SUBMARINE = 3;
    private static int NBTILES_AIRCRAFT = 5;
    private static int NBTILES_TORPEDO = 2;
    private static int NBTILES_CRUISER = 4;

    public ModernFleet(int nbCruiser, int nbSubmarine, int nbTorpedo, int nbAircraft) {
        super(nbCruiser, nbSubmarine, nbTorpedo, nbAircraft);
    }

    @Override
    public Ship getShip(ShipType shipType) {
        Ship res;
        switch (shipType) {
            case SUBMARINE:
                res = new Ship(
                        HP_SUBMARINE,
                        AMMO_SUBMARINE,
                        DMG_SUBMARINE,
                        NBTILES_SUBMARINE,
                        DirectionConstant.UP,
                        ShipType.SUBMARINE
                );
                break;
            case AIRCRAFT:
                res = new Ship(
                        HP_AIRCRAFT,
                        AMMO_AIRCRAFT,
                        DMG_AIRCRAFT,
                        NBTILES_AIRCRAFT,
                        DirectionConstant.UP,
                        ShipType.AIRCRAFT
                );
                break;
            case TORPEDO:
                res = new Ship(
                        HP_TORPEDO,
                        AMMO_TORPEDO,
                        DMG_TORPEDO,
                        NBTILES_TORPEDO,
                        DirectionConstant.UP,
                        ShipType.TORPEDO
                );
                break;
            case CRUISER:
                res = new Ship(
                        HP_CRUISER,
                        AMMO_CRUISER,
                        DMG_CRUISER,
                        NBTILES_CRUISER,
                        DirectionConstant.UP,
                        ShipType.CRUISER
                );
                break;
            default:
                res = null;
                break;
        }
        return res;
    }
}
