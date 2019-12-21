package controller;

import model.LiaisonRMI;
import model.informations.Attack;
import model.informations.Move;
import model.ShipShop;
import model.game.era.Era;
import model.game.era.EraFactory;
import model.game.player.tactic.Tactic;
import model.game.player.tactic.TacticFactory;
import view.panel.EraView;
import view.panel.TacticView;

import java.rmi.RemoteException;
import java.util.Observable;
import java.util.UUID;

public class GameController {
    LiaisonRMI model;


    public GameController(LiaisonRMI model) {
        this.model = model;
    }

    public void createGame(String stringEra, String stringTactic) {
        Era era;
        Tactic tactic;

        // Choose era
        switch (stringEra) {
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
        switch (stringTactic) {
            case TacticView.RANDOM:
                tactic = TacticFactory.getRandomTactic();
                break;
            case TacticView.LINEAR:
                tactic = TacticFactory.getLinearTactic();
                break;
            default:
                tactic = TacticFactory.getRandomTactic();
                break;
        }

        // Creating game
        try {
            model.createGame(era, tactic, true);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void loadGame() {
        try {
            model.load();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void saveGame() {
        try {
            model.save(model.getGame());
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public UUID placeShip(Move move) {
        try {
            return model.placeShip(move);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
        return null;
    }

    public void endShipPlacement() {
        try {
            model.endPlaceShip();
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void play(Attack attack) {
        try {
            model.play(attack);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void setTactic(String stringTactic) {
        Tactic tactic;
        // Choose tactic
        switch (stringTactic) {
            case TacticView.RANDOM:
                tactic = TacticFactory.getRandomTactic();
                break;
            case TacticView.LINEAR:
                tactic = TacticFactory.getLinearTactic();
                break;
            default:
                tactic = TacticFactory.getRandomTactic();
                break;
        }
        try {
            model.setTactic(1, tactic);
        } catch (RemoteException e) {
            e.printStackTrace();
        }
    }

    public void getShipInformations(UUID currentShip) {
        model.getShipInformations(currentShip);
    }

    public LiaisonRMI getModel() {
        return this.model;
    }
}
