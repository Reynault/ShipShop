package model.game.grid;

import model.DirectionConstant;
import model.Move;
import model.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.Ship;

import java.io.Serializable;
import java.util.*;

/**
 * Class grid that represent the grids of a player
 */
public class Grid implements Serializable {

    // Three constants that define if the tile has been shot, and if and ennemy/ship has been discovered
    private static final int NONE = 100;
    private static final int HIT = 200;
    private static final int TOUCHED = 300;

    private int[][] ennemyGrid;
    private int[][] playerGrid;

    private int grid_width;
    private int grid_height;

    private Map<UUID, Ship> ships;
    private Map<UUID, List<Position>> positions;

    /**
     * Default constructor that initialise everything
     */
    public Grid(int grid_width, int grid_height) {
        this.grid_height = grid_height;
        this.grid_width = grid_width;

        ennemyGrid = new int[grid_width][grid_height];
        playerGrid = new int[grid_width][grid_height];

        for (int i = 0; i < grid_width; i++) {
            for (int j = 0; j < grid_height; j++) {
                ennemyGrid[i][j] = NONE;
                playerGrid[i][j] = NONE;
            }

        }


        ships = new HashMap<>();
        positions = new HashMap<>();
    }

    /**
     * Place ship is a method that place the wanted ship
     * in the grid, it returns a null object if it can't, otherwise
     * it's the uuid of the new ship
     *
     * @param move         the move that place the ship
     * @param fleetFactory the fleet factory that create the ship
     * @return the uuid
     */
    public UUID placeShip(Move move, FleetFactory fleetFactory) {

        // Checking if in objects are null
        if (move == null || fleetFactory == null) {
            return null;
        }

        // Getting needed values
        UUID res = null;
        Ship ship;
        int x = move.getX();
        int y = move.getY();
        DirectionConstant direction = move.getDirection();
        ShipType shipType = move.getType();

        // Getting size
        int size = fleetFactory.getSize(shipType);

        // Checking if we can place the ship in the grid
        if (x >= 0 && x < grid_width && y >= 0 && y < grid_height && fleetFactory.hasShip(move.getType())) {

            // Then checking if the ship is overflowing the grid
            boolean overflow;
            Iterator<UUID> iterator;
            List<Position> check, pos;
            pos = new ArrayList<>();

            switch (direction) {
                case LEFT:
                    overflow = ((x - size) + 1) < 0;
                    for (int i = 0; i < size; i++) {
                        pos.add(new Position(x - i, y));
                    }
                    break;
                case UP:
                    overflow = ((y - size) + 1) < 0;
                    for (int i = 0; i < size; i++) {
                        pos.add(new Position(x, y - i));
                    }
                    break;
                case RIGHT:
                    overflow = ((x + size) - 1) >= grid_width;
                    for (int i = 0; i < size; i++) {
                        pos.add(new Position(x + i, y));
                    }
                    break;
                case DOWN:
                    overflow = ((y + size) - 1) >= grid_height;
                    for (int i = 0; i < size; i++) {
                        pos.add(new Position(x, y + i));
                    }
                    break;
                default:
                    overflow = true;
                    break;
            }

            if (!overflow) {

                // Then checking if the ship is on another ship
                iterator = positions.keySet().iterator();
                boolean exist = false;

                while (iterator.hasNext() && !exist) {
                    check = positions.get(iterator.next());

                    for (Position p : check) {
                        if (pos.contains(p)) {
                            exist = true;
                        }
                    }

                }

                // If no current ship exists, creating ship
                if (!exist) {
                    res = UUID.randomUUID();
                    ship = fleetFactory.getShip(move.getType(), move.getDirection());
                    ships.put(res, ship);
                    positions.put(res, pos);
                }
            }

        }

        return res;
    }

    public boolean canAttack(UUID ship) {
        return false;
    }

    public int getDmg(UUID id) {
        return 0;
    }

    public boolean isDefeated() {
        return false;
    }

    public boolean isShip(int x, int y) {
        return false;
    }

    public Ship getShip(UUID uuid) {
        return ships.get(uuid);
    }

    public Ship getShip(int x, int y) {
        return null;
    }

    public void crossTile(int x, int y) {

    }

    public void flagTile(int x, int y) {

    }

    public void hit(int x, int y, int hit) {
        //Test de si la case est un bateau
        if (isShip(y, y)) {
            //On récupère le bateau par les coordonnées
            if (!getShip(x, y).hasSunk()) {
                getShip(x, y).hit(hit);
            }
        }
    }

    public int getGrid_width() {
        return grid_width;
    }

    public int getGrid_height() {
        return grid_height;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "positions=" + positions +
                '}';
    }
}
