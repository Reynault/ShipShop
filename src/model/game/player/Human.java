package model.game.player;

import model.game.ship.FleetFactory;

public class Human extends Player{

    public Human(FleetFactory fleetFactory) {
        super(fleetFactory);
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
