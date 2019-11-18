package model.game.player;

import model.Move;
import model.game.player.tactic.Tactic;

public class IA extends Player{

    public IA() {
        super();
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
