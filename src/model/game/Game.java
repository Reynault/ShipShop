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
import java.util.List;
import java.util.UUID;

public class Game {

    public static int GRID_WIDTH = 20;
    public static int GRID_HEIGHT = 20;
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
        System.out.println("Attack start at (X,Y) by player : "+ players[currentPlayer] + " ");
        System.out.println("Attack on position ("+ attack.getX()+ "," +  attack.getY() + ")");
        if(otherCurrentPlayer.getGrid().isShip(attack.getX(),attack.getY())){
            System.out.println("Other Player have a ship at the position.");
            if(players[currentPlayer].canAttack(attack.getShip())){
                System.out.println("The Player can attack! Fire!");
                int dmg = players[currentPlayer].getDmg(attack.getShip());
                System.out.println("The attack make "+ players[currentPlayer].getDmg(attack.getShip())+" damage.");
                otherCurrentPlayer.hit(attack.getX(),attack.getY(),dmg);
                System.out.println("The attack hit the ennemy ship ! "+otherCurrentPlayer.getGrid().getShip(attack.getX(), attack.getY()).getHp()+" hp left.");
                players[currentPlayer].getGrid().flagTile(attack.getX(),attack.getY());
                System.out.println("The tile at position ("+ attack.getX()+","+attack.getY()+") was flag as hit");

            }else{
                //Le joueur 2 ne peut pas attaquer la cible (bateau coulé ou case déjà touché)
                System.out.println("The tile at position ("+ attack.getX()+","+attack.getY()+") was already flag as hit... Please choose an other one");

            }
        }else{
            //Le joueur 2 n'a pas de bateau à l'endroit cible
            players[currentPlayer].getGrid().crossTile(attack.getX(),attack.getY());
            System.out.println("The attack at position ("+ attack.getX()+","+attack.getY()+") miss...\n");
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

}
