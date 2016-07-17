/*
* You are a given a unimodal array of n distinct elements, 
* meaning that its entries are in increasing order up until its maximum element, 
* after which its elements are in decreasing order. 
* Give an algorithm to compute the maximum element that runs in O(log n) time.
*/

/* 
 * Design: piggybacking binary search on a sorted array; Instead of comparing the mid
 * value with the to-be-searched value, this algorithm compares arr[mid] and arr[mid + 1]
 * to determine which half should be checked next.
 * 
 * Run time analysis: T(n) = T(n/2) + c -> T(n) ~ O(log n)
 */
public class UnimodalArray
{
	private static int findMaxRecursive(int[] arr, int lo, int hi)
	{
		if(hi <= lo)
		{
			return arr[lo];
		}
		int mid = lo + (hi - lo) / 2;
		
		if(arr[mid] < arr[mid + 1])
		{
			// only needs to check the right half array arr[mid + 1, ......, n]
			 return findMaxRecursive(arr, mid + 1, hi);
		}
		else
		{
			// only needs to check the left half array arr[0, ........, mid]
			return findMaxRecursive(arr, 0, mid);
		}
	}
	
	public static int findMax(int[] arr)
	{
		return findMaxRecursive(arr, 0, arr.length - 1);
	}
	
	public static void main(String[] args)
	{
		int[] arr0 = {1,2,5,4,3,2,1};
		System.out.println(findMax(arr0));
		int[] arr1 = {1,2,3,4,6};
		System.out.println(findMax(arr1));
		int[] arr2 = {9,5,3,1};
		System.out.println(findMax(arr2));
	}
}