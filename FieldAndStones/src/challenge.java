/**
 * 
 * @author Nicholas A. Hays
 */

public class challenge {
	private static int fieldSize;
	private static int[][] largestSquare;

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
		System.out.println("arg 0 = " + args[0]);
		int numOfStones = Integer.parseInt(args[1]);
		System.out.println("arg 1 = " + args[1]);
		int[][] field = new int[fieldSize][fieldSize];
		for (int i = 2; i <= numOfStones * 2; i += 2) {
			int x = Integer.parseInt(args[i]);
			System.out.println("arg " + i + " = " + x);
			int y = Integer.parseInt(args[i + 1]);
			System.out.println("arg " + (i + 1) + " = " + y);
			field[x][y] = 1;
		}
		largestSquare = new int[fieldSize][fieldSize];
		findLargestSquare(field);
		for (int i = 0; i < largestSquare.length; i++) {
			for (int j = 0; j < largestSquare[i].length; j++) {
				System.out.print(largestSquare[i][j] + " ");
			}
			System.out.println();
		}

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
		for (int y = 0; y < theField.length; y++) {
			for (int x = 0; x < theField[y].length; x++) {
				int maxWidth = fieldSize - x;
				int maxHeight = fieldSize - y;
				int m = 0, n = 0;
				int maxSize = maxWidth < maxHeight ? maxWidth : maxHeight;
				outer: for (m = y; m < y + maxSize; m++) {
					for (n = x; n < x + maxSize; n++) {
						if (theField[m][n] == 1) {
							if (n - x > m - y) {
								maxSize = n - x;
							} else if (n - x < m - y) {
								largestSquare[y][x] = m - y;
								break outer;
							} else {
								largestSquare[y][x] = m - y;
								break outer;
							}
						}
					}
				}
				if (m - y == maxSize) {
					largestSquare[y][x] = maxSize;
				}
			}
		}
	}
}
