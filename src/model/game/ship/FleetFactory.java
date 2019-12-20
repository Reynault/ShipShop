package model.game.ship;

import model.EraConstant;
import model.ShipType;

import java.io.Serializable;

public abstract class FleetFactory implements Serializable {
    private int nbCruiser;
    private int nbSubmarine;
    private int nbTorpedo;
    private int nbAircraft;

    static int HP_SUBMARINE;
    static int HP_AIRCRAFT ;
    static int HP_TORPEDO ;
    static int HP_CRUISER ;

    static int AMMO_SUBMARINE;
    static int AMMO_AIRCRAFT;
    static int AMMO_TORPEDO;
    static int AMMO_CRUISER;

    static int DMG_SUBMARINE;
    static int DMG_AIRCRAFT;
    static int DMG_TORPEDO;
    static int DMG_CRUISER;

    static int NBTILES_SUBMARINE;
    static int NBTILES_AIRCRAFT;
    static int NBTILES_TORPEDO;
    static int NBTILES_CRUISER;

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
                size = NBTILES_TORPEDO;
                break;
            case CRUISER:
                size = NBTILES_CRUISER;
                break;
            case AIRCRAFT:
                size = NBTILES_AIRCRAFT;
                break;
            case SUBMARINE:
                size = NBTILES_SUBMARINE;
                break;
            default:
                size = -1;
                break;
        }
        return size;
    }
}
