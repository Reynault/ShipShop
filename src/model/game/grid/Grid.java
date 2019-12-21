package model.game.grid;

import model.constant.DirectionConstant;
import model.constant.GridConstant;
import model.informations.Move;
import model.constant.ShipType;
import model.game.ship.FleetFactory;
import model.game.ship.Ship;

import java.io.Serializable;
import java.util.*;

/**
 * Class grid that represent the grids of a player
 */
public class Grid implements Serializable {

    private GridConstant[][] ennemyGrid;
    private GridConstant[][] playerGrid;

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

        ennemyGrid = new GridConstant[grid_width][grid_height];
        playerGrid = new GridConstant[grid_width][grid_height];

        for (int i = 0; i < grid_width; i++) {
            for (int j = 0; j < grid_height; j++) {
                ennemyGrid[i][j] = GridConstant.NONE;
                playerGrid[i][j] = GridConstant.NONE;
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

        System.out.println("fleetffactory : "+ fleetFactory);
        System.out.println("move : "+ move);

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

            System.out.println("Position : "+Arrays.asList(pos));
            System.out.println("Overflow ?  : "+overflow);

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
        System.out.println("UUID GRID : "+res);
        return res;
    }

    public boolean canAttack(int x, int y, UUID ship) {
        return ennemyGrid[x][y] == GridConstant.NONE
                && ships.containsKey(ship)
                && ships.get(ship).canAttack();
    }

    public int getDmg(UUID id) {
        return getShip(id).getDmg();
    }

    /**
     * This method is returning true if the player can't shoot anymore
     * @return defeated
     */
    public boolean isDefeated() {
        Ship ship;
        boolean defeated = true;

        Iterator<UUID> iterator = ships.keySet().iterator();
        while(iterator.hasNext() && defeated){
            ship = ships.get(iterator.next());

            if(ship.canAttack()){
                defeated = false;
            }
        }

        return defeated;
    }

    public boolean isFlag(int x, int y){
        return ennemyGrid[x][y] == GridConstant.FLAG;
    }

    public boolean isShip(int x, int y) {
        Iterator iterator = positions.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry mapentry = (Map.Entry) iterator.next();
            List<Position> positionList = (List<Position>) mapentry.getValue();
            for (Position p: positionList) {
                if(p.getX() == x && p.getY() == y) return true;
            }
        }
        return false;
    }

    /**
     * Methode pour le debugage et les tests
     * @param uuid
     * @return
     */
    public boolean isShip(UUID uuid){
        return positions.containsKey(uuid);
    }

    public Ship getShip(UUID uuid){
        return ships.get(uuid);
    }

    public Ship getShip(int x, int y) {
        Iterator iterator = positions.entrySet().iterator();
        UUID shipUUID = null;
        boolean isFound = false;
        while (iterator.hasNext() || !isFound) {
            Map.Entry mapentry = (Map.Entry) iterator.next();
            List<Position> positionList = (List<Position>) mapentry.getValue();
            for (Position p: positionList) {
                if(p.getX() == x && p.getY() == y) {
                    isFound = true;
                    shipUUID = (UUID)mapentry.getKey();
                }
            }
        }
        return getShip(shipUUID);
    }

    /**
     * Method cross Tile that add a cross in one
     * of the two grids of the player
     * @param x the x
     * @param y the y
     * @param player if true, it's adding in the player grid
     */
    public void crossTile(int x, int y, boolean player) {
        if(player){
            playerGrid[x][y] = GridConstant.CROSS;
        }else{
            ennemyGrid[x][y] = GridConstant.CROSS;
        }
    }

    public void flagTile(int x, int y, boolean player) {
        if(player){
            playerGrid[x][y] = GridConstant.FLAG;
        }else {
            ennemyGrid[x][y] = GridConstant.FLAG;
        }
    }

    public void hit(int x, int y, int hit) {
        //Test de si la case est un bateau
        if (isShip(x, y)) {
            //On récupère le bateau par les coordonnées
            if (getShip(x, y).hasSunk()) {
                getShip(x, y).hit(hit);
            }
        }
    }

    public int getGridWidth() {
        return grid_width;
    }

    public int getGridHeight() {
        return grid_height;
    }

    @Override
    public String toString() {
        return "Grid{" +
                "positions=" + positions +
                '}';
    }

    public int getAmmo(UUID ship) {
        return getShip(ship).getAmmo();
    }

    public void decreaseAmmo(UUID ship) {
        getShip(ship).decreaseAmmo();
    }

    public int getLife() {
        int max = 0;
        int res = 0;

        Ship ship;

        for(UUID uuid : ships.keySet()){
            ship = ships.get(uuid);

            max += ship.getMaxHp();
            res += ship.getHp();
        }

        return res * 100 / max;
    }

    public Object[] getShips() {
        Set<UUID> keys = ships.keySet();
        return keys.toArray();
    }
}
