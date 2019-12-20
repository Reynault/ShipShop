package model.game.grid;

import model.game.Game;

public class GridFactory {
    public static Grid getSimpleGrid(){
        return new Grid(Game.GRID_WIDTH, Game.GRID_HEIGHT);
    }
}
