package ensta.model.ship;

import ensta.util.Orientation;
//import ensta.util.Oripachentation;

public abstract class AbstractShip {
    private char label;
    private String nom;
    private int taille;
    private Orientation orientation;
    private int strikeCount;
    public AbstractShip(char Label, String Nom, int Taille, Orientation Ori){
        this.label = Label;
        this.nom = Nom;
        this.taille = Taille;
        this.orientation = Ori;
    }
    public void setOrientation(Orientation ori) {
    	orientation = ori;
    }
	public Orientation getOrientation() {
		return orientation;
	}
	public int getLength() {
		return taille;
	}
	public Object getName() {
		return nom;
	}
	public char getLabel() {
		return label;
	}
	public int getStrikeCount() {
		return strikeCount;
	}
	public boolean isSunk() {
		return (taille == strikeCount);
	}
	public void addStrike() {
		if(this.strikeCount < this.taille) this.strikeCount++;
		else this.strikeCount = this.taille;
	}
	public String printOrientation () {
		if(this.orientation == Orientation.EAST) return "est";
		else if(this.orientation == Orientation.WEST) return "ouest";
		else if(this.orientation == Orientation.NORTH) return "north";
		else return "sud";
	}
}
