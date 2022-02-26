package ensta.model;

import java.util.Random;

public class Coords {
    private int x;
    private int y;
    public Coords(int i, int j){
        this.x = i;
        this.y = j;
    }
	public Coords(Coords coords) {
		this.x = coords.getX();
		this.y = coords.getY();
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public void setX(int i) {
		this.x = i;
	}
	public void setY(int j) {
		this.y = j;
	}
	public void setCoords(Coords res) {
		this.x = res.getX();
		this.y = res.getY();
	}
	public boolean isInBoard(int size) {
		if (x<=size && y<=size) return true;
		return false;
	}
	public String toString() {
		return "("+this.x+", "+this.y+")";
	}
	public static Coords randomCoords(int size) {
		// TODO Auto-generated method stub
		int x = new Random().nextInt(size - 1);
		int y = new Random().nextInt(size - 1);
		return new Coords(x, y);
	}
}
