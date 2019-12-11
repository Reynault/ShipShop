import model.ShipShop;
import model.ShipType;
import model.game.era.EraFactory;
import model.game.player.Player;
import model.game.player.tactic.CrossTactic;
import model.game.player.tactic.RandomTactic;
import model.game.ship.Ship;

public class Main {
    public static void main(String[] args) {
        System.out.println("Launching game ...");
        ShipShop shipShop = new ShipShop();
        shipShop.createGame(EraFactory.getModernEra(), new RandomTactic(), true);
        shipShop.drawShip(ShipType.TORPEDO);
        System.out.println("Game launched and Tropedo drawed !");
        System.out.println("Changing tactic for IA ...");
        shipShop.setTactic(1, new CrossTactic());
        System.out.println("Tactic Changed for IA !");
    }
}
