package ensta.model;

import java.io.Serializable;
import java.util.List;

import ensta.model.ship.AbstractShip;
import ensta.util.Orientation;
import ensta.view.InputHelper;

public class Player implements Serializable{
	/*
	 * ** Attributs
	 */
	private Board board;
	protected Board opponentBoard;
	private int destroyedCount;
	protected AbstractShip[] ships;
	private boolean lose;

	/*
	 * ** Constructeur
	 */
	public Player(Board board, Board opponentBoard, List<AbstractShip> ships) {
		this.setBoard(board);
		this.ships = ships.toArray(new AbstractShip[0]);
		this.opponentBoard = opponentBoard;
	}

	/*
	 * ** Méthodes
	 */

	/**
	 * Read keyboard input to get ships coordinates. Place ships on given
	 * coodrinates.
	 */
	public void putShips() {
		boolean done = false;
		int i = 0;

		do {
			AbstractShip ship = ships[i];
			String msg = String.format("placer %d : %s(%d)", i + 1, ship.getName(), ship.getLength());
			System.out.println(msg);
			InputHelper.ShipInput res = InputHelper.readShipInput();
			// set ship orientation
			Orientation ori = null;
			if(res.orientation.equals("north")) ori = Orientation.NORTH;
			else if (res.orientation.equals("south")) ori = Orientation.SOUTH;
			else if (res.orientation.equals("east")) ori = Orientation.EAST;
			else if (res.orientation.equals("west")) ori = Orientation.WEST;
			ship.setOrientation(ori);
			// put ship at given position
			Coords pTemp = new Coords(res.x,res.y);
			boolean success = false;
			System.out.println(board.getName());
			while(!success) {
				try{
					success = board.canPutShip(ship, pTemp);
//					success = true;
				}
				catch (Exception e){
					System.out.println("La valeur et en dehors du board ");
				}
				if(!success) {
					System.out.println("On ne peut pas le met dans le board, essayez un autre: ");
					res = InputHelper.readShipInput();
					// set ship orientation
					ori = null;
					if(res.orientation.equals("north")) ori = Orientation.NORTH;
					else if (res.orientation.equals("south")) ori = Orientation.SOUTH;
					else if (res.orientation.equals("east")) ori = Orientation.EAST;
					else if (res.orientation.equals("west")) ori = Orientation.WEST;
					ship.setOrientation(ori);
					// put ship at given position
					pTemp = new Coords(res.x,res.y);
				}
				else {
					board.putShip(ship, pTemp);
				}

			}
			// when ship placement successful
			++i;
			done = i == ships.length;
			// affichage
			board.printShip();
		} while (!done);
		System.out.println(board.getName());
		board.print();
	}

	public Hit sendHit(Coords coords) {
		boolean done = false;
		Hit hit = null;

		do {
			System.out.println("où frapper?");
			InputHelper.CoordInput hitInput = InputHelper.readCoordInput();
			int x = hitInput.x;
			int y = hitInput.y;
			done = 0 <= x && x < opponentBoard.getSize() && 0 <= y && y < opponentBoard.getSize();
			if (done){
				// call sendHit on this.opponentBoard
				Coords temp = new Coords(hitInput.x,hitInput.y);
				hit = this.opponentBoard.sendHit(temp);
				this.board.setHit(hit != Hit.MISS, temp);
				if (hit != Hit.MISS && hit != Hit.STRIKE){
					System.out.println(hit.toString() + " coulé");
				}
				// Game expects sendHit to return BOTH hit result & hit coords.
				// return hit is obvious. But how to return coords at the same time ?
				coords.setX(x);
				coords.setY(y);
			}
			else{
				System.out.println("Réessayez avec des valeurs correctes !");
			}			
		} while (!done);

		return hit;
	}

	public AbstractShip[] getShips() {
		return ships;
	}

	public void setShips(AbstractShip[] ships) {
		this.ships = ships;
	}

	public Board getBoard() {
		return board;
	}

	public void setBoard(Board board) {
		this.board = board;
	}

	public int getDestroyedCount() {
		return destroyedCount;
	}

	public void setDestroyedCount(int destroyedCount) {
		this.destroyedCount = destroyedCount;
	}

	public boolean isLose() {
		return lose;
	}

	public void setLose(boolean lose) {
		this.lose = lose;
	}
	
//	public void stock(String path) {
//		
//	}
}
