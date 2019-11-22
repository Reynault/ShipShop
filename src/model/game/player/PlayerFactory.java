package model.game.player;

import model.game.era.Era;

public class PlayerFactory {

    public static IA getIA(Era era) {
        return new IA(era.getFleetFactory());
    }

    public static Human getHuman(Era era) {
        return new Human(era.getFleetFactory());
    }

}
