package ensta.model;

import ensta.model.ship.AbstractShip;

public interface IBoard { 

    /**
     * Get the size of the grids contained in the Board
     * @return the size of the grids contained in the Board
     */
    public int getSize();

    /**
    * Put the given ship at the given position
    * @param ship The ship to place on the board
    * @param coords
     * @return true if the ship is put on the board
    */
    public boolean putShip(AbstractShip ship, Coords coords);

    /**
     * Get if a ship is placed at the given position
     * @param coords
     * @return true if a ship is located at the given position
     */
    public boolean hasShip(Coords coords);

    /**
     * Set the state of the hit at a given position
     * @param hit true if the hit must be set to successful
     * @param coords
     */
    public void setHit(boolean hit, Coords coords);

    /**
     * Get the state of a hit at the given position
     * @param coords
     * @return true if the hit is successful
     */
    public Boolean getHit(Coords coords);

	public Hit sendHit(Coords res);

	public boolean canPutShip(AbstractShip ship, Coords coords);

	public void printShip();
	public void printFrappe();
	public void print();

	public String getName();
}
