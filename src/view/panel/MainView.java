package view.panel;

import controller.GameController;
import model.DirectionConstant;
import view.constant.StringConstant;
import view.constant.TextureFactory;
import view.constant.Views;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.geom.AffineTransform;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Observable;

import static view.constant.GraphicConstant.HEIGHT_MAIN;
import static view.constant.GraphicConstant.WIDTH_MAIN;

public class MainView extends PanelView {

    // Data
    private final int width_cell, height_cell;

    private boolean select;
    private BufferedImage cursorImage;
    private int size;
    private int toward;
    private DirectionConstant[] direction;


    private final JPanel left;

    // Game review
    private JPanel gameReview;
    private final JLabel ammo;
    private final JLabel attack;
    private final JLabel life;

    // Placement buttons

    private JPanel sideBarPlacement;
    private JButton[] shipPlacement;

    private JButton[][] ennemy;
    private JButton[][] player;
    private JPanel ennemyGrid;
    private JPanel playerGrid;

    private JMenuBar menuBar;
    private JMenu menu;
    private JMenuItem changeTactic;
    private JMenuItem save;
    private JMenuItem exit;

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

        // Setting prefered size
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
        JLabel ennemyDescription = new JLabel(StringConstant.ENNEMY_TITLE, SwingConstants.CENTER);

        ennemyGrid = new JPanel();
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
        JLabel playerDescription = new JLabel(StringConstant.PLAYER_TITLE, SwingConstants.CENTER);

        playerGrid = new JPanel();
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
        shipPlacement[CRUISER].addActionListener(new ShipListener());
        shipPlacement[CRUISER].addMouseListener(selectionListener);

        shipPlacement[SUBMARINE] = new JButton(StringConstant.SUBMARINE);
        shipPlacement[SUBMARINE].addActionListener(new ShipListener());
        shipPlacement[SUBMARINE].addMouseListener(selectionListener);

        shipPlacement[AIRCRAFT] = new JButton(StringConstant.AIRCRAFT);
        shipPlacement[AIRCRAFT].addActionListener(new ShipListener());
        shipPlacement[AIRCRAFT].addMouseListener(selectionListener);

        shipPlacement[TORPEDO] = new JButton(StringConstant.TORPERDO);
        shipPlacement[TORPEDO].addActionListener(new ShipListener());
        shipPlacement[TORPEDO].addMouseListener(selectionListener);

        shipPlacement[READY] = new JButton(StringConstant.READY_BUTTON);
        shipPlacement[READY].addActionListener(new ReadyListener());

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
        gameReview.setLayout(new GridLayout(5,1));

        JLabel selectedShip = new JLabel(StringConstant.SELECTED_SHIP, SwingConstants.CENTER);
        life = new JLabel(StringConstant.LIFE, SwingConstants.CENTER);
        attack = new JLabel(StringConstant.ATTACK, SwingConstants.CENTER);
        ammo = new JLabel(StringConstant.AMMO, SwingConstants.CENTER);

        JButton endTurn = new JButton(StringConstant.END_TURN);
        endTurn.addActionListener(new EndTurnListener());

        gameReview.add(selectedShip);
        gameReview.add(life);
        gameReview.add(attack);
        gameReview.add(ammo);
        gameReview.add(endTurn);

        // Configuring data
        select = false;

        // Adding mouse listener
        this.addMouseListener(selectionListener);

        this.buildFrame();
    }

    private void changeStep(){
        select = false;
        sideBarPlacement.setVisible(false);
        left.add(gameReview, BorderLayout.WEST);
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    private class ReadyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            changeStep();
        }
    }

    private class ShipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            select = true;
            toward = 0;

            try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();

                cursorImage = ImageIO.read(ClassLoader.getSystemResource("sprite/aircraft.png"));

                size = 5;

                setCursor(toolkit.createCustomCursor(cursorImage, new Point(getX(),
                        getY()), "img"));

            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    private class EnnemyTileListener implements ActionListener{

        int x, y;

        public EnnemyTileListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    private class PlayerTileListener implements ActionListener {

        int x;
        int y;

        public PlayerTileListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if (select) {
                BufferedImage tmp, resizedImage;
                Graphics2D g;

                // Setting image depending on the direction
                switch (direction[toward]) {
                    case DOWN:
                    case UP:
                        resizedImage = new BufferedImage(width_cell, size * height_cell, BufferedImage.TYPE_INT_ARGB);
                        g = resizedImage.createGraphics();
                        g.drawImage(cursorImage, 0, 0, width_cell, size * height_cell, null);
                        g.dispose();
                        break;
                    case RIGHT:
                    case LEFT:
                        resizedImage = new BufferedImage(width_cell * size, height_cell, BufferedImage.TYPE_INT_ARGB);
                        g = resizedImage.createGraphics();
                        g.drawImage(cursorImage, 0, 0, width_cell * size, height_cell, null);
                        g.dispose();
                        break;
                    default:
                        resizedImage = new BufferedImage(width_cell * size, height_cell, BufferedImage.TYPE_INT_ARGB);
                        break;
                }

                // Adding image on buttons depending on the direction
                switch (direction[toward]){
                    case DOWN:
                        if((x-size)+1 >= 0) {
                            for (int i = 0; i < size; i++) {
                                tmp = resizedImage.getSubimage(
                                        0,
                                        i * height_cell,
                                        width_cell,
                                        height_cell
                                );

                                ImageIcon imageIcon = new ImageIcon(tmp);

                                player[x - i][y].setIcon(imageIcon);
                            }
                            select = false;
                            setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                    case UP:
                        if(x+size <= player.length) {
                            for(int i = 0; i < size; i++){
                                tmp = resizedImage.getSubimage(
                                        0,
                                        (size-(i+1)) * height_cell,
                                        width_cell,
                                        height_cell
                                );

                                ImageIcon imageIcon = new ImageIcon(tmp);

                                player[x+i][y].setIcon(imageIcon);
                            }
                            select = false;
                            setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                    case RIGHT:
                        if(y+size <= player.length) {
                            for (int i = 0; i < size; i++) {
                                tmp = resizedImage.getSubimage(
                                        i * height_cell,
                                        0,
                                        width_cell,
                                        height_cell
                                );


                                ImageIcon imageIcon = new ImageIcon(tmp);

                                player[x][y + i].setIcon(imageIcon);
                            }
                            select = false;
                            setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                    case LEFT:
                        if((y-size + 1) >= 0) {
                            for (int i = 0; i < size; i++) {
                                tmp = resizedImage.getSubimage(
                                        (size - (i + 1)) * height_cell,
                                        0,
                                        width_cell,
                                        height_cell
                                );

                                ImageIcon imageIcon = new ImageIcon(tmp);

                                player[x][y - i].setIcon(imageIcon);
                            }
                            select = false;
                            setCursor(Cursor.getDefaultCursor());
                        }
                        break;
                }


            }else{
                ImageIcon imageIcon = (ImageIcon) player[x][y].getIcon();

                if(imageIcon != null) {
                    BufferedImage tmp = (BufferedImage) imageIcon.getImage();

                    Graphics2D g = (Graphics2D) tmp.getGraphics();

                    g.drawImage(
                            TextureFactory.getInstance().getCross_ennemy(),
                            0,
                            0,
                            tmp.getWidth(),
                            tmp.getHeight(),
                            null
                    );
                }
            }
        }
    }

    private class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().setCurrent(Views.MENU);
        }
    }

    private class ChangeTacticListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().openView(Views.TACTIC);
        }

    }

    private class EndTurnListener implements ActionListener{
        @Override
        public void actionPerformed(ActionEvent e) {

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

}
