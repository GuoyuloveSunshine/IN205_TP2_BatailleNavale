package ensta.model.ship;

import ensta.util.Orientation;
import ensta.util.ShipType;

public class Carrier extends AbstractShip{
    public Carrier(Orientation ori){
        super(ShipType.C.getLabel(), ShipType.C.getFullName(), 
            ShipType.C.getSize(), ori);
    }
    public Carrier(){
        super(ShipType.C.getLabel(), ShipType.C.getFullName(), 
            ShipType.C.getSize(), Orientation.EAST);
    }
}

