package model.game.player.tactic;

import model.Move;
import model.game.player.Player;

public interface Tactic {

    public Move applyTactic(Player player);

}
