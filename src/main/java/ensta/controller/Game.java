package ensta.controller;

import java.io.*;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import ensta.ai.PlayerAI;
import ensta.model.Board;
import ensta.model.Coords;
import ensta.model.Hit;
import ensta.model.Player;
import ensta.model.ship.*;
import ensta.util.ColorUtil;

public class Game {

	/*
	 * *** Constante
	 */
	public static final File SAVE_FILE = new File("savegame.txt");

	/*
	 * *** Attributs
	 */
	private Player player1;
	private Player player2;
	private Scanner sin;

	/*
	 * *** Constructeurs
	 */
	public Game() {}

	public Game init() {
		if (!loadSave()) {
			System.out.println("Multijoueur? true or false? ");
			sin = new Scanner(System.in);
			boolean multiplayer = sin.nextBoolean();
			System.out.println("Enter ton nom: ");
			sin = new Scanner(System.in);
			String player1 = sin.nextLine();
			String player2 = "AI";
			
			if(multiplayer) {
				System.out.print("Enter le nom du opponent: ");
				sin = new Scanner(System.in);
				player2 = sin.nextLine();
			}
			
		// init boards
			Board p1 = new Board(player1);
			Board p2 = new Board(player2);
		// init this.player1 & this.player2
			List<AbstractShip> shipList1 = createDefaultShips();
			List<AbstractShip> shipList2 = createDefaultShips();
			this.player1 = new Player(p1,p2,shipList1);
			if(multiplayer) this.player2 = new Player(p2,p1,shipList2);
			else this.player2 = new PlayerAI(p2,p1,shipList2);
		// place player ships
			this.player1.putShips();
			sleep(1000);
			System.out.println("\n\n\n");
			System.out.println(p2.getName());
			this.player2.putShips();
			
		}
		return this;
	}

	/*
	 * *** Méthodes
	 */
	
	private static void sleep(int ms) {
		try{ 
			 Thread.sleep(ms); 
		 }
		catch (InterruptedException e){ 
			 e.printStackTrace(); 
		 } 
	}

	public void run() {
		Board b1 = player1.getBoard();
		Board b2 = player2.getBoard();
		Coords aCoords = new Coords(2,3);
		Hit hit;

		// main loop
		System.out.println("Jeux commence!!!");
		System.out.println(b1.getName());
		b1.print();
		boolean done = false;
		do {
			System.out.println(b2.getName());
			b2.print();
			System.out.println("Turn of: "+b1.getName());
			hit = this.player1.sendHit(aCoords); // player1 send a hit
			
			boolean strike = hit != Hit.MISS; // set this hit on his board (b1)
			b1.setHit(strike,aCoords);
			done = updateScore();
			System.out.println(b1.getName());
			b1.print();
			System.out.println(makeHitMessage(false /* outgoing hit */, aCoords, hit));

			save();

			if (!done && !strike) {
				do {
					System.out.println("Turn of: "+b2.getName());
					hit = this.player2.sendHit(aCoords); // player2 send a hit.
					strike = hit != Hit.MISS;
					if (strike) {
						System.out.println(b1.getName());
						b1.print();
					}
					System.out.println(makeHitMessage(true /* incoming hit */, aCoords, hit));
					done = updateScore();

					if (!done) {
						save();
					}
				} while (strike && !done);
			}

		} while (!done);

		SAVE_FILE.delete();
		System.out.println(String.format("joueur %d gagne", player1.isLose() ? 2 : 1));
		sin.close();
	}

	private void save() {
		try {
			// bonus 2 : uncomment
//			if (!SAVE_FILE.exists()) SAVE_FILE.getAbsoluteFile().getParentFile().mkdirs();
//			if (SAVE_FILE.exists()) SAVE_FILE.delete();

			//bonus 2 : serialize players
			FileOutputStream file = new FileOutputStream(SAVE_FILE.getAbsolutePath());
	        ObjectOutputStream out = new ObjectOutputStream(file);
            out.writeObject(this);
            out.close();
            file.close();
            System.out.println("Object has been serialized");
	         
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private boolean loadSave() {
		if (SAVE_FILE.exists()) {
			try {
				//bonus 2 : deserialize players
				FileInputStream file = new FileInputStream(SAVE_FILE.getAbsolutePath());
	            ObjectInputStream in = new ObjectInputStream(file);
	            Game game = (Game)in.readObject();
	            this.player1 = game.player1;
	            this.player2 = game.player2;
	            in.close();
	            file.close();
				return true;
			} catch (IOException | ClassNotFoundException e) {
				e.printStackTrace();
			}
		}
		return false;
	}

	private boolean updateScore() {
		for (Player player : new Player[] { player1, player2 }) {
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
		msg = String.format("%s Frappe en %c%d : %s", incoming ? "<=" : "=>", ((char) ('A' + coords.getX())),
				(coords.getY()), msg);
		return ColorUtil.colorize(msg, color);
	}

	private static List<AbstractShip> createDefaultShips() {
//		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine()});
		return Arrays.asList(new AbstractShip[] { new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier() });
	}
}