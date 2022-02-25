package ensta.ai;

import java.util.List;

import ensta.model.Board;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.Hit;
import ensta.model.Coords;


public class PlayerAI extends Player
{
  /* **
   * Attribut
   */
  private BattleShipsAI ai;

  /* **
   * Constructeur
   */
  public PlayerAI(Board ownBoard, Board opponentBoard, List<AbstractShip> ships)
  {
    super(ownBoard, opponentBoard, ships);
    ai = new BattleShipsAI(ownBoard, opponentBoard);
  }

  // TODO AIPlayer must not inherit "keyboard behavior" from player. Call ai
  // instead.
  public void putShips(AbstractShip[] ships) { 
    ai.putShips(ships); 
  }
  
  public Hit sendHit(Coords coords) {
      return ai.sendHit(coords);
  }
}
