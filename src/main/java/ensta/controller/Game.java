package ensta.controller;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.AbstractShip;
import ensta.model.ship.BattleShip;
import ensta.model.ship.Carrier;
import ensta.model.ship.Destroyer;
import ensta.model.ship.Submarine;
import ensta.util.ColorUtil;
import ensta.view.InputHelper;
import ensta.view.InputHelper.CoordInput;
import ensta.view.InputHelper.ShipInput;;


public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.dat");

	/*
	 * *** Attributs
	 */
	private Player me;
	private PlayerAI opponent;

	/*
	 * *** Constructeurs
	 */
	public Game() {
	}

	public void init() {
		if (!loadSave()) {

			// TODO init boards
			Board myBoard = new Board("board1");
			Board opponentBoard = new Board("board2");

			// TODO init this.player1 & this.player2
			List<AbstractShip> myShips = createDefaultShips();
			List<AbstractShip> opponentShips = createDefaultShips();
			this.me = new Player(myBoard, opponentBoard, myShips);
			this.opponent = new PlayerAI(opponentBoard, myBoard, opponentShips);

			// TODO place player ships
			opponent.putShips(opponentShips.toArray(new AbstractShip[0]));
			me.putShips();
		}
	}

	/*
	 * *** Méthodes
	 */
	public void run() {
		// INIT
		this.init();

		Coords coords = new Coords();
		Board opponentBoard = opponent.getBoard();
		Board myBoard = me.getBoard();
		boolean strike;
		Hit hit;


		// LOOP
		do {
			// Player 1
			hit = me.sendHit(coords); 
			strike = (hit != Hit.MISS); 
			opponentBoard.setHit(strike, coords);
			if (strike) {
				opponentBoard.printCoups();
			}
			System.out.println(makeHitMessage(strike, coords, hit));
			if(updateScore()){ break; }



			// save();
				

			// Player 2
			hit = opponent.sendHit(coords); 
			strike = (hit != Hit.MISS); 
			myBoard.setHit(strike, coords);		
			if (strike) {
				myBoard.printAll();
			}
			System.out.println(makeHitMessage(strike, coords, hit));
			if(updateScore()){ break; }

				
			
			// save();
				
		} while (true);

		SAVE_FILE.delete();
		System.out.println(String.format("Joueur %d gagne ! \n", me.isLose() ? 2 : 1));
	}

	private void save() {
//		try {
//			// TODO bonus 2 : uncomment
//			// if (!SAVE_FILE.exists()) {
//			// SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			// }
//
//			// TODO bonus 2 : serialize players
//
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
	}

	private boolean loadSave() {
//		if (SAVE_FILE.exists()) {
//			try {
//				// TODO bonus 2 : deserialize players
//
//				return true;
//			} catch (IOException | ClassNotFoundException e) {
//				e.printStackTrace();
//			}
//		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { me, opponent }) {
			int destroyed = 0;
			for (AbstractShip ship : player.getShips()) {
				if (ship.isSunk()) {
					destroyed++;
				}
			}

			player.setDestroyedCount(destroyed);
			player.setLose(destroyed == player.getShips().length);
			if (player.isLose()) {
				return true;
			}
		}
		return false;
	}

	private String makeHitMessage(boolean incoming, Coords coords, Hit hit) {
		String msg;
		ColorUtil.Color color = ColorUtil.Color.RESET;
		switch (hit) {
		case MISS:
			msg = hit.toString();
			break;
		case STRIKE:
			msg = hit.toString();
			color = ColorUtil.Color.RED;
			break;
		default:
			msg = hit.toString() + " coulé";
			color = ColorUtil.Color.RED;
		}
		msg = String.format("%s Frappe en %c%d : %s \n", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY()), msg);
		return ColorUtil.colorize(msg, color);
	}



	private static List<AbstractShip> createDefaultShips() {
	return Arrays.asList(new AbstractShip[] { /*new Destroyer(), new Submarine(), new Submarine(), */new BattleShip(), new Carrier() });
	}
}
