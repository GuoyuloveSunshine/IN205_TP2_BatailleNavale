package ensta.model.ship;

import ensta.util.Orientation;
import ensta.util.ShipType;

public class Submarine extends AbstractShip{
    public Submarine(Orientation ori){
        super(ShipType.S.getLabel(), ShipType.S.getFullName(), 
            ShipType.S.getSize(), ori);
    }
    public Submarine(){
        super(ShipType.S.getLabel(), ShipType.S.getFullName(), 
            ShipType.S.getSize(), Orientation.EAST);
    }
}
