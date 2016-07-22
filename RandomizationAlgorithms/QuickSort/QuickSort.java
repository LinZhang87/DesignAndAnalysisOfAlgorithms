/*
* 
*/
import edu.princeton.cs.algs4.*;
public class QuickSort
{	
	private static void swap(int[] arr, int i, int j)
	{
		int temp = arr[i];
		arr[i] = arr[j];
		arr[j] = temp;
	}
	
	private static int findMedianIndexOfThree(int[] arr, int lo, int hi)
	{
		int mid = lo + (hi - lo)/2;
		
		if(arr[lo] < arr[mid])
		{
			if(arr[lo] >= arr[hi])
			{
				// arr[lo] is [arr(hi), arr[mid])
				return lo;
			}
			else if(arr[mid] < arr[hi])
			{
				// arr[mid] is (arr[lo],arr[hi])
				return mid;
			}
		}
		else
		{
			if(arr[lo] < arr[hi])
			{
				// arr[lo] is [arr[mid],arr[hi])
				return lo;
			}
			else if(arr[mid] >= arr[hi])
			{
				// arr[mid] is[arr[hi],arr[lo]]
				return mid;
			}
		}
		
		// the rest two cases where arr[hi] is the median value
		return hi;
	}
	private static void qSort(int[] arr, int lo, int hi)
	{
		if(hi > lo)
		{
			int pivotIndex = findMedianIndexOfThree(arr, lo, hi);
			int pivot = arr[pivotIndex];
			swap(arr, lo, pivotIndex);
			int i = lo + 1;
			for(int j = lo + 1; j <= hi; j++)
			{
				if(arr[j] < pivot)
				{
					swap(arr, i, j);
					i++;
				}
			}
			swap(arr, lo, i - 1);
					
			qSort(arr, lo, i - 2);
			qSort(arr, i, hi);
		}
	}
	public static void quickSort(int[] arr)
	{
		qSort(arr, 0, arr.length - 1);
	}
	
	private static void show(int[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			StdOut.println(a[i]);
		}
	}
	
	public static void main(String[] args)
	{
		int[] arr0 = {1};
		quickSort(arr0);
		System.out.println("test case 0, arr0 = {1}");
		show(arr0);
		System.out.println("\n");

		int[] arr1 = {1,2};
		quickSort(arr1);
		System.out.println("test case 1, arr1 = {1,2}");
		show(arr1);
		System.out.println("\n");

		int[] arr2 = {2,1};
		quickSort(arr2);
		System.out.println("test case 2, arr2 = {2,1}");
		show(arr2);
		System.out.println("\n");

		int[] arr3 = {3,8,2,5,1,4,7,6};
		quickSort(arr3);
		System.out.println("test case 3, arr3 = {3,8,2,5,1,4,7,6}");
		show(arr3);
		System.out.println("\n");
		
		int[] arr4 = {3,8,2,5};
		quickSort(arr4);
		System.out.println("test case 4, arr4 = {3,8,2,5}");
		show(arr4);
		System.out.println("\n");
		
		int[] arr5 = {3,8,2,5,1,6,4,7,6,5};
		quickSort(arr5);
		System.out.println("test case 5, arr5 = {3,8,2,5,1,6,4,7,6,5}");
		show(arr5);
		System.out.println("\n");
		
		int[] arr6 = {3,3,2,5,1,6,4,3,6,5};
		quickSort(arr6);
		System.out.println("test case 5, arr5 = {3,3,2,5,1,6,4,3,6,5}");
		show(arr6);
		System.out.println("\n");
	}
}