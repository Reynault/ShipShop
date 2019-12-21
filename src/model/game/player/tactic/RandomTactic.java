package model.game.player.tactic;

import model.game.Game;
import model.game.player.Player;
import model.informations.Attack;

import java.io.Serializable;
import java.util.Random;
import java.util.UUID;

public class RandomTactic implements Tactic, Serializable {
    private Random rand;

    public RandomTactic() {
        rand = new Random();
    }

    @Override
    public Attack applyTactic(Game game, Player attacker, Player victim) {
        Object[] ships = attacker.getShips();

        int width = victim.getWidth();
        int height = victim.getHeight();

        // Selecting one random attack movement
        Attack attack = selectRandomAttack(ships, width, height);

        if(!attacker.isDefeated()) {
            // While this one isn't valid
            while (!attacker.canAttack(attack.getX(), attack.getY(), attack.getShip())) {
                // Selecting another
                attack = selectRandomAttack(ships, width, height);
            }
            System.out.println(" ? ");
        }

        return attack;
    }

    private Attack selectRandomAttack(Object[] ships, int width, int height) {
        int x, y;
        UUID id;

        x = rand.nextInt(width);
        y = rand.nextInt(height);
        id = (UUID)ships[rand.nextInt(ships.length)];
        System.out.println(id);

        return new Attack(x, y, id);
    }
}
