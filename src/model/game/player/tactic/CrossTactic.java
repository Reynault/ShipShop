package model.game.player.tactic;

import model.Attack;
import model.game.player.Player;

import java.io.Serializable;

public class CrossTactic implements Tactic, Serializable {

    @Override
    public Attack applyTactic(Player attacker, Player victim) {
        return null;
    }

}
