package model.game.player.tactic;

import model.Attack;
import model.game.player.Player;

public interface Tactic {

    Attack applyTactic(Player attacker, Player victim);

}
