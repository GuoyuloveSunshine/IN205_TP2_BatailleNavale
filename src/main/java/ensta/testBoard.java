package ensta;

import ensta.model.*;
import ensta.model.ship.*;

public class testBoard {
	public static void main(String args[]) {
		//Exercices 1-5
//		exercice1();
//		exercice3();
//		exercice5();
		exercice6();
  }
	public static void exercice1() {
		Board ziqi = new Board("ziqi");
		Board dada = new Board("dada",5);
		ziqi.print();
		dada.print();
	}
	public static void exercice3() {
		Board ziqi = new Board("ziqi");
		AbstractShip b1 = new Battleship();
		AbstractShip b2 = new Destroyer();
		AbstractShip b3 = new Carrier();
		Coords p1 = new Coords(4,1);
		Coords p2 = new Coords(7,3);
		Coords p3 = new Coords(5,1);
		Coords p4 = new Coords(8,4);
		ziqi.putShip(b1, p1);
		ziqi.putShip(b2, p2);
		ziqi.putShip(b3, p3);
		ziqi.putShip(b3, p4);
		ziqi.print();
	}
	public static void exercice5() {
		Board ziqi = new Board("ziqi");
		AbstractShip b1 = new Battleship();
		AbstractShip b2 = new Destroyer();
		Coords p1 = new Coords(4,1);
		Coords p2 = new Coords(7,3);
		
		ziqi.putShip(b1, p1);
		ziqi.putShip(b2, p2);
		ziqi.setHit(true,p1);
		ziqi.setHit(false, p2);
		ziqi.print();
		
	}
	
	public static void exercice6() {
		Board ziqi = new Board("ziqi");
		AbstractShip b1 = new Battleship();
		AbstractShip b2 = new Destroyer();
		Coords p1 = new Coords(4,1);
		Coords p2 = new Coords(7,3);
		Coords p3 = new Coords(5,1);
		Coords p4 = new Coords(6,1);
		Coords p5 = new Coords(7,7);
		
		//L'etat initial
		ziqi.putShip(b1, p1);
		ziqi.putShip(b2, p2);
		ziqi.print();
		Hit temp;
		System.out.println("-------------------------------------------------------------------");
		// il y a un miss
		temp = ziqi.sendHit(p5);
		ziqi.setHit(temp != Hit.MISS, p5);
		temp = ziqi.sendHit(p1);
		ziqi.setHit(temp != Hit.MISS, p1);
		temp = ziqi.sendHit(p3);
		ziqi.setHit(temp != Hit.MISS, p3);
		ziqi.print();
		System.out.println("-------------------------------------------------------------------");
		// il y a un sunk
		temp = ziqi.sendHit(p4);
		ziqi.setHit(temp != Hit.MISS, p4);
		temp = ziqi.sendHit(p4);
		ziqi.setHit(temp != Hit.MISS, p4);
		
		ziqi.print();
		System.out.println("-------------------------------------------------------------------");
		//Apres un sunk
		temp = ziqi.sendHit(p4);
		ziqi.setHit(temp != Hit.MISS, p4);
		ziqi.print();
	}
}
