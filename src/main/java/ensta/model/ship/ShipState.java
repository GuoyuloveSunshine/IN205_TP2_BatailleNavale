package ensta.model.ship;

import ensta.util.*;
public class ShipState {
	private AbstractShip ship;
	private boolean struck;
	public ShipState(AbstractShip aShip) {
		this.ship = aShip;
		this.struck = false;
	}
	public void putShip(AbstractShip aShip) {
		this.ship = aShip;
		this.struck = false;
	}
	public void addStrike() {
		this.ship.addStrike();
		struck = true;
	}
	public boolean isStruck() {
		return this.struck;
		
	}
	public boolean hasShip() {
		if (ship == null) return false;
		else if(this.isSunk()) return false;
		else return true;
	}
	public String toString() {
		if(isStruck()) {
			return ColorUtil.colorize(ship.getLabel()+" ", ColorUtil.Color.RED);
		}
		else {
			return ship.getLabel()+" ";
		}
		 
	}
	public boolean isSunk() {
		return ship.isSunk();
	}
	public AbstractShip getShip() {
		return this.ship;
	}
	
}
