package model.game.player.tactic;

import model.Attack;
import model.Move;
import model.game.player.Player;

import java.io.Serializable;

public class RandomTactic implements Tactic, Serializable {

    @Override
    public Attack applyTactic(Player attacker, Player victim) {
        return null;
    }

}
