import model.ShipShop;
import model.ShipType;
import model.game.era.EraFactory;
import model.game.player.tactic.RandomTactic;
import model.game.ship.Ship;

public class Main {
    public static void main(String[] args) {
        System.out.println("Launching game ...");
        ShipShop shipShop = new ShipShop();
        shipShop.createGame(EraFactory.getModernEra(), new RandomTactic(), false);
        shipShop.drawShip(ShipType.TORPEDO);
    }
}
