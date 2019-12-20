import model.*;
import model.game.era.EraFactory;
import model.game.grid.Grid;
import model.game.player.Player;
import model.game.player.tactic.CrossTactic;
import model.game.player.tactic.RandomTactic;
import model.game.ship.ModernFleet;
import model.game.ship.Ship;

import java.io.File;
import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Launching game ...");
        ShipShop shipShop = new ShipShop();
        shipShop.createGame(EraFactory.getModernEra(), new RandomTactic(), true);
        shipShop.drawShip(ShipType.TORPEDO);
        System.out.println("Game launched and Tropedo drawed !");
        System.out.println("Changing tactic for IA ...");
        shipShop.setTactic(1, new CrossTactic());
        System.out.println("Tactic Changed for IA !");
        System.out.println("Saving game ...");
        shipShop.save(shipShop.getGame());
        System.out.println("Saving game done ...");
        System.out.println("Loading game ...");
        ShipShop shipShopbis = new ShipShop();
        shipShopbis.load();
        System.out.println(shipShopbis);
        System.out.println("Loading game done ...");
        ModernFleet modernFleet = new ModernFleet(2,2,2,2);
        Ship ship = new Ship(5, 5, 0, 2, DirectionConstant.UP, ShipType.TORPEDO);
        Grid grid = new Grid();
    }
}
