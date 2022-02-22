package ensta.model;

import java.util.Random;

import ensta.ai.BattleShipsAI;
import ensta.model.ship.*;

public class TestGame{
    public static void main(String args[]) {
        Board board = new Board("testBoard");
        AbstractShip[] ships = new AbstractShip[4];
        Carrier carrier = new Carrier();
        ships[0] = carrier;
        Submarine submarine = new Submarine();
        ships[1] = submarine;
        Destroyer destroyer = new Destroyer();
        ships[2] = destroyer;
        BattleShip battleShip = new BattleShip();
        ships[3] = battleShip;

        BattleShipsAI ai = new BattleShipsAI(board, board);
        int compteurDestroyed = 0;

        Coords coords = new Coords(0,0);
        Hit hit;
        Random rd = new Random();

        while(compteurDestroyed < 4) {
            coords.setX(rd.nextInt(board.getSize()));
			coords.setY(rd.nextInt(board.getSize()));
            hit = ai.sendHit(coords);

            if(hit == Hit.STRIKE){
                compteurDestroyed++;
            }

            board.print();

            // attente pour plus de lisibilité 
            
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}