import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;

public class challenge {
	private static int fieldSize;

	/**
	 * Parses the input from the cmd line to create a boolean matrix
	 * representing the field and its stones at coordinates (x,y). Format: 1st
	 * arg: field size (the length of the field) 2nd arg: number of stones in
	 * the field. 3rd arg: coordinates of each stone on the field XY. sample
	 * call from cmd line: java challenge 8 4 1 2 2 6 4 5 7 3
	 * 
	 */
	public static void main(String[] args) {

		fieldSize = Integer.parseInt(args[0]);
		int numOfStones = Integer.parseInt(args[1]);
		int[][] field = new int[fieldSize][fieldSize];
		for (int i = 2; i <= numOfStones * 2; i += 2) {
			int x = Integer.parseInt(args[i]);
			int y = Integer.parseInt(args[i + 1]);
			field[x][y] = 1;
		}
		int[][] maxSquare = new int[fieldSize][fieldSize];
		findLargestSquare(field);

	}

	public static void parseInput(StringBuilder sb) {
		int fieldLength = sb.charAt(0);
		System.out.println(fieldLength);
		// for(int i = 0; i < )

	}

	/**
	 * Given a target coordinate, determines the largest possible square not
	 * containing any stones within its boundary that can be mapped on the board
	 * using the target coordinate.
	 * 
	 * 
	 * @param theField
	 *            the boolean matrix representing the stones on the field.
	 */
	public static void findTargetSquare(int[][] theField) {

	}

	/**
	 * Given a boolean matrix representing the field, this method will print the
	 * position (upper left corner) of the coordinate that can generate the
	 * largest possible square not containing any stones within its boundary,
	 * and the square size.
	 * 
	 * 
	 * @param theField
	 *            the boolean matrix representing the stones on the field.
	 */
	public static void findLargestSquare(int[][] theField) {
		int[][] largestSquare = new int[fieldSize][fieldSize];
		for (int y = 0; y < theField.length; y++) {
			for (int x = 0; x < theField[y].length; x++) {
				int maxWidth = fieldSize - x;
				int maxHeight = fieldSize - y;
				int maxSize = maxWidth < maxHeight ? maxWidth : maxHeight;
				outer:
				for (int m = y; m < y + maxSize; m++) {
					int n;
					for (n = x; n < x + maxSize; n++) {
						if (theField[m][n] == 1) {
							if (n - x > m - y) {
								maxSize = n - x;
							} else if (n - x < m - y) {
								largestSquare[m][n] = m - y;
								break outer;
							} else {
								largestSquare[m][n] = m - y;
								break outer;
							}
						}
					}
					if (n == n + maxSize ) {
						largestSquare[m][n] = maxWidth;
					}
				}
			}

		}

	}
}
