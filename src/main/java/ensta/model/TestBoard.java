package ensta.model;

import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.Orientation;


public class TestBoard {
    public static void main(String args[]) {
        Board board1 = new Board("board1");
        Board board2 = new Board("board2", 5);
        Board board3 = new Board("board3", 30);
        board1.print();
        board2.print();
        board3.print();

        Coords coordsShipOut = new Coords(9, 1);
        Coords coordsOut = new Coords(50, -10);
        Coords coordsOk1 = new Coords(5, 5);
        Coords coordsOk2 = new Coords(4, 4);
        Coords coordsOk3 = new Coords(0, 0);
        Carrier carrier = new Carrier(Orientation.NORTH);
        BattleShip battleShip = new BattleShip(Orientation.EAST);
        Destroyer destroyer = new Destroyer(Orientation.WEST);
        Submarine submarine = new Submarine(Orientation.SOUTH);
        board1.putShip(carrier, coordsShipOut);
        board1.putShip(battleShip, coordsOut);
        board1.putShip(destroyer, coordsOk1);
        board1.putShip(submarine, coordsOk2);
        board1.putShip(submarine, coordsOk3);

        Hit hit1 = board1.sendHit(5, 5);
        Hit hit2 = board1.sendHit(5, 6);
    }
}
