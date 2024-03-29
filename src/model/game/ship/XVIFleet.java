package model.game.ship;

import java.io.Serializable;

public class XVIFleet extends FleetFactory implements Serializable {


    public XVIFleet(int nbCruiser, int nbSubmarine, int nbTorpedo, int nbAircraft) {
        super(nbCruiser, nbSubmarine, nbTorpedo, nbAircraft);

        // Setting values
        this.HP_SUBMARINE = 2;
        this.HP_AIRCRAFT = 3;
        this.HP_TORPEDO = 2;
        this.HP_CRUISER = 2;

        this.AMMO_SUBMARINE = 1;
        this.AMMO_AIRCRAFT = 3;
        this.AMMO_TORPEDO = 1;
        this.AMMO_CRUISER = 6;

        this.DMG_SUBMARINE = 1;
        this.DMG_AIRCRAFT = 3;
        this.DMG_TORPEDO = 1;
        this.DMG_CRUISER = 1;

        this.NBTILES_SUBMARINE = 3;
        this.NBTILES_AIRCRAFT = 5;
        this.NBTILES_TORPEDO = 2;
        this.NBTILES_CRUISER = 4;
    }

}
