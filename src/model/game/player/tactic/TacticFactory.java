package model.game.player.tactic;

public class TacticFactory {
    public static Tactic getRandomTactic(){
        return new RandomTactic();
    }

    public static Tactic getLinearTactic(){
        return new LinearTactic();
    }
}
