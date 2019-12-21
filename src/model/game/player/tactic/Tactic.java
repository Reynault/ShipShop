package model.game.player.tactic;

import model.informations.Attack;
import model.game.Game;
import model.game.player.Player;

public interface Tactic {

    Attack applyTactic(Game game, Player attacker, Player victim);

}
