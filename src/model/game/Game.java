package model.game;

import model.constant.EraConstant;
import model.constant.GridConstant;
import model.constant.ShipType;
import model.game.era.Era;
import model.game.grid.Grid;
import model.game.player.Player;
import model.game.player.tactic.Tactic;
import model.game.ship.Ship;
import model.informations.Attack;
import model.informations.Move;
import model.informations.Review;

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
        if (p1.isHuman()) {
            currentPlayer = 0;
        } else {
            currentPlayer = 1;
        }
    }

    public void setTactic(int player, Tactic tactic) {
        players[player].setTactic(tactic);
    }

    /**
     * Method that end the place step of the game
     *
     * @return a boolean that is true if the step is finished
     */
    public boolean endPlaceShip() {
        Player next = players[(currentPlayer + 1) % 2];
        // Setting the current player
        players[currentPlayer].setReady(true);

        // If the other player is a human and isn't ready
        if (next.isHuman() && !next.isReady()) {
            // Then, it means that the step isn't finished
            return false;
        } else {
            // Else, the user isn't human, or is ready
            return true;
        }

    }

    public UUID placeShip(Move move) {
        UUID uuid = players[currentPlayer].placeShip(move);
//        System.out.println("PlaceShip Game : " + uuid);
        return uuid;
    }

    /**
     * funtion used for launch an attack on an ennemy ship
     *
     * @param attack
     */
    public Review play(Attack attack) {
        boolean canAttack = false;
        boolean playerSet = false;
        boolean alreadySunk = false;
        boolean ennemySet = false;
        boolean end = false;
        int xPlayer = 0;
        int yPlayer = 0;
        int xEnnemy = 0;
        int yEnnemy = 0;
        GridConstant player = GridConstant.NONE, ennemy = GridConstant.NONE;

        //Get the next player (Who play after the current)
        Player nextPlayer = whosNext();

        // --------PRINT---------
//        System.out.println("Attack start at (X,Y) by player : " + players[currentPlayer] + " ");
//        System.out.println("Attack on position (" + attack.getX() + "," + attack.getY() + ")");

        int x, y;
        x = attack.getX();
        y = attack.getY();
        UUID ship = attack.getShip();
        Grid targetGrid = nextPlayer.getGrid();

        System.out.println("VALEUR X : "+x);
        System.out.println("VALEUR Y : "+y);
        // Testing if the current ship can actually attack
        if(players[currentPlayer].canAttack(x, y, ship)){
            canAttack = true;

            //Decrease ammo of the ship who fire
            players[currentPlayer].decreaseAmmo(ship);

            //Test if the target at X and Y position is a ship
            if (targetGrid.isShip(x, y)) {

                // --------PRINT---------
//                System.out.println("Other Player have a ship at the position.");

                alreadySunk = targetGrid.getShip(x, y).hasSunk();

                // --------PRINT---------
//                    System.out.println("Other Player ship's have " + targetGrid.getShip(x, y).getHp() + " hp.");

                // --------PRINT---------
//                    System.out.println("The Player can attack! Fire!");
//                    System.out.println("The attack make " + players[currentPlayer].getDmg(ship) + " damage.");

                //Hit the ennemy Ship (decrease his hp of the global ship)
                nextPlayer.hit(x, y, players[currentPlayer].getDmg(ship));

                // --------PRINT---------
//                    System.out.println("The Player ship have " + players[currentPlayer].getAmmo(ship) + " ammo left.");
//                    System.out.println("The attack hit the ennemy ship ! " + targetGrid.getShip(x, y).getHp() + " hp left.");

                //Flag the tile who just be the target (you can't target it anymore)
                players[currentPlayer].getGrid().flagTile(x, y, false);
                nextPlayer.getGrid().flagTile(x, y, true);

                // --------PRINT---------
//                    System.out.println("The tile at position (" + x + "," + y + ") was flag as hit\n");

                xPlayer = x;
                yPlayer = y;
                player = GridConstant.FLAG;
                playerSet = true;

            } else {
                //If the next player doesn't have a Ship in the target tile, the tile is flag as already hit
                //The tile can't be target anymore
                players[currentPlayer].getGrid().crossTile(x, y, false);
                nextPlayer.getGrid().crossTile(x, y, true);

                xPlayer = x;
                yPlayer = y;
                player = GridConstant.CROSS;
                playerSet = true;

                // --------PRINT---------
//                System.out.println("The attack at position (" + x + "," + y + ") miss...\n");
            }

            currentPlayer = (currentPlayer + 1) % 2;

            // Verifying if the game is finished
            if (!isFinished()) {
                // If not, we let the other player to play
                if (!nextPlayer.isHuman()) {
                    Review res = play(nextPlayer.getBestMove(this, whosNext()));
                    xEnnemy = res.getxPlayer();
                    yEnnemy = res.getyPlayer();
                    ennemy = res.getPlayer();
                    ennemySet = true;

                    if(res.isEnd()){
                        end = true;
                    }
                }
            }else{
                end = true;
                System.out.println("qsdqsdqdsqdsqdsqdssqdqdsqdsqdsqdsqdsqdsqsdqsdqsdds");
            }
        }else if(!players[currentPlayer].isHuman()){
            setNext();
        }

        return new Review(canAttack, alreadySunk, ennemySet && playerSet, end, xPlayer, yPlayer, xEnnemy, yEnnemy, player, ennemy);
    }

    private boolean isFinished() {
        boolean res = false;
        if (players[currentPlayer].isDefeated() || players[(currentPlayer + 1) % 2].isDefeated()) {
            res = true;
        }
        return res;
    }

    public Ship getShip(UUID uuid) {
        return players[currentPlayer].getShip(uuid);
    }

    /**
     * Methode qui retourne le joueur suivant du joueur actuel
     */
    private Player whosNext() {
        return players[(currentPlayer == 0) ? 1 : 0];
    }


    /**
     * Methode qui permet de changer le joueur en cours (fin de tour début du tour pour l'autre joueur
     */
    public void setNext() {
//        System.out.println("Current Player : " + currentPlayer);
        currentPlayer = (currentPlayer == 0) ? 1 : 0;
//        System.out.println("Current Player after next : " + currentPlayer);
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
     *
     * @return
     */
    public Player[] getPlayer() {
        return players;
    }


    /**
     * Methode de debugage pour afficher le joueur courrant
     *
     * @return
     */
    public int getCurrentPlayer() {
        return currentPlayer;
    }

    public int getSize(ShipType type) {
        return players[currentPlayer].getSize(type);
    }

    public int getNbShip(ShipType cruiser) {
        return players[currentPlayer].getNbShip(cruiser);
    }

    public int getLife(int num) {
        return players[num].getLife();
    }

    public EraConstant getEra() {
        return era.getName();
    }

    public Player getPlayerHuman() {
        return (p1.isHuman())? p1: p2;
    }
}
