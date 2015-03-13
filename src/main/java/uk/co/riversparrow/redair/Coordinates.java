package uk.co.riversparrow.redair;

public class Coordinates {
	public int x;
	public int y;
	public int z;
	
	public Coordinates(int instanceX, int instanceY, int instanceZ) {
		x = instanceX;
		y = instanceY;
		z = instanceZ;
	}
	public Coordinates(String coordinatesString) throws IllegalArgumentException {
		String string = coordinatesString.replace("(", "");
		string = string.replace(")", "");
		String[] coords = string.split(",");
		try {
			x = Integer.parseInt(coords[0], 10);
			y = Integer.parseInt(coords[1], 10);
			z = Integer.parseInt(coords[2], 10);
		} catch(Exception ex) {
			throw new IllegalArgumentException(ex.getMessage());
		}
	}
	
	@Override
	public String toString() {
		return "(" + x + "," + y + "," + z + ")";
	}
}
