/*
* Selection Problem
* Input: a list of numbers S, an integer k
* Output: The kth smallest element of S
*/

/*
 * Run time analysis:
 * After two split operations on average, the interested array will shrink to 
 * at most 3/4 of its original size. 
 * T(n) <= T(n * 3  / 4) + O(n), where it takes linear time to split at each level
 * By master method, this gives us O(n) running time
 * 
 * Quicksort uses the same split algorithm
 */

public class SelectionProblem
{
	private static int Select_Kth_Smallest(int[] arr, int k, int n)
	{
		if(k <= 0 || k > arr.length)
		{
			return Integer.MAX_VALUE;	//invalid k 
		}
		
		//base case 
		if(arr.length == 1)
		{
			return arr[0];
		}
		
		//split into 3 sub-arrays based on arr[0]
		int smallerArrayLength = 0, equalArrayLength = 0, biggerArrayLength = 0;
		int[] smallerArray = new int[n];
		int[] equalArray = new int[n];
		int[] biggerArray = new int[n];
		
		for(int i = 0; i < n; i++)
		{
			if(arr[i] < arr[0])
			{
				smallerArray[smallerArrayLength] = arr[i];
				smallerArrayLength++;
			}
			else if(arr[i] == arr[0])
			{
				equalArray[equalArrayLength] = arr[i];
				equalArrayLength++;
			}
			else
			{
				biggerArray[biggerArrayLength] = arr[i];
				biggerArrayLength++;
			}
		}
		
		if(k <= smallerArrayLength)
		{
			return Select_Kth_Smallest(smallerArray, k, smallerArrayLength);
		}
		else if(k > smallerArrayLength && k <= (smallerArrayLength + equalArrayLength))
		{
			return arr[0];
		}
		else
		{
			return Select_Kth_Smallest(biggerArray, k - smallerArrayLength - equalArrayLength, biggerArrayLength);
		}
	}
	
	public static int Select_Kth_Smallest(int[] arr, int k)
	{
		return Select_Kth_Smallest(arr, k, arr.length);
	}
		
	public static void main(String[] args)
	{
		/*
		 * test case 0
		 */
		int[] arr0 = {2, 36, 5, 21, 8, 13, 11, 20, 5, 4, 1};
		System.out.println(Select_Kth_Smallest(arr0, 11));
	}
}