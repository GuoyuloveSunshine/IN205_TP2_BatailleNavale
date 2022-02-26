package ensta;

import ensta.model.*;
import ensta.model.ship.*;

import java.util.Random;

import ensta.ai.*;


public class testGame {
	public static void main(String args[]) {
		Board ziqi = new Board("ziqi");
		AbstractShip aShipList[] = {new Destroyer(), new Submarine(), new Submarine(), new Battleship(), new Carrier()};

		BattleShipsAI ai = new BattleShipsAI(ziqi,ziqi);
		ai.putShips(aShipList);
		ai.printEtat();
		Coords aCoord = new Coords(2,3);
		int times = 1;
		while(ziqi.getDestroy() != aShipList.length) {
			System.out.println(times++ +" fois -----------------------------------------------------------------");
			ai.sendHit(aCoord);
			ai.printEtat();
		}
	}
}
