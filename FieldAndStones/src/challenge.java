/**
 * public repository: 'www.github.com/nhays89/challenge/
 * @author Nicholas A. Hays
 * @author Ethan Rowell - Brute Force method
 */

public class challenge {

	/**
	 * Check out 'www.github.com/nhays89/challenge/' readme for details.  
	 */
	public static void main(String[] args) {
		int fieldSize = Integer.parseInt(args[0]);
		int numOfStones = Integer.parseInt(args[1]);
		int[][] field = new int[fieldSize][fieldSize];
		for (int i = 2; i <= numOfStones * 2; i += 2) {
			int x = Integer.parseInt(args[i]);
			int y = Integer.parseInt(args[i + 1]);
			field[x][y] = 1;
		}
	/*	int[] sampleTarget = new int[2];
		sampleTarget[0] = 6;
		sampleTarget[1] = 6;
		determineTarget(field, sampleTarget, 3);*/
		int[][] fieldSquares = analyzeField(field);
		printLargestSquare(fieldSquares);
		bruteForce(field);
	}

	/**
	 * Given a target coordinate, square size, and 
	 * 
	 * @param theField
	 *            the boolean matrix representing the stones on the field.
	 */
	public static boolean determineTarget(int[][] theField, int[] target, int size) {
		int x = target[0];
		int y = target[1];
		if(x + size > theField.length || y + size > theField[0].length) {
			return false;
		}
		for(int m = x; m < x + size; m++) {
			for (int n = y; n < y + size; n++) {
				if(theField[m][n] == 1) {
					return false;
				}
			}
		}	
		return true;
	}

	/**
	 * Given a boolean matrix representing the field, for each coordinate on the field
	 * this method will find the largest possible square not containing any stones 
	 * within its boundary. 
	 * 
	 * @param theField
	 *            the boolean matrix representing the stones on the field.
	 * @return the array representing the largest possible square at each coordinate.
	 */
	public static int[][] analyzeField(int[][] theField) {
		int fieldSize = theField.length;
		int[][] largestSquare = new int[fieldSize][fieldSize];
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
		return largestSquare;
	}

	/**
	*  Iterates over the entire field to check the largest size for each location 
	*  and saving the size and coordinates.
	*
	* @param field the field of stones data.
	*/
	private static void bruteForce(int[][] field) {
        int max = 0, bestX = 0, bestY = 0;

        for (int i = 0; i < field.length; i++) {
            for (int j = 0; j < field[0].length; j++) {
                int size = 1;
                while(determineTarget(field, new int[] { j, i }, size)) {
                    size++;
                }
                //bring size down 1
                if (size - 1 > max) {
                    max = size - 1;
                    bestX = j;
                    bestY = i;
                }
            }
        }
        System.out.println("Brute Force Max: " + max + "\nx coord: " + bestX + "\ny coord: " + bestY );
    }
	
	/**
	 * Prints largest possible square size in the 2d array. 
	 * @param theSquares the 
	 */
	public static void printLargestSquare(int[][] theSquares) {
		int max = 0, maxX = 0, maxY = 0;
		for(int i = 0; i < theSquares.length; i++) {
			for(int j = 0; j < theSquares[i].length; j++) {
				if(theSquares[i][j] > max) {
					max = theSquares[i][j];
					maxX = j;
					maxY = i;
				}
			}
		}
		System.out.println("Max Square Size: " + max + "\n" + "x coord: " + maxX + "\n" + "y coord: " + maxY);
	}
}
