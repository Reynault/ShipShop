package view.panel;

import controller.GameController;
import model.*;
import model.constant.DirectionConstant;
import model.constant.GridConstant;
import model.constant.ShipType;
import model.constant.UpdateObserver;
import model.game.ship.Ship;
import model.informations.Attack;
import model.informations.Move;
import model.informations.Review;
import view.constant.StringConstant;
import view.constant.TextureFactory;
import view.constant.Views;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.util.Observable;
import java.util.UUID;

import static view.constant.GraphicConstant.HEIGHT_MAIN;
import static view.constant.GraphicConstant.WIDTH_MAIN;

public class MainView extends PanelView {

    // Data
    private final int width_cell, height_cell;

    // Data for the movement
    private BufferedImage cursorImage;
    private int currentSize;
    private ShipType currentType;
    private DirectionConstant[] direction;
    private int toward;

    // Data for an attack
    private UUID currentShip;
    private int xTarget, yTarget;
    private Attack plannedAttack;

    // Game review
    private JPanel gameReview;
    private final JLabel ammo;
    private final JLabel attack;
    private final JLabel life;
    private final JLabel selectedShip;
    private final JButton endTurn;
    private boolean inGame;

    // Placement buttons

    private boolean select;
    private JPanel sideBarPlacement;
    private JButton[] shipPlacement;

    // Global

    private final JPanel left;
    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem changeTactic;
    private JMenuItem save;
    private JMenuItem exit;
    private JLabel ennemyDescription;
    private JLabel playerDescription;

    private JButton[][] ennemy;
    private JButton[][] player;

    // Constants
    private final int WIDTH_PANEL = 10;
    private final int HEIGHT_PANEL = 10;

    private final int WIDTH_SIDEBAR = 1;

    private final int CRUISER = 0;
    private final int SUBMARINE = 1;
    private final int TORPEDO = 2;
    private final int AIRCRAFT = 3;
    private final int READY = 4;

    public MainView(MainObserver mainObserver, GameController controller) {
        super(mainObserver, controller);

        // Creating mouse listener
        SelectionListener selectionListener = new SelectionListener();

        direction = new DirectionConstant[4];
        direction[0] = DirectionConstant.UP;
        direction[1] = DirectionConstant.RIGHT;
        direction[2] = DirectionConstant.DOWN;
        direction[3] = DirectionConstant.LEFT;
        toward = 0;

        // Setting prefered currentSize
        this.setPreferredSize(
                new Dimension(WIDTH_MAIN, HEIGHT_MAIN)
        );

        // Setting main layout
        this.setLayout(new BorderLayout());

        // Setting menu bar
        menuBar = new JMenuBar();
        menu = new JMenu(StringConstant.MENUBAR_TITLE);

        menuBar.add(menu);

        changeTactic = new JMenuItem(StringConstant.CHANGE_TACTIC);
        changeTactic.addActionListener(new ChangeTacticListener());
        save = new JMenuItem(StringConstant.SAVE_GAME);
        save.addActionListener(new SaveListener());
        exit = new JMenuItem(StringConstant.EXIT_GAME);
        exit.addActionListener(new ExitListener());


        menu.add(changeTactic);
        menu.add(save);
        menu.add(exit);

        this.add(menuBar, BorderLayout.NORTH);

        JPanel global = new JPanel();
        global.setLayout(new GridLayout(1, 2));
        this.add(global, BorderLayout.CENTER);

        left = new JPanel();
        JPanel right = new JPanel();

        left.setLayout(new BorderLayout());
        right.setLayout(new BorderLayout());

        global.add(left);
        global.add(right);

        // Setting ennemy grid
        ennemyDescription = new JLabel(StringConstant.ENNEMY_TITLE, SwingConstants.CENTER);

        JPanel ennemyGrid = new JPanel();
        ennemyGrid.setLayout(new GridLayout(WIDTH_PANEL, HEIGHT_PANEL));
        ennemyGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        right.add(ennemyDescription, BorderLayout.NORTH);
        right.add(ennemyGrid, BorderLayout.CENTER);
        right.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Filling in ennemy grid
        ennemy = new JButton[HEIGHT_PANEL][WIDTH_PANEL];

        width_cell = 50;
        height_cell = 50;

        for (int i = 0; i < HEIGHT_PANEL; i++) {
            for (int j = 0; j < WIDTH_PANEL; j++) {
                ennemy[i][j] = new JButton();
                ennemy[i][j].setPreferredSize(new Dimension(
                        width_cell, height_cell
                ));
                ennemy[i][j].addActionListener(new EnnemyTileListener(i, j));
                ennemy[i][j].addMouseListener(selectionListener);

                ennemyGrid.add(ennemy[i][j], i, j);
            }
        }

        // Setting player grid
        playerDescription = new JLabel(StringConstant.PLAYER_TITLE, SwingConstants.CENTER);

        JPanel playerGrid = new JPanel();
        playerGrid.setLayout(new GridLayout(WIDTH_PANEL, HEIGHT_PANEL));
        playerGrid.setBorder(new EmptyBorder(10, 10, 10, 10));

        left.add(playerDescription, BorderLayout.NORTH);
        left.add(playerGrid, BorderLayout.CENTER);
        left.setBorder(new EmptyBorder(10, 10, 10, 10));

        // Filling player grid
        player = new JButton[HEIGHT_PANEL][WIDTH_PANEL];

        for (int i = 0; i < HEIGHT_PANEL; i++) {
            for (int j = 0; j < WIDTH_PANEL; j++) {
                player[i][j] = new JButton();
                player[i][j].setPreferredSize(new Dimension(
                        width_cell, height_cell
                ));
                player[i][j].addActionListener(new PlayerTileListener(i, j));
                player[i][j].addMouseListener(selectionListener);

                playerGrid.add(player[i][j], i, j);
            }
        }

        left.add(playerGrid, BorderLayout.CENTER);

        // Setting ship placement buttons
        shipPlacement = new JButton[5];

        shipPlacement[CRUISER] = new JButton(StringConstant.CRUISER);
        shipPlacement[CRUISER].addMouseListener(selectionListener);

        shipPlacement[SUBMARINE] = new JButton(StringConstant.SUBMARINE);
        shipPlacement[SUBMARINE].addMouseListener(selectionListener);

        shipPlacement[AIRCRAFT] = new JButton(StringConstant.AIRCRAFT);
        shipPlacement[AIRCRAFT].addMouseListener(selectionListener);

        shipPlacement[TORPEDO] = new JButton(StringConstant.TORPEDO);
        shipPlacement[TORPEDO].addMouseListener(selectionListener);

        shipPlacement[READY] = new JButton(StringConstant.READY_BUTTON);
        shipPlacement[READY].addActionListener(new ReadyListener());
        shipPlacement[READY].setEnabled(false);

        // Setting side bar with ship placement
        sideBarPlacement = new JPanel();
        sideBarPlacement.setLayout(new GridLayout(shipPlacement.length, WIDTH_SIDEBAR));

        left.add(sideBarPlacement, BorderLayout.WEST);

        // Adding buttons
        for (JButton button : shipPlacement) {
            sideBarPlacement.add(button);
        }

        // Setting side bar with game review
        gameReview = new JPanel();
        gameReview.setLayout(new GridLayout(5, 1));

        selectedShip = new JLabel(StringConstant.SELECTED_SHIP, SwingConstants.CENTER);
        life = new JLabel(StringConstant.LIFE + "-", SwingConstants.CENTER);
        attack = new JLabel(StringConstant.ATTACK + "-", SwingConstants.CENTER);
        ammo = new JLabel(StringConstant.AMMO + "-", SwingConstants.CENTER);

        endTurn = new JButton(StringConstant.END_TURN);
        endTurn.setEnabled(false);
        endTurn.addActionListener(new EndTurnListener());

        gameReview.add(selectedShip);
        gameReview.add(life);
        gameReview.add(attack);
        gameReview.add(ammo);
        gameReview.add(endTurn);

        // Configuring data
        select = false;
        inGame = false;

        // Adding mouse listener
        this.addMouseListener(selectionListener);

        this.buildFrame();
    }

    private void changeStep() {
        // Indicate to the model that we change the current state
        controller.endShipPlacement();

        // Select is now false (no more ship selected)
        select = false;
        inGame = true;

        // Removing the ship placing sidebar
        sideBarPlacement.setVisible(false);

        // Adding the information bar
        left.add(gameReview, BorderLayout.WEST);
    }

    @Override
    public void update(Observable o, Object arg) {
        UpdateObserver val = (UpdateObserver) arg;

        ShipShop shipShop = (ShipShop) o;

        switch (val) {
            // When creating a game
            case CREATE_GAME:

                // Setting ship button values
                shipPlacement[CRUISER].addActionListener(
                        new ShipListener(
                                (BufferedImage) shipShop.drawShip(ShipType.CRUISER),
                                shipShop.getSize(ShipType.CRUISER),
                                ShipType.CRUISER
                        )
                );

                shipPlacement[SUBMARINE].addActionListener(
                        new ShipListener(
                                (BufferedImage) shipShop.drawShip(ShipType.SUBMARINE),
                                shipShop.getSize(ShipType.SUBMARINE),
                                ShipType.SUBMARINE
                        )
                );

                shipPlacement[TORPEDO].addActionListener(
                        new ShipListener(
                                (BufferedImage) shipShop.drawShip(ShipType.TORPEDO),
                                shipShop.getSize(ShipType.TORPEDO),
                                ShipType.TORPEDO
                        )
                );

                shipPlacement[AIRCRAFT].addActionListener(
                        new ShipListener(
                                (BufferedImage) shipShop.drawShip(ShipType.AIRCRAFT),
                                shipShop.getSize(ShipType.AIRCRAFT),
                                ShipType.AIRCRAFT
                        )
                );

                majPlacementShip(shipShop);

                break;

            case PLACE_SHIP:
                majPlacementShip(shipShop);
                break;

            case LAUNCH:
                // Updating the two labels on the top of the two grids
                playerDescription.setText(
                        StringConstant.PLAYER_TITLE + " - " + StringConstant.REMAINING_TITLE + shipShop.getLife() + "%"
                );

                ennemyDescription.setText(
                        StringConstant.ENNEMY_TITLE + " - " + StringConstant.REMAINING_TITLE + shipShop.getEnnemyLife() + "%"
                );
                break;

            case GET_SHIP_INFO:
                Ship ship = shipShop.getShip(currentShip);

                if(ship.canAttack()){
                    ammo.setFont(new Font("Dialog", Font.PLAIN, 12));
                    ammo.setForeground(Color.BLACK);
                    ammo.setText(
                            StringConstant.AMMO + ship.getAmmo()
                    );

                    attack.setText(
                            StringConstant.ATTACK + ship.getDmg()
                    );

                    life.setText(
                            StringConstant.LIFE + ship.getHp()
                    );
                }else{

                    if(!ship.canShoot()) {
                        ammo.setText(
                                StringConstant.CAN_NOT_SHOOT
                        );

                        attack.setText(
                                ""
                        );

                        life.setText(
                                ""
                        );
                    }else{
                        ammo.setText(
                                StringConstant.DEAD
                        );

                        attack.setText(
                                ""
                        );

                        life.setText(
                                ""
                        );
                    }

                    ammo.setFont(new Font("Dialog", Font.PLAIN, 13));
                    ammo.setForeground(Color.RED);
                }

                selectedShip.setText(
                        ship.getShipType() + ""
                );

                break;

            case CAN_NOT_ATTACK:

                break;

            case END_GAME:

                if (shipShop.getLife() > shipShop.getEnnemyLife()){
                    mainObserver.setEndMessage(StringConstant.WIN_MESSAGE);
                }else if(shipShop.getLife() == shipShop.getEnnemyLife()) {
                    mainObserver.setEndMessage(StringConstant.EQUALITY_MESSAGE);
                }else{
                    mainObserver.setEndMessage(StringConstant.LOSE_MESSAGE);
                }

                mainObserver.setCurrent(Views.END_GAME);

                break;

            case END_TURN:
                plannedAttack = null;
                currentShip = null;

                endTurn.setEnabled(false);

                Review review = shipShop.getTurnReview();

                int x, y, tmp;
                GridConstant state;

                // If data are set
                if(review.isDataSet()){
                    // Update ennemy grid first
                    x = review.getxPlayer();
                    y = review.getyPlayer();
                    state = review.getPlayer();

                    tmp = x;
                    x = (WIDTH_PANEL - 1) - y;
                    y = tmp;

                    switch (state) {
                        case FLAG:
                            ennemy[x][y].setIcon(new ImageIcon(
                                    TextureFactory.getInstance().getFlagPlayer().getScaledInstance(width_cell, height_cell, Image.SCALE_DEFAULT)
                            ));
                            break;
                        case CROSS:
                            ennemy[x][y].setIcon(new ImageIcon(
                                    TextureFactory.getInstance().getCrossPlayer().getScaledInstance(width_cell, height_cell, Image.SCALE_DEFAULT)
                            ));
                            break;
                    }

                    // Update player grid
                    x = review.getxEnnemy();
                    y = review.getyEnnemy();
                    state = review.getEnnemy();

                    tmp = x;
                    x = (WIDTH_PANEL - 1) - y;
                    y = tmp;

                    ImageIcon image = (ImageIcon) player[x][y].getIcon();

                    BufferedImage buffer = new BufferedImage(width_cell, height_cell, BufferedImage.TYPE_INT_ARGB);

                    if (image != null) {
                        buffer = (BufferedImage) image.getImage();
                    }

                    switch (state) {
                        case FLAG:
                            buffer.getGraphics().drawImage(
                                    TextureFactory.getInstance().getFlagEnnemy(),
                                    0,
                                    0,
                                    width_cell,
                                    height_cell,
                                    null
                            );
                            break;
                        case CROSS:
                            buffer.getGraphics().drawImage(
                                    TextureFactory.getInstance().getCrossEnnemy(),
                                    0,
                                    0,
                                    width_cell,
                                    height_cell,
                                    null
                            );
                            break;
                    }

                    player[x][y].setIcon(new ImageIcon(buffer));
                }

                // Updating the two labels on the top of the two grids
                playerDescription.setText(
                        StringConstant.PLAYER_TITLE + " - " + StringConstant.REMAINING_TITLE + shipShop.getLife() + "%"
                );

                ennemyDescription.setText(
                        StringConstant.ENNEMY_TITLE + " - " + StringConstant.REMAINING_TITLE + shipShop.getEnnemyLife() + "%"
                );

                ammo.setText(
                        StringConstant.AMMO + "-"
                );

                attack.setText(
                        StringConstant.ATTACK + "-"
                );

                life.setText(
                        StringConstant.LIFE + "-"
                );

                selectedShip.setText(StringConstant.SELECTED_SHIP);

                break;
        }
    }

    private void majPlacementShip(ShipShop shipShop) {
        int nbCruiser = shipShop.getNbShip(ShipType.CRUISER);
        shipPlacement[CRUISER].setText(
                StringConstant.CRUISER + nbCruiser
        );

        if (nbCruiser == 0) {
            shipPlacement[CRUISER].setEnabled(false);
        }

        int nbSubmarine = shipShop.getNbShip(ShipType.SUBMARINE);
        shipPlacement[SUBMARINE].setText(
                StringConstant.CRUISER + nbSubmarine
        );

        if (nbSubmarine == 0) {
            shipPlacement[SUBMARINE].setEnabled(false);
        }

        int nbTorpedo = shipShop.getNbShip(ShipType.TORPEDO);
        shipPlacement[TORPEDO].setText(
                StringConstant.TORPEDO + nbTorpedo
        );

        if (nbTorpedo == 0) {
            shipPlacement[TORPEDO].setEnabled(false);
        }

        int nbAircraft = shipShop.getNbShip(ShipType.AIRCRAFT);
        shipPlacement[AIRCRAFT].setText(
                StringConstant.AIRCRAFT + nbAircraft
        );

        if (nbAircraft == 0) {
            shipPlacement[AIRCRAFT].setEnabled(false);
        }

        if (nbAircraft == 0 && nbCruiser == 0 && nbSubmarine == 0 && nbTorpedo == 0) {
            shipPlacement[READY].setEnabled(true);
        }
    }

    private class ReadyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            changeStep();
        }
    }

    private class ShipListener implements ActionListener {

        private BufferedImage image;
        private int size;
        private ShipType type;

        public ShipListener(BufferedImage image, int size, ShipType type) {
            this.image = image;
            this.size = size;
            this.type = type;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            select = true;
            toward = 0;
            cursorImage = image;
            currentSize = size;
            currentType = type;

            // When triggering a ship placement button, the cursor becomes the new ship
            Toolkit toolkit = Toolkit.getDefaultToolkit();
            setCursor(toolkit.createCustomCursor(cursorImage, new Point(getX(),
                    getY()), "img"));
        }
    }

    private class EnnemyTileListener implements ActionListener {

        int x, y;

        public EnnemyTileListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // If we are in the second part of the game, and if there is a ship selected
            if (inGame && currentShip != null) {

                // If the current button is already checked
                ImageIcon image = (ImageIcon) ennemy[x][y].getIcon();

                if(image == null) {

                    // If there is already one, we erase
                    if (plannedAttack != null) {
                        ennemy[xTarget][yTarget].setIcon(null);
                    }

                    // Then, we can generate an attack
                    int xGrid = y;
                    int yGrid = (WIDTH_PANEL - 1) - x;

                    plannedAttack = new Attack(xGrid, yGrid, currentShip);
                    xTarget = x;
                    yTarget = y;


                    // Adding new icon
                    ennemy[x][y].setIcon(new ImageIcon(
                            TextureFactory.getInstance().getPlannedAttack().getScaledInstance(width_cell, height_cell, Image.SCALE_DEFAULT)
                    ));

                    // And finally, the player can attack
                    endTurn.setEnabled(true);
                }
            }
        }
    }

    private class PlayerTileListener implements ActionListener {

        UUID relatedShip;
        int x;
        int y;

        public PlayerTileListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public void setRelatedShip(UUID relatedShip) {
            this.relatedShip = relatedShip;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            // What happen when the game is launched
            if (inGame) {
                // If the button is related to a ship
                if (relatedShip != null) {

                    currentShip = relatedShip;
                    controller.getShipInformations(currentShip);

                }
            }

            // What happen in the ship placement step
            if (select) {
                BufferedImage tmp, resizedImage;
                Graphics2D g;

                // Creating move
                int xGrid = y;
                int yGrid = 9 - x;
                DirectionConstant constant = direction[toward];
                ShipType type = currentType;

                Move currentMove = new Move(xGrid, yGrid, constant, type);
                UUID res = controller.placeShip(currentMove);

                if (res != null) {

                    // Setting image depending on the direction
                    switch (direction[toward]) {
                        case DOWN:
                        case UP:
                            resizedImage = new BufferedImage(width_cell, currentSize * height_cell, BufferedImage.TYPE_INT_ARGB);
                            g = resizedImage.createGraphics();
                            g.drawImage(cursorImage, 0, 0, width_cell, currentSize * height_cell, null);
                            g.dispose();
                            break;
                        case RIGHT:
                        case LEFT:
                            resizedImage = new BufferedImage(width_cell * currentSize, height_cell, BufferedImage.TYPE_INT_ARGB);
                            g = resizedImage.createGraphics();
                            g.drawImage(cursorImage, 0, 0, width_cell * currentSize, height_cell, null);
                            g.dispose();
                            break;
                        default:
                            resizedImage = new BufferedImage(width_cell * currentSize, height_cell, BufferedImage.TYPE_INT_ARGB);
                            break;
                    }

                    // Adding image on buttons depending on the direction
                    switch (direction[toward]) {
                        case DOWN:
                            if ((x - currentSize) + 1 >= 0) {
                                for (int i = 0; i < currentSize; i++) {
                                    tmp = resizedImage.getSubimage(
                                            0,
                                            i * height_cell,
                                            width_cell,
                                            height_cell
                                    );

                                    ImageIcon imageIcon = new ImageIcon(tmp);

                                    player[x - i][y].setIcon(imageIcon);

                                    PlayerTileListener a = (PlayerTileListener) (player[x - i][y].getActionListeners()[0]);
                                    a.setRelatedShip(res);
                                }
                                select = false;
                                setCursor(Cursor.getDefaultCursor());
                            }
                            break;
                        case UP:
                            if (x + currentSize <= player.length) {
                                for (int i = 0; i < currentSize; i++) {
                                    tmp = resizedImage.getSubimage(
                                            0,
                                            (currentSize - (i + 1)) * height_cell,
                                            width_cell,
                                            height_cell
                                    );

                                    ImageIcon imageIcon = new ImageIcon(tmp);

                                    player[x + i][y].setIcon(imageIcon);
                                    PlayerTileListener a = (PlayerTileListener) (player[x + i][y].getActionListeners()[0]);
                                    a.setRelatedShip(res);
                                }
                                select = false;
                                setCursor(Cursor.getDefaultCursor());
                            }
                            break;
                        case RIGHT:
                            if (y + currentSize <= player.length) {
                                for (int i = 0; i < currentSize; i++) {
                                    tmp = resizedImage.getSubimage(
                                            i * height_cell,
                                            0,
                                            width_cell,
                                            height_cell
                                    );


                                    ImageIcon imageIcon = new ImageIcon(tmp);

                                    player[x][y + i].setIcon(imageIcon);
                                    PlayerTileListener a = (PlayerTileListener) (player[x][y + i].getActionListeners()[0]);
                                    a.setRelatedShip(res);
                                }
                                select = false;
                                setCursor(Cursor.getDefaultCursor());
                            }
                            break;
                        case LEFT:
                            if ((y - currentSize + 1) >= 0) {
                                for (int i = 0; i < currentSize; i++) {
                                    tmp = resizedImage.getSubimage(
                                            (currentSize - (i + 1)) * height_cell,
                                            0,
                                            width_cell,
                                            height_cell
                                    );

                                    ImageIcon imageIcon = new ImageIcon(tmp);

                                    player[x][y - i].setIcon(imageIcon);

                                    PlayerTileListener a = (PlayerTileListener) (player[x][y - i].getActionListeners()[0]);
                                    a.setRelatedShip(res);
                                }
                                select = false;
                                setCursor(Cursor.getDefaultCursor());
                            }
                            break;
                    }

                }
            }
        }
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainObserver.setCurrent(Views.MENU);
        }
    }

    private class ChangeTacticListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            mainObserver.openView(Views.TACTIC);
        }

    }

    private class EndTurnListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            ennemy[xTarget][yTarget].setIcon(null);
            controller.play(plannedAttack);
        }
    }

    private class SelectionListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // If a ship is selected
            if (select) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    toward = (toward + 1) % 4;

                    Toolkit toolkit = Toolkit.getDefaultToolkit();

                    // Rotating the form
                    double theta = Math.PI / 2;
                    double cos = Math.abs(Math.cos(theta));
                    double sin = Math.abs(Math.sin(theta));
                    double width = cursorImage.getWidth();
                    double height = cursorImage.getHeight();
                    int w = (int) (width * cos + height * sin);
                    int h = (int) (width * sin + height * cos);

                    BufferedImage out = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);

                    Graphics2D g2 = out.createGraphics();
                    double x = w / 2;
                    double y = h / 2;
                    AffineTransform at = AffineTransform.getRotateInstance(theta, x, y);
                    x = (w - width) / 2;
                    y = (h - height) / 2;
                    at.translate(x, y);
                    g2.drawRenderedImage(cursorImage, at);
                    g2.dispose();

                    cursorImage = out;

                    setCursor(toolkit.createCustomCursor(cursorImage, new Point(getX(),
                            getY()), "img"));
                }
            }
        }

        @Override
        public void mouseReleased(MouseEvent e) {

        }

        @Override
        public void mouseEntered(MouseEvent e) {

        }

        @Override
        public void mouseExited(MouseEvent e) {

        }
    }

    private class SaveListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            controller.saveGame();
        }
    }
}
