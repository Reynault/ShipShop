package model.game.player.tactic;

import model.Move;
import model.game.player.Player;

public class CrossTactic implements Tactic{

    @Override
    public Move applyTactic(Player player) {
        player.setTactic(this);
        return null;
    }

}
