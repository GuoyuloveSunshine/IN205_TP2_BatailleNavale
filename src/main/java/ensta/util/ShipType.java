package ensta.util;
public enum ShipType {
	D("Destroyer",'D',2),
	S("Submarine",'S',3),
	B("BattleShip",'B',4),
	C("Carrier",'C',5);


    private String fullName;
    private char label;
    private int size;
    private ShipType(String name, char label,int size){
        this.fullName = name;
        this.label = label;
        this.size = size;
    }
    public String getFullName(){
        return fullName;
    }
    public char getLabel(){
        return label;
    }
    public int getSize(){
        return size;
    }
}
