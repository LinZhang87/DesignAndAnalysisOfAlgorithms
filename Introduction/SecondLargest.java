/*
* You are given as input an unsorted array of n distinct numbers, 
* where n is a power of 2. Give an algorithm that identifies the second-largest number in the array, 
* and that uses at most n+(log2 n) −2 comparisons.
*/

/*
 * Merge sort uses O(n log n)comparisons, so we just can not use merge sort here
 * 
 */

/*
 * Space complexity: 
 * when there is only 1 element in the array, a new array of size 2 + log 2 n is 
 * created. As a result, there are 2 new arrays created when FindMaxTournament
 * is called on 2 elements array. Since we only carry information about all comparisons
 * an array won until this element loses a comparison, at any given time the memory
 * used is 2 * (2 + log 2 n)
 */

/*
 * Time complexity:  O(N) + O(log2(N)) ~ O(N)
 */


public class SecondLargest
{	
	private static int[] FindMaxTournament(int n, int lo, int hi, int[] arr)
	{
		//base case
		if(hi == lo)
		{
			int[] compared = new int[2 + (int) ( Math.log(n) / Math.log(2) ) ];
			compared[0] = 2;
			compared[1] = arr[lo];
			return compared;
		}
		
		int[] compared1;
		int[] compared2;
		
		int mid = lo + (hi - lo) / 2;
		compared1 = FindMaxTournament(n, lo, mid, arr);
		compared2 = FindMaxTournament(n, mid + 1, hi, arr);
		
		if(compared1[1] > compared2[1])
		{
			compared1[compared1[0]] = compared2[1];
			compared1[0] += 1;
			return compared1;
		}
		else
		{
			compared2[compared2[0]] = compared1[1];
			compared2[0] += 1;
			return compared2;
		}
	}
	
	public static int FindSecondMax(int n, int[] arr)
	{
		int[] compared;
		int[] compared2;
		compared = FindMaxTournament(n, 0, n-1, arr);
		compared2 = FindMaxTournament(compared.length, 2, compared[0] - 1, compared);
		return compared2[1];
	}
	public static void main(String[] args)
	{
		/*
		 * test case 0: arr0 = {1,2}
		 */
		int[] arr0 = {1,2};
		System.out.println(FindSecondMax(2, arr0));
		
		/*
		 * test case 1: arr1 = {1,2,3,8,5,6,7,4}
		 */
		int[] arr1 = {1,2,3,8,5,6,7,4};
		System.out.println(FindSecondMax(8, arr1));
		
		/*
		 * test case 2: arr2 = {1,3,4,7,8,2,6,5}
		 */
		int[] arr2 = {1,2,3,8,5,6,7,4};
		System.out.println(FindSecondMax(8, arr2));
	}
}