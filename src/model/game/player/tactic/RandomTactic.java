package model.game.player.tactic;

import model.informations.Attack;
import model.game.Game;
import model.game.player.Player;

import java.io.Serializable;

public class RandomTactic implements Tactic, Serializable {

    @Override
    public Attack applyTactic(Game game, Player attacker, Player victim) {

        return null;
    }

}
