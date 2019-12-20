package view.panel;

import model.DirectionConstant;
import view.constant.StringConstant;
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
import java.awt.image.AffineTransformOp;
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
    private int RANGE_Y, RANGE_X;
    private int toward;
    private DirectionConstant[] direction;

    // Placement buttons
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

    public MainView(MainObserver mainObserver) {
        super(mainObserver);

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

        JPanel left = new JPanel();
        JPanel right = new JPanel();

        left.setLayout(new BorderLayout());
        right.setLayout(new BorderLayout());

        global.add(left);
        global.add(right);

        // Setting ennemy grid
        ennemyGrid = new JPanel();
        ennemyGrid.setLayout(new GridLayout(WIDTH_PANEL, HEIGHT_PANEL));
        ennemyGrid.setBorder(new EmptyBorder(10,10,10,10));

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
                ennemy[i][j].addActionListener(new PlayerTileListener(i, j));
                ennemy[i][j].addMouseListener(selectionListener);

                ennemyGrid.add(ennemy[i][j], i, j);
            }
        }

        // Setting player grid
        playerGrid = new JPanel();
        playerGrid.setLayout(new GridLayout(WIDTH_PANEL, HEIGHT_PANEL));
        playerGrid.setBorder(new EmptyBorder(10,10,10,10));

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

        shipPlacement[CRUISER] = new JButton("0");
        shipPlacement[CRUISER].addActionListener(new ShipListener());
        shipPlacement[CRUISER].addMouseListener(selectionListener);

        shipPlacement[SUBMARINE] = new JButton("1");
        shipPlacement[SUBMARINE].addActionListener(new ShipListener());
        shipPlacement[SUBMARINE].addMouseListener(selectionListener);

        shipPlacement[AIRCRAFT] = new JButton("2");
        shipPlacement[AIRCRAFT].addActionListener(new ShipListener());
        shipPlacement[AIRCRAFT].addMouseListener(selectionListener);

        shipPlacement[TORPEDO] = new JButton("3");
        shipPlacement[TORPEDO].addActionListener(new ShipListener());
        shipPlacement[TORPEDO].addMouseListener(selectionListener);

        shipPlacement[READY] = new JButton(StringConstant.READY_BUTTON);
        shipPlacement[READY].addActionListener(new ReadyListener());

        // Setting side bar with ship placement
        JPanel sidebar = new JPanel();
        sidebar.setLayout(new GridLayout(shipPlacement.length, WIDTH_SIDEBAR));

        left.add(sidebar, BorderLayout.WEST);

        // Adding buttons
        for (JButton button : shipPlacement) {
            sidebar.add(button);
        }

        // Configuring data
        select = false;

        // Adding mouse listener
        this.addMouseListener(selectionListener);

        this.buildFrame();
    }

    @Override
    public void update(Observable o, Object arg) {

    }

    public class ReadyListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }

    public class ShipListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            select = true;
            toward = 0;

            try {
                Toolkit toolkit = Toolkit.getDefaultToolkit();

                cursorImage = ImageIO.read(ClassLoader.getSystemResource("sprite/submarine.png"));

                RANGE_Y = 4;
                RANGE_X = 1;

                setCursor(toolkit.createCustomCursor(cursorImage, new Point(getX(),
                        getY()), "img"));

                width = cursorImage.getWidth();
                height = cursorImage.getHeight();
            } catch (IOException e1) {
                e1.printStackTrace();
            }

        }
    }

    public class PlayerTileListener implements ActionListener {

        int x;
        int y;

        public PlayerTileListener(int x, int y) {
            this.x = x;
            this.y = y;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            if(select){
                BufferedImage tmp;

                BufferedImage resizedImage = new BufferedImage(RANGE_X *width_cell, RANGE_Y *height_cell, BufferedImage.TYPE_INT_ARGB);
                Graphics2D g = resizedImage.createGraphics();
                g.drawImage(cursorImage, 0, 0, RANGE_X *width_cell, RANGE_Y *height_cell, null);
                g.dispose();

                System.out.println();
                // Checking if we can add the ship in the grid
                if((x - RANGE_Y) >= 0 && (x - RANGE_Y) <  player.length &&
                        (y + RANGE_X) >= 0 && (y + RANGE_X) < player[x].length) {

                    // Setting ship in the grid
                    for (int i = 0; i < RANGE_Y; i++) {
                        for (int j = 0; j < RANGE_X; j++) {


                            tmp = resizedImage.getSubimage(
                                    j * width_cell,
                                    i * height_cell,
                                    width_cell,
                                    height_cell
                            );

                            ImageIcon imageIcon = new ImageIcon(tmp);

                            player[x - i][y + j].setIcon(imageIcon);

                        }
                    }

                }

                select = false;
                setCursor(Cursor.getDefaultCursor());

            }
        }
    }

    public class ExitListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().setCurrent(Views.MENU);
        }
    }

    public class ChangeTacticListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            getMainObserver().openView(Views.TACTIC);
        }

    }

    float width, height;

    public class SelectionListener implements MouseListener {


        @Override
        public void mouseClicked(MouseEvent e) {
        }

        @Override
        public void mousePressed(MouseEvent e) {
            // If a ship is selected
            if (select) {
                if (e.getButton() == MouseEvent.BUTTON3) {
                    toward = (toward+1) % 4;

                    Toolkit toolkit = Toolkit.getDefaultToolkit();

                    // Rotating the form
                    double theta = Math.PI/2;
                    double cos = Math.abs(Math.cos(theta));
                    double sin = Math.abs(Math.sin(theta));
                    double width  = cursorImage.getWidth();
                    double height = cursorImage.getHeight();
                    int w = (int)(width * cos + height * sin);
                    int h = (int)(width * sin + height * cos);

                    BufferedImage out = new BufferedImage(w, h, cursorImage.getType());

                    Graphics2D g2 = out.createGraphics();
                    double x = w/2;
                    double y = h/2;
                    AffineTransform at = AffineTransform.getRotateInstance(theta, x, y);
                    x = (w - width)/2;
                    y = (h - height)/2;
                    at.translate(x, y);
                    g2.drawRenderedImage(cursorImage, at);
                    g2.dispose();

                    cursorImage = out;

                    setCursor(toolkit.createCustomCursor(cursorImage, new Point(getX(),
                            getY()), "img"));


                    // Changing the range
                    int tmp = RANGE_Y;
                    RANGE_Y = RANGE_X;
                    RANGE_X = tmp;
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
