package model;

import java.util.HashMap;
import java.util.Map;

/**
 * The cell class store all of the information about each cell, which includes whether the cell is
 * closing to a wumpus, whether it has wumpus, whether it has a pit, whether it is closing to a pit,
 * whether it has a bat in the cell.
 */
public class Cell {
  //string is the direction, cell is the next cell.
  Map<String, Cell> cellMap;
  boolean isTunnel;
  boolean isRoom;
  boolean closeToWumpus;
  boolean isWumpus;
  boolean isPit;
  boolean closeToPit;
  boolean hasBat;
  boolean couldReachToWumpus;
  boolean isTarget;
  int x;
  int y;

  /**
   * The constructor for Cell class.
   */
  public Cell(int x, int y) {
    cellMap = new HashMap<>();
    cellMap.put("left", null);
    cellMap.put("right", null);
    cellMap.put("up", null);
    cellMap.put("down", null);
    closeToWumpus = false;
    isWumpus = false;
    isPit = false;
    closeToPit = false;
    hasBat = false;
    couldReachToWumpus = false;
    this.x = x;
    this.y = y;
  }

  /**
   * Set the pointer to the next cell.
   *
   * @param nextCell      The cell next to the current cell.
   * @param directionSide The direction pointer to the next cell.
   */
  public void setNextCell(Cell nextCell, String directionSide) {
    switch (directionSide) {
      case "left":
        cellMap.put("left", nextCell);
        break;
      case "right":
        cellMap.put("right", nextCell);
        break;
      case "up":
        cellMap.put("up", nextCell);
        break;
      case "down":
        cellMap.put("down", nextCell);
        break;
      default:
    }
  }

  /**
   * Get the cell on the left side of the current cell.
   *
   * @return The cell on the left side of current.
   */
  public Cell getLeftCell() {
    return cellMap.get("left");
  }

  /**
   * Get the cell on the right side of the current cell.
   *
   * @return The cell on the right side of current.
   */
  public Cell getRightCell() {
    return cellMap.get("right");
  }

  /**
   * Get the cell on the up side of the current cell.
   *
   * @return The cell on the up side of current.
   */
  public Cell getUpCell() {
    return cellMap.get("up");
  }

  /**
   * Get the cell on the down side of the current cell.
   *
   * @return The cell on the down side of current.
   */
  public Cell getDownCell() {
    return cellMap.get("down");
  }

  /**
   * Set the room status for the cell.
   *
   * @param isRoom Whether the cell is a room/cave.
   */
  public void setIsRoom(boolean isRoom) {
    this.isRoom = isRoom;
  }

  /**
   * Set the tunnel status for the cell.
   *
   * @param isTunnel Whether the cell is a tunnel or not.
   */
  public void setIsTunnel(boolean isTunnel) {
    this.isTunnel = isTunnel;
  }

  /**
   * Set the wumpus status as true for the cell.
   */
  public void setIsWumpus() {
    this.isWumpus = true;
  }

  /**
   * Get the wumpus status for the cell.
   */
  public boolean getIsWumpus() {
    return isWumpus;
  }

  /**
   * Set the close to wumpus status as true for the cell.
   */
  public void setCloseToWumpus() {
    this.closeToWumpus = true;
  }

  /**
   * Get if the cell is close to wumpus.
   *
   * @return True/False.
   */
  public boolean getCloseToWumpus() {
    return closeToWumpus;
  }

  /**
   * Set the cell has bats as true.
   */
  public void setHasBat() {
    hasBat = true;
  }

  /**
   * Get if the cell has bats.
   */
  public boolean getHasBat() {
    return hasBat;
  }

  /**
   * Set the cell as a pit.
   */
  public void setIsPit() {
    isPit = true;
  }

  /**
   * Set the cell if it is close to a pit.
   */
  public void setCloseToPit() {
    closeToPit = true;
  }

  /**
   * Get if the cell is a pit.
   *
   * @return True/False.
   */
  public boolean getIsPit() {
    return isPit;
  }

  /**
   * Get if the cell is close to a pit.
   *
   * @return True/False.
   */
  public boolean getCloseToPit() {
    return closeToPit;
  }

  public boolean getIsRoom() {
    return isRoom;
  }

  public boolean getIsTunnel() {
    return isTunnel;
  }

  public int geRoomDoors() {
    int count = 0;
    if (getUpCell() != null) {
      count++;
    }
    if (getDownCell() != null) {
      count++;
    }
    if (getLeftCell() != null) {
      count++;
    }
    if (getRightCell() != null) {
      count++;
    }
    return count;
  }

  public int[] getCellPos() {
    return new int[] {x, y};
  }

  public String getCurrCellStatus() {
    if (this.isTunnel) {
      if (getLeftCell() != null && getRightCell() != null) {
        return "is tunnel horizontal";
      }
      if (getLeftCell() != null && getUpCell() != null) {
        return "is tunnel 1";
      }
      if (getLeftCell() != null && getDownCell() != null) {
        return "is tunnel 2";
      }
      if (getUpCell() != null && getDownCell() != null) {
        return "is tunnel vertical";
      }
      if (getDownCell() != null && getRightCell() != null) {
        return "is tunnel 3";
      }
      if (getUpCell() != null && getRightCell() != null) {
        return "is tunnel 4";
      }
    } else if (this.isRoom){
      int roomNum = geRoomDoors();
      if (roomNum == 1) {
        if (getUpCell() != null) {
          return "is room 1 up";
        }
        if (getDownCell() != null) {
          return "is room 1 down";
        }
        if (getLeftCell() != null) {
          return "is room 1 left";
        }
        if (getRightCell() != null) {
          return "is room 1 right";
        }
      } else if (roomNum == 3) {
        if (getUpCell() != null && getLeftCell() != null && getDownCell() != null) {
          return "is room 3 1";
        }
        if (getUpCell() != null && getLeftCell() != null && getRightCell() != null) {
          return "is room 3 2";
        }
        if (getUpCell() != null && getRightCell() != null && getDownCell() != null) {
          return "is room 3 3";
        }
        if (getDownCell() != null && getLeftCell() != null && getRightCell() != null) {
          return "is room 3 4";
        }
      } else {
        return "is room 4";
      }
    }
    return "";
  }
}
