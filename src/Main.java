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
import java.util.UUID;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Launching game ...");
        ShipShop shipShop = new ShipShop();
        shipShop.createGame(EraFactory.getXVIEra(), new RandomTactic(), true);
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

        System.out.println("Size of grid is : " + grid.getGridSizeX()+", " + grid.getGridSizeY());
        Move move = new Move(5,5, DirectionConstant.UP, ShipType.TORPEDO);
        UUID uuid = shipShop.placeShip(move);

        System.out.println("There's a ship : "+grid.getShip(uuid));


        shipShop.play(new Attack(0,0, uuid));
        shipShop.play(new Attack(5,5, uuid));
        shipShop.play(new Attack(2,1, uuid));
        shipShop.play(new Attack(3,1, uuid));
        shipShop.play(new Attack(4,1, uuid));
        shipShop.play(new Attack(5,1, uuid));
        shipShop.play(new Attack(6,1, uuid));
        shipShop.play(new Attack(7,1, uuid));
        shipShop.play(new Attack(8,1, uuid));
        shipShop.play(new Attack(9,1, uuid));
        shipShop.play(new Attack(10,1, uuid));
        shipShop.play(new Attack(11,1, uuid));
        shipShop.play(new Attack(12,1, uuid));
        shipShop.play(new Attack(13,1, uuid));
        shipShop.play(new Attack(14,1, uuid));
        shipShop.play(new Attack(15,1, uuid));
        shipShop.play(new Attack(16,1, uuid));
        shipShop.play(new Attack(17,1, uuid));
        shipShop.play(new Attack(18,1, uuid));
        shipShop.play(new Attack(19,1, uuid));
        shipShop.play(new Attack(20,1, uuid));
    }
}
