package model.game.ship;

import model.EraConstant;
import model.ShipType;

import java.io.Serializable;

public abstract class FleetFactory implements Serializable {
    int nbCruiser;
    int nbSubmarine;
    int nbTorpedo;
    int nbAircraft;

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

    public abstract Ship getShip(ShipType shipType);

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
