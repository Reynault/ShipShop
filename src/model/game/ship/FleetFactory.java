package model.game.ship;

import model.EraConstant;
import model.ShipType;

import java.io.Serializable;

public abstract class FleetFactory implements Serializable {
    private int nbCruiser;
    private int nbSubmarine;
    private int nbTorpedo;
    private int nbAircraft;

    public int HP_SUBMARINE;
    public int HP_AIRCRAFT ;
    public int HP_TORPEDO ;
    public int HP_CRUISER ;

    public int AMMO_SUBMARINE;
    public int AMMO_AIRCRAFT;
    public int AMMO_TORPEDO;
    public int AMMO_CRUISER;

    public int DMG_SUBMARINE;
    public int DMG_AIRCRAFT;
    public int DMG_TORPEDO;
    public int DMG_CRUISER;

    public int NBTILES_SUBMARINE;
    public int NBTILES_AIRCRAFT;
    public int NBTILES_TORPEDO;
    public int NBTILES_CRUISER;

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
