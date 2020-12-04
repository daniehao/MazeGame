package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.util.function.Consumer;

import javax.swing.*;

import model.Cell;

/**
 * The class for the view of the MazeGame.
 */
public class MazeLayout extends JFrame implements GameView {
  private int playerNum;
  private String alert;
  private MazePanel alertPanel;
  private MazePanel mazePanel;
  private MazePanel controlPanel;
  private JScrollPane scrollPane;
  private JButton quitButton;
  private JButton startSameButton;
  private JButton moveUp;
  private JButton moveDown;
  private JButton moveLeft;
  private JButton moveRight;
  private JButton shootUp;
  private JButton shootDown;
  private JButton shootLeft;
  private JButton shootRight;
  private JTextField shootDistance;
  private JButton applyMoveButton;
  private JButton applyShootButton;
  private int rows;
  private int cols;
  private transient Consumer<String> commandCallback;

  /**
   * Default constructor.
   */
  public MazeLayout() {
    super();
    initGaps();
    this.setTitle("Maze Game!");
    this.setSize(800, 800);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    this.setLayout(new BorderLayout());
    alertPanel = new MazePanel();
    alertPanel.setPreferredSize(new Dimension(200, 50));
    alertPanel.add(new Label("Player " + playerNum + "'s Round"));
    alertPanel.add(new Label(alert));
    this.add(alertPanel, BorderLayout.NORTH);

    // maze panel
    mazePanel = new MazePanel();
    mazePanel.setPreferredSize(new Dimension(500, 500));
    mazePanel.setLayout(new GridLayout(10,10));
    scrollPane = new JScrollPane(mazePanel);
    this.add(scrollPane, BorderLayout.CENTER);

    // move
    controlPanel = new MazePanel();
    controlPanel.setLayout(new GridLayout(4, 6));
    controlPanel.add(new Label("Move Direction: "));
    controlPanel.add (moveUp);
    controlPanel.add(moveDown);
    controlPanel.add(moveLeft);
    controlPanel.add(moveRight);

    // shoot
    controlPanel.add(new Label("Shoot Direction: "));
    controlPanel.add (shootUp);
    controlPanel.add(shootDown);
    controlPanel.add(shootLeft);
    controlPanel.add(shootRight);
    controlPanel.add(new Label("Shoot Distance: "));
    controlPanel.add(shootDistance);

    controlPanel.add(new Label(" "));
    controlPanel.add(new Label(" "));
    controlPanel.add(new Label(" "));

    //quit
    controlPanel.add(applyMoveButton);
    controlPanel.add(applyShootButton);
    controlPanel.add(new Label(" "));
    controlPanel.add(startSameButton);
    startSameButton.setActionCommand("Start Same Game");
    quitButton.addActionListener((ActionEvent e) -> {
      System.exit(0);
    });
    controlPanel.add(quitButton);
    this.add(controlPanel,BorderLayout.SOUTH);
  }

  public void initGaps() {
    moveUp = new JButton("Up");
    moveDown = new JButton("Down");
    moveLeft = new JButton("Left");
    moveRight = new JButton("Right");
    shootUp = new JButton("Up");
    shootDown = new JButton("Down");
    shootLeft = new JButton("Left");
    shootRight = new JButton("Right");
    shootDistance = new JTextField(5);
    applyMoveButton = new JButton("Move");
    applyShootButton = new JButton("Shoot");
    startSameButton = new JButton("Start Same Game");
    quitButton = new JButton("Quit");
  }

  public void setMazeDimension(int rows, int cols) {
    this.rows = rows;
    this.cols = cols;
  }

  public void setPlayer(int player) {
    playerNum = player;
  }

  public void setAlertString(String alert) {
    this.alert = alert;
  }

  @Override
  public void makeVisible() {
    this.setVisible(true);
  }

  @Override
  public void setCommandCallback(Consumer<String> callback) {
    commandCallback = callback;
  }

  @Override
  public void showErrorMessage(String error) {
    JOptionPane.showMessageDialog(this, error, "Error",
            JOptionPane.ERROR_MESSAGE);

  }

  @Override
  public void buildMaze(Cell[] cells) {
    ImageIcon mazeIcon = null;
    for (Cell element : cells) {
      if (element.getIsRoom()) {
        int roomNum = element.geRoomDoors();
        if (roomNum == 1) {
          if (element.getUpCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-1-up.png");
          }
          if (element.getDownCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-1-down.png");
          }
          if (element.getLeftCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-1-left.png");
          }
          if (element.getRightCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-1-right.png");
          }
        } else if (roomNum == 3) {
          if (element.getUpCell() != null && element.getLeftCell() != null && element.getDownCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-3-up-down-left.png");
          }
          if (element.getUpCell() != null && element.getLeftCell() != null && element.getRightCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-3-up-left-right.png");
          }
          if (element.getUpCell() != null && element.getRightCell() != null && element.getDownCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-3-up-down-right.png");
          }
          if (element.getDownCell() != null && element.getLeftCell() != null && element.getRightCell() != null) {
            mazeIcon = new ImageIcon("../../res/images/roombase-1-down-left-right.png");
          }
        } else {
          mazeIcon = new ImageIcon("../../res/images/roombase-4.png");
        }
      } else if (element.getIsTunnel()) {
        if (element.getLeftCell() != null && element.getRightCell() != null) {
          mazeIcon = new ImageIcon("../../res/images/hallway-h.png");
        }
        if (element.getLeftCell() != null && element.getUpCell() != null) {
          mazeIcon = new ImageIcon("../../res/images/hallway-up-left.png");
        }
        if (element.getLeftCell() != null && element.getDownCell() != null) {
          mazeIcon = new ImageIcon("../../res/images/hallway-down-left.png");
        }
        if (element.getUpCell() != null && element.getDownCell() != null) {
          mazeIcon = new ImageIcon("../../res/images/hallway-v.png");
        }
        if (element.getDownCell() != null && element.getRightCell() != null) {
          mazeIcon = new ImageIcon("../../res/images/hallway-down-right.png");
        }
        if (element.getUpCell() != null && element.getRightCell() != null) {
          mazeIcon = new ImageIcon("../../res/images/hallway-up-right.png");
        }
      }

//      ImageIcon grassIcon = new ImageIcon("images/grass_tile.jpg");
      JPanel panel = new JPanel(new GridLayout(3, 5, 0, 0));

      JLabel labels[] = new JLabel[(3 * 5)];

      for (
              int i = 0;
              i < 3 * 5; i++) {
        labels[i] = new JLabel(mazeIcon);
        panel.add(labels[i]);
      }
    }
  }

  public void createAndShowGUI() {
    //Create and set up the window.
    MazeLayout frame = new MazeLayout();
    frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    //Set up the content pane.
    //Display the window.
    frame.pack();
    frame.setVisible(true);
  }


//  public static void main(String[] args) {
//    /* Use an appropriate Look and Feel */
//    try {
//      //UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
//      UIManager.setLookAndFeel("javax.swing.plaf.metal.MetalLookAndFeel");
//    } catch (UnsupportedLookAndFeelException ex) {
//      ex.printStackTrace();
//    } catch (IllegalAccessException ex) {
//      ex.printStackTrace();
//    } catch (InstantiationException ex) {
//      ex.printStackTrace();
//    } catch (ClassNotFoundException ex) {
//      ex.printStackTrace();
//    }
//    /* Turn off metal's use of bold fonts */
//    UIManager.put("swing.boldMetal", Boolean.FALSE);
//
//    //Schedule a job for the event dispatch thread:
//    //creating and showing this application's GUI.
//    javax.swing.SwingUtilities.invokeLater(new Runnable() {
//      public void run() {
//        createAndShowGUI();
//      }
//    });
//  }
}
