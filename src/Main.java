import model.*;
import model.constant.DirectionConstant;
import model.constant.ShipType;
import model.game.era.EraFactory;
import model.game.player.tactic.CrossTactic;
import model.game.player.tactic.RandomTactic;
import model.informations.Attack;
import model.informations.Move;

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

                printEmptyLine();

        //Test de la fonction play

        System.out.println("CURRENT PLAYER : "+shipShop.getGame().getCurrentPlayer());
        Move move = new Move(5,5, DirectionConstant.UP, ShipType.TORPEDO);
        UUID uuid = shipShop.placeShip(move);
        System.out.println("UUID : "+ uuid);

        Move move2 = new Move(12,5, DirectionConstant.UP, ShipType.TORPEDO);
        UUID uuid2 = shipShop.placeShip(move2);

        System.out.println("List of ship in the grid : "+shipShop.getGame().getPlayer()[0].getGrid().isShip(uuid));
        System.out.println("List of ship in the grid : "+shipShop.getGame().getPlayer()[0].getGrid().isShip(uuid2));


        for(int i = 0; i < shipShop.getGame().getPlayer()[1].getGrid().getGrid_width(); i++){
            for(int y = 0; y<shipShop.getGame().getPlayer()[1].getGrid().getGrid_height(); y++){
                shipShop.play(new Attack(i, y, uuid));
            }
        }

        printEmptyLine();


    }


    private static void printEmptyLine(){
        System.out.println("\n///////////////////////////////////\n");
    }
}
