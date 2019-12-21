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
        UUID tmp, ship = null;
        int k = 0;

        while(k < ships.length && ship == null){
            tmp = (UUID) ships[k];
            if(attacker.getShip(tmp).canAttack()){
                ship = tmp;
            }
            k++;
        }

        boolean found = false;
        while(i < victim.getWidth() && ! found){
            while(j < victim.getHeight() && ! found){
                if(attacker.canAttack(i, j, ship)){
                    found = true;
                    System.out.println("real J : " + (j));
                    System.out.println("real I : " + (i));
                }
                j++;
            }
            j = 0;
            i++;
        }

        System.out.println("J : " + (j-1));
        System.out.println("I : " + (i-1));

        return new Attack(j-1, i-1, ship);
    }

}
