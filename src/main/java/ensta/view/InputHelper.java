package ensta.view;

import java.util.Arrays;
import java.util.Scanner;

public final class InputHelper {

	private static Scanner scanner = new Scanner(System.in);

	/*
	 * ** Constructeur
	 */
	private InputHelper() {
	}

	/*
	 * ** Classe ShipInput, interne à InputHelper
	 */
	public static class ShipInput {
		public String orientation;
		public int x;
		public int y;
	}

	/*
	 * ** Classe CoordInput, interne à InputHelper
	 */
	public static class CoordInput {
		public int x;
		public int y;
	}

	/*
	 * ** Méthodes de la classe InputHelper
	 */
	public static ShipInput readShipInput() {
		ShipInput res = new ShipInput();
		String[] validOrientations = { "north", "south", "east", "west" }; // North, South, East, West
		boolean done = false;
		do {
			try {
				String[] in = scanner.nextLine().toLowerCase().split(" ");
				if (in.length == 2) {
					String coord = in[0];
					if (Arrays.asList(validOrientations).contains(in[1])) {
						res.orientation = in[1];
						res.x = coord.charAt(0) - 'a';
						res.y = Integer.parseInt(coord.substring(1, coord.length()));
						done = true;
					}
				}
			} catch (Exception e) {}
			if (!done) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0 north'");
			}
		} while (!done && scanner.hasNextLine());

		return res;
	}

	public static CoordInput readCoordInput() {
		CoordInput res = new CoordInput();
		boolean done = false;
		do {
			try {
				String coord = scanner.nextLine().toLowerCase();
				res.x = coord.charAt(0) - 'a';
				res.y = Integer.parseInt(coord.substring(1, coord.length()));
				done = true;
			} catch (Exception e) {
				System.err.println("Format incorrect! Entrez la position sous forme 'A0'");
			}
		} while (!done && scanner.hasNextLine());

		return res;
	}
}
