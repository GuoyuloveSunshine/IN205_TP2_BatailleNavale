package ensta;

import java.util.ArrayList;
import java.util.List;

import ensta.model.*;
import ensta.model.ship.*;

public class testPlayer {
	public static void main(String args[]) {
		exercice4();
	}
	public static void exercice4() {
		Board ziqi = new Board("ziqi");
		Board dada = new Board("dada");
		List<AbstractShip> ships = new ArrayList<>();
		ships.add(new Destroyer());
		ships.add(new Submarine());
		ships.add(new Submarine());
		ships.add(new Battleship());
		ships.add(new Carrier());
		Player p1 = new Player(ziqi,dada, ships);
		p1.putShips();
	}

}
