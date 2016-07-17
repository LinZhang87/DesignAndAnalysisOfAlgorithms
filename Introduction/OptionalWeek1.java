/*
* You are given a sorted (from smallest to largest) array A of n distinct integers 
* which can be positive, negative, or zero. You want to decide whether or not there 
* is an index i such that A[i] = i. Design the fastest algorithm that you can for solving this problem.
*/

/*
 * Most straightforward way: do a linear scan, which gives us O(n) running time. 
 * The goal here is to design an algorithm that can beat O(n), possibly O(log(n))
 */

public class OptionalWeek1
{
	private static int findIndexAndValueMatchRecursive(int[] arr, int lo, int hi)
	{
		if(lo > hi)
		{
			return -1;
		}
		
		int mid = lo + (hi - lo) / 2;
		
		if(arr[mid] == mid)
		{
			return mid;
		}
		else if(arr[mid] > mid)
		{
			// only needs to check the first half 
			return findIndexAndValueMatchRecursive(arr, lo, mid - 1);
		}
		else
		{
			// only needs to check the second half
			return findIndexAndValueMatchRecursive(arr, mid + 1, hi);
		}
	}
	public static int findIndexAndValueMatch(int[] arr)
	{
		// Handle special case to achieve O(1) time 
		if(arr[arr.length - 1] < 0)
		{
			return -1;
		}
			
		return findIndexAndValueMatchRecursive(arr, 0, arr.length - 1);
	}
	public static void main(String[] args)
	{
		/*
		 * test case 0: arr0 = {-3, -2, -1}
		 */
		int[] arr0 = {-3, -2, -1};
		System.out.println(findIndexAndValueMatch(arr0));
		
		/*
		 * test case 1: arr1 = {-4, -2, 0, 2, 4}
		 */
		int[] arr1 = {-4, -2, 0, 2, 4};
		System.out.println(findIndexAndValueMatch(arr1));
		
		/*
		 * test case 2: arr1 = {-4, -2, 0, 2, 5}
		 */
		int[] arr2 = {-4, -2, 0, 2, 5};
		System.out.println(findIndexAndValueMatch(arr2));
		
		/*
		 * test case 2: arr1 = {-4, -2, 0, 2, 5}
		 */
		int[] arr3 = {0, 3, 5, 7, 9};
		System.out.println(findIndexAndValueMatch(arr3));
	}
}