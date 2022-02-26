package ensta.model.ship;

import ensta.util.Orientation;
import ensta.util.ShipType;

public class Battleship extends AbstractShip{
    public Battleship(Orientation ori){
        super(ShipType.B.getLabel(), ShipType.B.getFullName(), 
            ShipType.B.getSize(), ori);
    }
    public Battleship(){
        super(ShipType.B.getLabel(), ShipType.B.getFullName(), 
            ShipType.B.getSize(), Orientation.EAST);
    }
}
