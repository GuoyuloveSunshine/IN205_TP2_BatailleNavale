package ensta.model;

import ensta.model.ship.AbstractShip;
import ensta.model.ship.ShipState;
import ensta.util.ColorUtil;
import ensta.util.Orientation;

public class Board implements IBoard {

	private static final int DEFAULT_SIZE = 10;
	private int size;
	private int destroy;
	private String nom;
	private ShipState ships[][];
	private Boolean hits[][];
	
	public Board(String nom, int taille) {
		this.nom = nom;
		this.size = taille;
		this.ships = new ShipState[size][size];
		this.hits = new Boolean[size][size];
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				ships[j][i] = new ShipState(null);
			}
		}
		
	}

	public Board(String nom) {
		this(nom,DEFAULT_SIZE);
	}

	public ShipState[][] getShips() {
		return this.ships;
	}
	
	public void print() {
		System.out.println("Navires:                                   Frappes: ");
		char colonne = 'A';
		System.out.print("  ");
		for(int j = 0; j < size; j++) {
			System.out.print(" "+colonne++);
		}
		colonne = 'A';
		System.out.print("                       ");
		for(int j = 0; j < size; j++) {
			System.out.print(" "+colonne++);
		}
		System.out.print("\n");
		
		
		int ligne = 0;
		for(int i = 0; i < size; i++) {
			if(ligne < 10) System.out.print(ligne +"  ");
			else System.out.print(ligne +" ");
			for(int j = 0; j < size; j++) {
				if(ships[j][i].hasShip()) System.out.print(ships[j][i].toString());
				else System.out.print(". ");
			}
			System.out.print("                    ");
			
			if(ligne < 10) System.out.print(ligne++ +"  ");
			else System.out.print(ligne++ +" ");
			for(int j = 0; j < size; j++) {
				Boolean flag = this.hits[j][i];
				if(flag == null) System.out.print(". ");
				else if(flag == true) System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
				else if(flag == false) System.out.print("X ");
			}
			
			
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	
	public void printShip() {
		System.out.println("Navires: ");
		char colonne = 'A';
		System.out.print("  ");
		for(int j = 0; j < size; j++) {
			System.out.print(" "+colonne++);
		}
		System.out.print("\n");
		int ligne = 0;
		for(int i = 0; i < size; i++) {
			if(ligne < 10) System.out.print(ligne++ +"  ");
			else System.out.print(ligne++ +" ");
			for(int j = 0; j < size; j++) {
				if(ships[j][i].hasShip()) System.out.print(ships[j][i].toString());
				else System.out.print(". ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	
	public void printFrappe() {
		System.out.println("Frappes: ");
		char colonne = 'A';
		System.out.print("  ");
		for(int j = 0; j < size; j++) {
			System.out.print(" "+colonne++);
		}
		System.out.print("\n");
		int ligne = 0;
		for(int i = 0; i < size; i++) {
			if(ligne < 10) System.out.print(ligne++ +"  ");
			else System.out.print(ligne++ +" ");
			for(int j = 0; j < size; j++) {
				Boolean flag = this.hits[j][i];
				if(flag == null) System.out.print(". ");
				else if(flag == true) System.out.print(ColorUtil.colorize("X ", ColorUtil.Color.RED));
				else if(flag == false) System.out.print("X ");
			}
			System.out.print("\n");
		}
		System.out.print("\n");
	}
	
	public int getSize(){
		return this.size;
	}
	
	public String getName(){
		return this.nom;
	}
	
	public int getDestroy() {
		return this.destroy;
	}
	public boolean canPutShip(AbstractShip ship, Coords coords) {
		Orientation o = ship.getOrientation();
		int dx = 0, dy = 0;
		if (o == Orientation.EAST) {
			if (coords.getX() + ship.getLength() >= this.size) {
				return false;
			}
			dx = 1;
		} else if (o == Orientation.SOUTH) {
			if (coords.getY() + ship.getLength() >= this.size) {
				return false;
			}
			dy = 1;
		} else if (o == Orientation.NORTH) {
			if (coords.getY() + 1 - ship.getLength() < 0) {
				return false;
			}
			dy = -1;
		} else if (o == Orientation.WEST) {
			if (coords.getX() + 1 - ship.getLength() < 0) {
				return false;
			}
			dx = -1;
		}

		Coords iCoords = new Coords(coords);

		for (int i = 0; i < ship.getLength(); ++i) {
			if (this.hasShip(iCoords)) {
				return false;
			}
			iCoords.setX(iCoords.getX() + dx);
			iCoords.setY(iCoords.getY() + dy);
		}

		return true;
	}

	public boolean putShip(AbstractShip ship, Coords coords){
		if(canPutShip(ship,coords)) {
			Orientation o = ship.getOrientation();
//			System.out.println("ori: "+o);
			int dx = 0, dy = 0;
			if(o == Orientation.EAST) dx = 1;
			else if (o == Orientation.SOUTH) dy = 1;
			else if (o == Orientation.NORTH) dy = -1;
			else if (o == Orientation.WEST) dx = -1;
			
			Coords iCoords = new Coords(coords);
			for (int i = 0; i < ship.getLength(); ++i) {
//				System.out.println("length: "+ ship.getLength());
//				System.out.println("dx: "+ dx+ ", dy: "+ dy);
				ships[iCoords.getX()][iCoords.getY()].putShip(ship);
				iCoords.setX(iCoords.getX() + dx);
				iCoords.setY(iCoords.getY() + dy);
			}
			return true;
		}
		else {
			System.out.println("On ne peut pas mettre le bateau: ( "+ship.getName() +" ,  " + ship.printOrientation() + "), dans la position: (" + coords.getX()+ ", "+ coords.getY()+").");
			return false;
		}
		
	}
	public boolean hasShip(Coords aCoords){
		return (ships[aCoords.getX()][aCoords.getY()].hasShip());
	}
	public void setHit(boolean hit, Coords coords){
		hits[coords.getX()][coords.getY()] = hit;
			
	}
	public Boolean getHit(Coords coords){
		return hits[coords.getX()][coords.getY()];
	}
	public Hit sendHit(Coords res){
		if(!hasShip(res)) {
			System.out.println("Miss");
			return Hit.MISS;
		}
		int x = res.getX();
		int y = res.getY();
		ships[x][y].addStrike();
		System.out.println("Bien hit");
		
		if(ships[x][y].isSunk()) {
			int length = ships[x][y].getShip().getLength();
			this.destroy++;
			System.out.println(ships[x][y].getShip().getLabel() + " coulé");
			return Hit.fromInt(length);
		}
		else return Hit.STRIKE;
	}
}
