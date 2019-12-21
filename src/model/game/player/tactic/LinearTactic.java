package model.game.player.tactic;

import model.game.Game;
import model.game.player.Player;
import model.informations.Attack;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public class LinearTactic implements Tactic, Serializable {
    private Random rand;

    public LinearTactic() {
        rand = new Random();
    }

    @Override
    public Attack applyTactic(Game game, Player attacker, Player victim) {

        int i = 0;
        int j = 0;

        Object[] ships = attacker.getShips();
        UUID ship = (UUID) ships[rand.nextInt(ships.length)];

        while (! attacker.getShip(ship).canAttack()){
            ship = (UUID) ships[rand.nextInt(ships.length)];
        }

        boolean found = false;
        while(i < victim.getWidth() && ! found){
            while(j < victim.getHeight() && ! found){
                if(attacker.canAttack(i, j, ship)){
                    found = true;
                }
                j++;
            }
            i++;
        }

        return new Attack(i-1, j-1, ship);
    }

}
