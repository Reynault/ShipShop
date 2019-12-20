package model.game;

import model.Attack;
import model.Move;
import model.ShipType;
import model.game.era.Era;
import model.game.player.IA;
import model.game.player.Player;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;

import java.awt.*;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;

public class Game implements Serializable {

    public static int GRID_WIDTH;
    public static int GRID_HEIGHT;
    private int currentPlayer;
    private Era era;
    private final Player p1;
    private final Player p2;
    private Player[] players;


    public Game(Era era, Player p1, Player p2) {
        this.era = era;
        this.p1 = p1;
        this.p2 = p2;
        this.players = new Player[2];
        this.players[0] = this.p1;
        this.players[1] = this.p2;
        if (p1.isHuman()){
            currentPlayer = 0;
        }else{
            currentPlayer = 1;
        }
    }

    public void setTactic(int player, Tactic tactic){
        players[player].setTactic(tactic);
    }

    public Image drawShip(ShipType type){
        return era.drawShip(type);
    }

    /**
     * Method that end the place step of the game
     * @return a boolean that is true if the step is finished
     */
    public boolean endPlaceShip(){
        Player next = players[(currentPlayer + 1) % 2];
        // Setting the current player
        players[currentPlayer].setReady(true);

        // If the other player is a human and isn't ready
        if (next.isHuman() && !next.isReady()){
            // Then, it means that the step isn't finished
            return false;
        }else{
            // Else, the user isn't human, or is ready
            return true;
        }

    }

    public UUID placeShip(Move move){
        return players[currentPlayer].placeShip(move);
    }

    public List<Attack> play(Attack attack){
        Player otherCurrentPlayer = next();
        if(otherCurrentPlayer.getGrid().isShip(attack.getX(),attack.getY())){
            if(players[currentPlayer].canAttack(attack.getShip())){
                int dmg = players[currentPlayer].getDmg(attack.getShip());
                otherCurrentPlayer.hit(attack.getX(),attack.getY(),dmg);
                players[currentPlayer].getGrid().flagTile(attack.getX(),attack.getY());
            }else{
                //Le joueur 2 ne peut pas attaquer la cible (bateau coulé ou case déjà touché)
            }
        }else{
            //Le joueur 2 n'a pas de bateau à l'endroit cible
            players[currentPlayer].getGrid().crossTile(attack.getX(),attack.getY());
        }
        return null;
    }

    public Ship getShip(UUID uuid){
        return null;
    }

    /**
     * Methode qui retourne le joueur suivant du joueur actuel
     */
    private Player next(){
        return players[(currentPlayer == 0)?1:0];
    }

    @Override
    public String toString() {
        return "Game{" +
                "currentPlayer=" + currentPlayer +
                ", era=" + era +
                ", p1=" + p1 +
                ", p2=" + p2 +
                '}';
    }
}
