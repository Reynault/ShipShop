package model.game.grid;

import model.Move;
import model.game.Game;
import model.game.ship.Ship;

import java.util.UUID;

public class Grid {

    protected int[][] ennemyGrid = new int[Game.GRID_WIDTH][Game.GRID_HEIGHT];


    public Grid(){

    }


    public void placeShip(Move move){

    }

    public boolean canAttack(UUID ship){
        return false;
    }

    public int getDmg(UUID id){
        return 0;
    }

    public boolean isDefeated(){
        return false;
    }

    public boolean isShip(int x, int y){
        return false;
    }

    public Ship getShip(UUID uuid){
        return null;
    }

    public Ship getShip(int x, int y){
        return null;
    }

    public void crossTile(int x, int y){

    }

    public void flagTile(int x, int y){

    }

    public void hit(int x, int y, int hit){
        //Test de si la case est un bateau
        if(isShip(y, y)){
            //On récupère le bateau par les coordonnées
            if(!getShip(x, y).hasSunk()){
                getShip(x, y).hit(hit);
            }
        }
    }



}
