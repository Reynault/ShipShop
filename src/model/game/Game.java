package model.game;

import model.Attack;
import model.Move;
import model.ShipType;
import model.game.era.Era;
import model.game.player.Player;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;

import java.awt.*;
import java.io.Serializable;
import java.util.UUID;

public class Game implements Serializable {

    public static int GRID_WIDTH = 10;
    public static int GRID_HEIGHT = 10;
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
        UUID uuid = players[currentPlayer].placeShip(move);
        System.out.println("PlaceShip Game : "+ uuid);
        return uuid;
    }

    /**
     * funtion used for launch an attck on an ennemy ship
     * @param attack
     */
    public void play(Attack attack){
        //Get the next player (Who play after the current)
        Player nextPlayer = whosNext();

        System.out.println("Attack start at (X,Y) by player : "+ players[currentPlayer] + " ");
        System.out.println("Attack on position ("+ attack.getX()+ "," +  attack.getY() + ")");

        //Test if the target at X and Y position is a ship
        if(nextPlayer.getGrid().isShip(attack.getX(),attack.getY())){
            System.out.println("Other Player have a ship at the position.");

            //Test if the Ship is sunk or not
            if(nextPlayer.getGrid().getShip(attack.getX(), attack.getY()).hasSunk()) {
                System.out.println("Other Player ship's have "+ nextPlayer.getGrid().getShip(attack.getX(),attack.getY()).getHp() + " hp.");

                //Test if the current Player Ship can fire
                if(players[currentPlayer].canAttack(attack.getShip())){
                    System.out.println("The Player can attack! Fire!");

                    System.out.println("The attack make " + players[currentPlayer].getDmg(attack.getShip()) + " damage.");
                    //Hit the ennemy Ship (decrease his hp of the global ship)
                    nextPlayer.hit(attack.getX(), attack.getY(), players[currentPlayer].getDmg(attack.getShip()));

                    //Decrease ammo of the ship who fire
                    players[currentPlayer].decreaseAmmo(attack.getShip());
                    System.out.println("The Player ship have "+players[currentPlayer].getAmmo(attack.getShip())+" ammo left.");

                    System.out.println("The attack hit the ennemy ship ! " + nextPlayer.getGrid().getShip(attack.getX(), attack.getY()).getHp() + " hp left.");

                    //Flag the tile who just be the target (you can't target it anymore)
                    players[currentPlayer].getGrid().flagTile(attack.getX(), attack.getY());
                    System.out.println("The tile at position (" + attack.getX() + "," + attack.getY() + ") was flag as hit\n");

                }else{
                    //Test display if the target tile was already shoot
                    System.out.println("The tile at position ("+ attack.getX()+","+attack.getY()+") was already flag as hit... or you don't have ammo\n");
                }

            }else{
                //Test display if ship is already sunk
                System.out.println("The ennemy Ship is already sunk...\n");
            }
        }else{
            //If the next player doesn't have a Ship in the target tile, the tile is flag as already hit
            //The tile can't be target anymore
            players[currentPlayer].getGrid().crossTile(attack.getX(),attack.getY());
            System.out.println("The attack at position ("+ attack.getX()+","+attack.getY()+") miss...\n");
        }

        currentPlayer = (currentPlayer+1)%2;

        // Verifying if the game is finished
        if(!isFinished()){
            // If not, we let the other player to play
            if(!nextPlayer.isHuman()){
                play(nextPlayer.getBestMove(whosNext()));
            }
        }
    }

    private boolean isFinished() {
        boolean res = false;
        if(players[currentPlayer].getLife() == 0 || players[(currentPlayer+1)%2].getLife() == 0){
            res = true;
        }else{

        }
        return res;
    }

    public Ship getShip(UUID uuid){
        return players[currentPlayer].getShip(uuid);
    }

    /**
     * Methode qui retourne le joueur suivant du joueur actuel
     */
    private Player whosNext(){
        return players[(currentPlayer == 0)?1:0];
    }


    /**
     * Methode qui permet de changer le joueur en cours (fin de tour début du tour pour l'autre joueur
     */
    public void setNext(){
        System.out.println("Current Player : "+currentPlayer);
        currentPlayer = (currentPlayer == 0)?1:0;
        System.out.println("Current Player after next : "+currentPlayer);

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




    /////////////////////////////////////////////////////////////////////
    /////////////////////////////////////////////////////////////////////
    ////////////////////////////////////////////////////////////////////
    /**
     * Methode de debugage pour récupérer les 2 joueurs
     * @return
     */
    public Player[] getPlayer(){
        return players;
    }


    /**
     * Methode de debugage pour afficher le joueur courrant
     * @return
     */
    public int getCurrentPlayer(){
        return currentPlayer;
    }

    public int getSize(ShipType type) {
        return players[currentPlayer].getSize(type);
    }

    public int getNbShip(ShipType cruiser) {
        return players[currentPlayer].getNbShip(cruiser);
    }

    public int getLife() {
        return players[currentPlayer].getLife();
    }

    public int getEnnemyLife() {
        return players[(currentPlayer+1)%2].getLife();
    }
}
