package model.game.ship;

import model.DirectionConstant;
import model.ShipType;

import java.io.Serializable;

public class ModernFleet extends FleetFactory implements Serializable {

    public ModernFleet(int nbCruiser, int nbSubmarine, int nbTorpedo, int nbAircraft) {
        super(nbCruiser, nbSubmarine, nbTorpedo, nbAircraft);

        // Setting values
        HP_SUBMARINE = 3;
        HP_AIRCRAFT = 5;
        HP_TORPEDO = 2;
        HP_CRUISER = 4;

        AMMO_SUBMARINE = 5;
        AMMO_AIRCRAFT = 5;
        AMMO_TORPEDO = 5;
        AMMO_CRUISER = 5;

        DMG_SUBMARINE = 2;
        DMG_AIRCRAFT = 4;
        DMG_TORPEDO = 1;
        DMG_CRUISER = 3;

        this.NBTILES_SUBMARINE = 3;
        this.NBTILES_AIRCRAFT = 5;
        this.NBTILES_TORPEDO = 2;
        this.NBTILES_CRUISER = 4;
    }

    @Override
    public Ship getShip(ShipType shipType) {
        Ship res;
        switch (shipType) {
            case SUBMARINE:
                nbSubmarine --;
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
                nbAircraft --;
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
                nbTorpedo --;
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
                nbCruiser --;
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
