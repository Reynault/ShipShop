package controller;

import model.Attack;
import model.Move;
import model.ShipShop;
import model.game.era.Era;
import model.game.era.EraFactory;
import model.game.player.tactic.Tactic;
import model.game.player.tactic.TacticFactory;
import view.panel.EraView;
import view.panel.TacticView;

public class GameController {
    ShipShop model;

    public GameController(ShipShop model) {
        this.model = model;
    }

    public void createGame(String stringEra, String stringTactic){
        Era era;
        Tactic tactic;

        // Choose era
        switch (stringEra){
            case EraView.MODERN:
                era = EraFactory.getModernEra();
                break;
            case EraView.XVI:
                era = EraFactory.getXVIEra();
                break;
            default:
                era = EraFactory.getModernEra();
                break;
        }

        // Choose tactic
        switch (stringTactic){
            case TacticView.RANDOM:
                tactic = TacticFactory.getRandomTactic();
                break;
            case TacticView.CROSS:
                tactic = TacticFactory.getCrossTactic();
                break;
            default:
                tactic = TacticFactory.getRandomTactic();
                break;
        }

        // Creating game
        model.createGame(era, tactic, true);
    }

    public void loadGame(){
        model.load();
    }

    public void saveGame(){
        model.save(model.getGame());
    }

    public void placeShip(Move move){
        model.placeShip(move);
    }

    public void endShipPlacement(){
        model.endPlaceShip();
    }

    public void play(Attack attack){
        model.play(attack);
    }
}
