package model.game.player.tactic;

import model.Move;
import model.game.player.Player;

import java.io.Serializable;

public class CrossTactic implements Tactic, Serializable {

    @Override
    public Move applyTactic(Player player) {
        player.setTactic(this);
        return null;
    }

}
