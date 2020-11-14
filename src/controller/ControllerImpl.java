package controller;

import java.io.IOException;
import java.util.Objects;
import java.util.Scanner;

import game.MazeGame;
import game.MazeGameImpl;

/**
 * A controller for the maze game. This controller works with a Readable and Appendable object. It
 * has been designed to accept a sequence of multiple inputs from the Readable object.
 */
public class ControllerImpl implements Controller {
  private final Readable in;
  private final Appendable out;
  MazeGame game;

  public ControllerImpl(Readable in, Appendable out) {
    this.in = in;
    this.out = out;
  }

  @Override
  public void createMaze() {
    try (Scanner scan = new Scanner(this.in)) {
      int rows = scan.nextInt();
      int cols = scan.nextInt();
      int remains = scan.nextInt();
      boolean isPerfect = scan.next() == "true"? true: false;
      boolean isWrapping = scan.next() == "true"?true: false;
      int playerPosX = scan.nextInt();
      int playerPosY = scan.nextInt();
      MazeGame game = new MazeGameImpl(rows, cols, remains, isPerfect, isWrapping,
              playerPosX, playerPosY);
      this.game = game;
    }
  }

  @Override
  public void start() throws IOException {
    Objects.requireNonNull(game);
    String direction;
    int distance;
    try (Scanner scan = new Scanner(this.in)) {
      while (true) {
        switch (scan.next()) {
          case "shoot":
            direction = scan.next();
            distance = scan.nextInt();
            game.shoot(direction, distance);
            break;
          case "move":
            direction = scan.next();
            game.move(direction);
          case "q":
            return;
          default:
            throw new IllegalStateException("ERROR: should not get here");
        }
      }
    }
  }
}
