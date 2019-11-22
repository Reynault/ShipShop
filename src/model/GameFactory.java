package model;

import model.game.Game;
import model.game.era.Era;
import model.game.player.Player;
import model.game.player.PlayerFactory;
import model.game.player.tactic.Tactic;

public class GameFactory {

    public static Game getPVEGame(Era era, Tactic tactic){
        return new Game(
                era,
                PlayerFactory.getHuman(era),
                PlayerFactory.getIA(era, tactic)
        );
    }

    public static Game getEVPGame(Era era, Tactic tactic){
        return new Game(
                era,
                PlayerFactory.getIA(era, tactic),
                PlayerFactory.getHuman(era)
        );
    }


}
