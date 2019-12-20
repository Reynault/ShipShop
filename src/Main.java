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
//        shipShop.drawShip(ShipType.TORPEDO);
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

                printEmptyLine();

        //Test du changement de joueur
        shipShop.getGame().setNext();

        shipShop.getGame().setNext();

        shipShop.getGame().setNext();

                printEmptyLine();

        //Test de la fonction play
        ModernFleet modernFleet = new ModernFleet(2,2,2,2);
        Ship ship = modernFleet.getShip(ShipType.TORPEDO);
        Grid grid = new Grid();
        Move move = new Move(5,5, DirectionConstant.UP, ShipType.TORPEDO);
        UUID uuid = shipShop.placeShip(move);
        System.out.println("Size of grid is : " + grid.getGridSizeX()+", " + grid.getGridSizeY());
        System.out.println("List of ship in the grid : "+grid.getShip(uuid));

        shipShop.getGame().setNext();

        Grid grid2 = new Grid();
        Move move2 = new Move(12,5, DirectionConstant.UP, ShipType.TORPEDO);
        UUID uuid2 = shipShop.placeShip(move);

        System.out.println("Size of grid2 is : " + grid2.getGridSizeX()+", " + grid2.getGridSizeY());
        System.out.println("List of ship in the grid2 : "+grid2.getShip(uuid));


        shipShop.play(new Attack(12,5, uuid));
        shipShop.play(new Attack(5,5, uuid));
        shipShop.play(new Attack(2,1, uuid));
        shipShop.play(new Attack(3,1, uuid));

    }


    private static void printEmptyLine(){
        System.out.println("\n///////////////////////////////////\n");
    }
}
