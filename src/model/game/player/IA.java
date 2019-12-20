package model.game.player;

import model.DirectionConstant;
import model.Move;
import model.ShipType;
import model.game.Game;
import model.game.player.tactic.Tactic;
import model.game.ship.FleetFactory;

import java.util.Random;

/**
 * An IA is a player that is controled by the computer with a specific tactic
 */
public class IA extends Player{

    private Tactic tactic;

    /**
     * IA constructor that place ships
     * @param fleetFactory
     */
    public IA(FleetFactory fleetFactory) {
        super(fleetFactory);

        Random rand = new Random();
        Move move;

        // Filling the list of ships
        for(ShipType ship: ShipType.values()){
            while(fleetFactory.hasShip(ship)) {
                move = new Move(
                        rand.nextInt(grid.getGrid_width()),
                        rand.nextInt(grid.getGrid_height()),
                        DirectionConstant.getRandomDirection(),
                        ship
                );

                while(grid.placeShip(move, fleetFactory) == null) {
                    move = new Move(
                            rand.nextInt(grid.getGrid_width()),
                            rand.nextInt(grid.getGrid_height()),
                            DirectionConstant.getRandomDirection(),
                            ship
                    );
                }
            }
        }
    }

    public Move getBestMove(){
        return null;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    public void setTactic(Tactic tactic){
        this.tactic = tactic;
    }

}
