package model.game.ship;

import model.DirectionConstant;
import model.EraConstant;
import model.ShipType;

import java.io.Serializable;

public abstract class FleetFactory implements Serializable {
    private int nbCruiser;
    private int nbSubmarine;
    private int nbTorpedo;
    private int nbAircraft;

    int HP_SUBMARINE;
    int HP_AIRCRAFT ;
    int HP_TORPEDO ;
    int HP_CRUISER ;

    int AMMO_SUBMARINE;
    int AMMO_AIRCRAFT;
    int AMMO_TORPEDO;
    int AMMO_CRUISER;

    int DMG_SUBMARINE;
    int DMG_AIRCRAFT;
    int DMG_TORPEDO;
    int DMG_CRUISER;

    int NBTILES_SUBMARINE;
    int NBTILES_AIRCRAFT;
    int NBTILES_TORPEDO;
    int NBTILES_CRUISER;

    public FleetFactory(int nbCruiser, int nbSubmarine, int nbTorpedo, int nbAircraft) {
        this.nbCruiser = nbCruiser;
        this.nbSubmarine = nbSubmarine;
        this.nbTorpedo = nbTorpedo;
        this.nbAircraft = nbAircraft;
    }

    public static FleetFactory getFactory(EraConstant era, int nbAircraft, int nbCruiser, int nbSubmarine, int nbTorpedo) {
        FleetFactory res;
        switch (era) {
            case XVI:
                res = new XVIFleet(nbCruiser, nbSubmarine, nbTorpedo, nbAircraft);
                break;
            case MODERN:
            default:
                res = new ModernFleet(nbCruiser, nbSubmarine, nbTorpedo, nbAircraft);
                break;
        }
        return res;
    }

    public boolean hasShip(ShipType shipType){
        boolean res;
        switch (shipType){
            case CRUISER:
                res = nbCruiser > 0;
                break;
            case TORPEDO:
                res = nbTorpedo > 0;
                break;
            case AIRCRAFT:
                res = nbAircraft > 0;
                break;
            case SUBMARINE:
                res = nbSubmarine > 0;
                break;
            default:
                res = false;
                break;
        }
        return res;
    }

    public Ship getShip(ShipType shipType, DirectionConstant direction){
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

    public int getSize(ShipType shipType) {
        int size;
        switch (shipType){
            case TORPEDO:
                size = this.NBTILES_TORPEDO;
                break;
            case CRUISER:
                size = this.NBTILES_CRUISER;
                break;
            case AIRCRAFT:
                size = this.NBTILES_AIRCRAFT;
                break;
            case SUBMARINE:
                size = this.NBTILES_SUBMARINE;
                break;
            default:
                size = -1;
                break;
        }
        return size;
    }
}
