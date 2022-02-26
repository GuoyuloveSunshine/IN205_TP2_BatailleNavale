package ensta.model.ship;

import ensta.util.Orientation;
import ensta.util.ShipType;

public class Destroyer extends AbstractShip{
    public Destroyer(Orientation ori){
        super(ShipType.D.getLabel(), ShipType.D.getFullName(), 
            ShipType.D.getSize(), ori);
    }
    public Destroyer(){
        super(ShipType.D.getLabel(), ShipType.D.getFullName(), 
            ShipType.D.getSize(), Orientation.EAST);
    }
}


