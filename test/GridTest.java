import model.DirectionConstant;
import model.Move;
import model.ShipType;
import model.game.grid.Grid;
import model.game.ship.FleetFactory;
import model.game.ship.ModernFleet;
import model.game.ship.Ship;
import org.junit.jupiter.api.*;

import java.util.UUID;

class GridTest {

    private Grid grid;
    private FleetFactory fleetFactory;

    @BeforeEach
    void init() {
        fleetFactory = new ModernFleet(5,5,5,5);
        grid = new Grid(fleetFactory.getSize(ShipType.CRUISER), fleetFactory.getSize(ShipType.CRUISER));
    }


    @DisplayName("PlaceShip")
    @Nested
    class PlaceShip {

        @DisplayName("Right")
        @Nested
        class Right {

            @Test
            void place_ship_down() {

                Move move = new Move(
                    0,0, DirectionConstant.DOWN, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNotNull(uuid);

            }

            @Test
            void place_ship_right() {

                Move move = new Move(
                        0,0, DirectionConstant.RIGHT, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNotNull(uuid);

            }

            @Test
            void place_ship_up() {

                Move move = new Move(
                        0,fleetFactory.getSize(ShipType.CRUISER)-1, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNotNull(uuid);

            }

            @Test
            void place_ship_left() {

                System.out.println(fleetFactory.getSize(ShipType.CRUISER));

                Move move = new Move(
                        fleetFactory.getSize(ShipType.CRUISER)-1,0, DirectionConstant.LEFT, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNotNull(uuid);

            }

        }

        @DisplayName("Boundary")
        @Nested
        class Boundary{

            @Test
            void place_ship_left_wall(){

                Move move = new Move(
                        0,0, DirectionConstant.LEFT, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_up_wall(){

                Move move = new Move(
                        0,0, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_down_wall(){

                Move move = new Move(
                        0,fleetFactory.getSize(ShipType.CRUISER)-1, DirectionConstant.DOWN, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_right_wall(){

                Move move = new Move(
                        fleetFactory.getSize(ShipType.CRUISER)-1,0, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_out_of_bound_negative_x(){

                Move move = new Move(
                        -50,0, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_out_of_bound_negative_y(){

                Move move = new Move(
                        0,-50, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_out_of_bound_y(){

                Move move = new Move(
                        0,fleetFactory.getSize(ShipType.CRUISER)+50, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_out_of_bound_x(){

                Move move = new Move(
                        fleetFactory.getSize(ShipType.CRUISER)+50,0, DirectionConstant.UP, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }

            @Test
            void place_ship_over_another(){

                Move move = new Move(
                        1,0, DirectionConstant.DOWN, ShipType.CRUISER
                );

                grid.placeShip(move, fleetFactory);

                move = new Move(
                        0,1, DirectionConstant.RIGHT, ShipType.CRUISER
                );

                UUID uuid = grid.placeShip(move, fleetFactory);

                Assertions.assertNull(uuid);

            }
        }

        @DisplayName("Existence")
        @Nested
        class Existence{

            @Test
            void place_ship_exist(){

                Move move = new Move(
                        0,0, DirectionConstant.RIGHT, ShipType.TORPEDO
                );


                UUID uuid = grid.placeShip(move, fleetFactory);

                Ship ship = grid.getShip(uuid);

                Assertions.assertNotNull(ship);

            }

            @Test
            void place_ship_same_ships(){

                Move move = new Move(
                        0,0, DirectionConstant.RIGHT, ShipType.TORPEDO
                );


                UUID uuid = grid.placeShip(move, fleetFactory);


                move = new Move(
                        0,1, DirectionConstant.RIGHT, ShipType.TORPEDO
                );


                UUID uuid2 = grid.placeShip(move, fleetFactory);

                Assertions.assertNotEquals(uuid, uuid2);

            }

        }
    }
}