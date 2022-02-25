package ensta.model;

import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.Orientation;


public class TestBoard {
    public static void main(String args[]) {
        Board board1 = new Board("board1");
        
        board1.printAll();

        Coords coords1 = new Coords(1,1);
        Coords coords2 = new Coords(2,2);
        Coords coords3 = new Coords(3,3);

       
       
        Carrier carrier = new Carrier(Orientation.NORTH);
        BattleShip battleShip = new BattleShip(Orientation.EAST);
        Destroyer destroyer = new Destroyer(Orientation.WEST);
        Submarine submarine = new Submarine(Orientation.SOUTH);


        board1.putShip(destroyer, coords1);
        
        System.out.println(submarine.isSunk());
        

        board1.printAll();
        

        //Hit hit1 = board1.sendHit(9, 9);
        //Hit hit2 = board1.sendHit(8, 9);
    }
}
