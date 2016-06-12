# FieldAndStones
Consider a square field of size n x n where some positions are occupied by stones. See the [example](Example) below. Here, the occupied positions are (1, 2), (2, 6), (4, 5), and (7, 3). The task is to find the position (i.e. the coordinates of the top left corner) and the size of the largest square that can be drawn on that field containing no stones. In the example, the solution would be a square whose top left corner is at (2,0) and whose size is 5.

##Example 
<img name="ex" src="https://github.com/nhays89/FieldAndStones/blob/master/FieldAndStones/img/field_stones_ex1.png"/>

##Approaches to Solving Field and Stones
* [Hybrid](Hybrid)
* [Brute Force](Brute Force)
* [Dynamic Programming](Dynamic Programming)

##<a href="https://www.google.com/search?q=Brute+Force&oq=Brute+Force&aqs=chrome..69i57j0l5.1598j0j7&sourceid=chrome&ie=UTF-8#q=Brute+Force+definition+computer+science">Brute Force</a>


<ul><li>Iterates over the entire field to check the largest size for each location and saving the size and coordinates.</li></ul>
	
```
	private static void bruteForce(int[][] field) {
		int max = 0, bestX = 0, bestY = 0;

		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[0].length; j++) {
				int size = 1;
				while (determineTarget(field, new int[] { j, i }, size)) {
					size++;
				}
				// bring size down 1
				if (size - 1 > max) {
					max = size - 1;
					bestX = j;
					bestY = i;
				}
			}
		}
	}
```

##[Hybrid] ("https://en.wikipedia.org/wiki/Hybrid_algorithm">Hybrid)

This approach starts by selecting a coordinate from the top left corner of the field (0,0). The coordinate will serve as a reference point to deteremine the maximum size square possible at this partiuclar location. Next, calculate the maximum size square that could poosibly be achieved at this coordinate by subtracting the difference between its column index (x) from the index at the end of the row. Then, a clone of this coordinate is generated to initiate an iteration (maxSize times) over the columns adjacent to this coordinate starting from right to left. The iteration will continue looping over the cells until the end of the row or the current cell has a stone. If its the end of the row, the loop will cointinue on the next row down; however, in the case of a stone, 1 of 2 options occur: 1) if the difference between the stone's x coordinate and the reference point's x coordinate is greater than the difference between the stone's y coordinate and the reference point's y coordinate then we break from this current loop, adjust our maximum loop size to the difference between the stones x and the reference point's x, and continue looping on the next row down, or 2) if the difference between the stone's x and the reference point's x is less than or equal to the difference between the stone's y and the reference point's y then we assign the reference point's max size square equal to the difference between the stone's y and the reference point's y, and then reassign the reference point to the next adjacent cell on that row. The process will continue until the reference point has visited all columns and rows of the grid. 

####Algorithm  
largestSquare := fieldWidth x fieldHeight.  
For each row to fiedHeight  
&nbsp;&nbsp;&nbsp;&nbsp;For each col to fieldWidth  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;maxSize := max(maxWidth, maxHeight).  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;For each  m to maxSize  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;For each n to maxSize  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If coord at m,n = stone then  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;If n - col > m - row then  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;maxSize := n - col.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;else  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;largestSquare r,c := m - r.  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End if  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End if  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End for  
&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;End for  
&nbsp;&nbsp;&nbsp;&nbsp;End for  
End for  

```
	public static int[][] analyzeField(int[][] theField) {
		int fieldSize = theField.length;
		int[][] largestSquare = new int[fieldSize][fieldSize];
		for (int r = 0; r < theField.length; r++) {
			for (int c = 0; c < theField[r].length; c++) {
				int maxWidth = fieldSize - c;
				int maxHeight = fieldSize - r;
				int m = 0, n = 0;
				int maxSize = maxWidth < maxHeight ? maxWidth : maxHeight;
				outer: for (m = r; m < r + maxSize; m++) {
					for (n = c; n < c + maxSize; n++) {
						if (theField[m][n] == 1) {
							if (n - c > m - r) {
								maxSize = n - c;
							} else {
								largestSquare[r][c] = m - r;
								break outer;
							}
						}
					}
				}
				if (m - r == maxSize) {
					largestSquare[r][c] = maxSize;
				}
			}
		}
		return largestSquare;
	}
```



