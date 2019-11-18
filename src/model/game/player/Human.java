package model.game.player;

public class Human extends Player{

    public Human() {
        super();
    }

    @Override
    public boolean isHuman() {
        return true;
    }
}
