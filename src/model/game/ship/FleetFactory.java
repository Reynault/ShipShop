package model.game.ship;

import model.EraConstant;
import model.ShipType;

import java.io.Serializable;

public abstract class FleetFactory implements Serializable {
    private int nbCruiser;
    private int nbSubmarine;
    private int nbTorpedo;
    private int nbAircraft;

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
}
