package model.game.player;

import model.Move;
import model.game.player.tactic.Tactic;
import model.game.ship.FleetFactory;

public class IA extends Player{

    private Tactic tactic;

    public IA(FleetFactory fleetFactory) {
        super(fleetFactory);
    }

    public Move getBestMove(){
        return null;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    public void setTactic(Tactic tactic){
        this.tactic = tactic;
    }

}
