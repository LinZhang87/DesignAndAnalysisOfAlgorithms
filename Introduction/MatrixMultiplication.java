/*
* Strassen's Algorithm
* 
* The problem of matrix mulitiplication
* Input:  2 n * n matrices X and Y; Output: X * Y (n * n matrix)
* 
* Solution 1: straightforward one;  Running time: Theta of n^3
* 
* Solution 2: Strassen's Algorithm
* 
* Applying Divide and Conquer, we get the following:
* step1. recursively compute the 8 necessary products
* step2. do the necessary additions (theta of n^2)
* However, the run time is still theta of n^3(follows from the master method)
* 
* Strassen's Algorithm to the rescue
* step1. recursively compute only 7 products instead of 8
* step2. do the necessary additions + subtractions(theta of n^2)
* 
* 
* Note: For the purpose of implementation simplicity, assume n is even.
* However, Strassen's algorithm works on both square and non-square matrices, 
* regardless if n is odd or even. For a generic implementation, refer to
* https://en.wikipedia.org/wiki/Strassen_algorithm
* 
* Run time is better than cubic time.
* 
* Q: what about space complexity? 
* 
*/

public class MatrixMultiplication
{	
	private static void printMatrix(int[][] x, int n)
	{
		int i, j;
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				System.out.println(x[i][j]);
			}
		}
	}
	private static int[][] matrixAdd(int[][] x, int[][] y, int n)
	{
		int i, j;
		int[][] r = new int[n][n];
		
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				r[i][j] = x[i][j] + y[i][j];
			}
		}
		return r;
	}
	
	private static int[][] matrixSubtract(int[][] x, int[][] y, int n)
	{
		int i, j;
		int[][] r = new int[n][n];
		
		for(i = 0; i < n; i++)
		{
			for(j = 0; j < n; j++)
			{
				r[i][j] = x[i][j] - y[i][j];
			}
		}
		return r;
	}
	
	private static void copyPartialMatrix(int[][] original, int[][] copy, 
			int row_lo, int row_hi, int col_lo, int col_hi, int copy_start_row, int copy_start_col)
	{
		int i, j, k = copy_start_row, m = copy_start_col;
		for(i = row_lo; i <= row_hi; i++)
		{
			for(j = col_lo; j <= col_hi; j++)
			{
				copy[k][m] = original[i][j];
				m++;
			}
			k++;
			m = copy_start_col;
		}
	}
	
	private static void mergeMatrix(int[][] r, int[][] upper_left, int[][] upper_right, 
			int[][] lower_left, int[][] lower_right, int n)
	{
		copyPartialMatrix(upper_left, r, 0, n/2 - 1, 0, n/2 - 1, 0, 0);
		copyPartialMatrix(upper_right, r, 0, n/2 - 1, 0, n/2 - 1, 0, n/2);
		copyPartialMatrix(lower_left, r, 0, n/2 - 1, 0, n/2 - 1, n/2, 0);
		copyPartialMatrix(lower_right, r, 0, n/2 - 1, 0, n/2 - 1, n/2, n/2);
	}
	
	public static int[][] strassenMatrixMul(int[][] x, int[][] y, int n)
	{
		//ignore error checking here, assuming both x and y are n * n matrices
		//Base case where both x and y only have 1 entry
		if(n <= 1)
		{
			int[][] base_result = {{x[0][0] * y[0][0]}};
			return base_result;
		}
		/*divide x to {{A,B},{C,D}}, y to {{E,F},{G,H}}
		*Recursively calculate the following.
		*p1 = A(F-H), p2 = (A+B)H, p3=(C+D)E, p4 = D(G-E)
		*p5 = (A+D)(E+H), p6 = (B-D)(G+H), p7 = (A-C)(E+F)
		*/
		int[][] A = new int[n/2][n/2];
		copyPartialMatrix(x, A, 0, n/2 - 1, 0, n/2 - 1, 0, 0);
		
		int[][] B = new int[n/2][n/2];
		copyPartialMatrix(x, B, 0, n/2 - 1, n/2, n - 1, 0, 0);
		
		int[][] C = new int[n/2][n/2];
		copyPartialMatrix(x, C, n/2, n - 1, 0, n/2 - 1, 0, 0);
		
		int[][] D = new int[n/2][n/2];
		copyPartialMatrix(x, D, n/2, n - 1, n/2, n - 1, 0, 0);
		
		int[][] E = new int[n/2][n/2];
		copyPartialMatrix(y, E, 0, n/2 - 1, 0, n/2 - 1, 0, 0);
		
		int[][] F = new int[n/2][n/2];
		copyPartialMatrix(y, F, 0, n/2 - 1, n/2, n - 1, 0, 0);
		
		int[][] G = new int[n/2][n/2];
		copyPartialMatrix(y, G, n/2, n - 1, 0, n/2 - 1, 0, 0);
		
		int[][] H = new int[n/2][n/2];
		copyPartialMatrix(y, H, n/2, n - 1, n/2, n - 1, 0, 0);
		
		int[][] p1 = new int[n/2][n/2];
		int[][] p2 = new int[n/2][n/2];
		int[][] p3 = new int[n/2][n/2];
		int[][] p4 = new int[n/2][n/2];
		int[][] p5 = new int[n/2][n/2];
		int[][] p6 = new int[n/2][n/2];
		int[][] p7 = new int[n/2][n/2];
		
		p1 = strassenMatrixMul(A, matrixSubtract(F,H,n/2), n/2);
		p2 = strassenMatrixMul(matrixAdd(A, B, n/2), H, n/2);
		p3 = strassenMatrixMul(matrixAdd(C, D, n/2), E, n/2);
		p4 = strassenMatrixMul(D, matrixSubtract(G,E,n/2), n/2);
		p5 = strassenMatrixMul(matrixAdd(A,D,n/2), matrixAdd(E,H,n/2), n/2);
		p6 = strassenMatrixMul(matrixSubtract(B,D,n/2), matrixAdd(G,H,n/2), n/2);
		p7 = strassenMatrixMul(matrixSubtract(A,C,n/2), matrixAdd(E,F,n/2), n/2);
				
		//Merge result
		int[][] result = new int[n][n];
		mergeMatrix(result, matrixAdd(matrixAdd(p4, p5, n/2), matrixSubtract(p6, p2, n/2), n/2), 
							matrixAdd(p1, p2, n/2),
							matrixAdd(p3, p4, n/2),
							matrixAdd(matrixSubtract(p1, p3, n/2), matrixSubtract(p5, p7, n/2), n/2),
							n);
	
		return result;
	}
	
	public static void main(String[] args)
	{
		//ToDo: Use JUnit Framework for these unit tests
		
		//test case 1: x = {{3}}, y = {{4}}
		int[][] x1 = {{3}};
		int[][] y1 = {{4}};
		int[][] r1 = strassenMatrixMul(x1, y1, 1);
		printMatrix(r1, 1);
		
		// test case 2: x = {{1,2}, {3,4}}, y = {{5,6},{7,8}} 
		 
		int[][] x2 = {{1,2}, {3,4}};
		int[][] y2 = {{5,6}, {7,8}};
		int[][] r2 = strassenMatrixMul(x2, y2, 2);
		printMatrix(r2, 2);
				
		//test case 3: x = {{1,2,3,4}, {4,5,6,7},{7,8,9,0},{1,2,3,4}}, 
		// y = {{1,2,3,4}, {4,5,6,7},{7,8,9,0},{1,2,3,4}}
		 
		int[][] x3 = {{1,2,3,4}, {4,5,6,7},{7,8,9,0},{1,2,3,4}};
		int[][] y3 = {{1,2,3,4}, {4,5,6,7},{7,8,9,0},{1,2,3,4}};
		int[][] r3 = strassenMatrixMul(x3, y3, 4);
		printMatrix(r3, 4);
	}
}