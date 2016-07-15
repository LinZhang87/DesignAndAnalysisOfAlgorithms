// Merge sort is a good introduction to divide and conquer design paradigm
// It improves over selection, insertion and bubble sorts
// Time complexity: O(n * log(n)); space complexity: O(n)
import edu.princeton.cs.algs4.*;

public class MergeSort
{
	private static boolean less(Comparable v, Comparable w)
	{
		return v.compareTo(w) < 0;
	}
	private static boolean isSorted(Comparable[] a, int low, int high)
	{
		for(int i = low + 1; i <= high; i++)
		{
			if(less(a[i], a[i - 1]))
			{
				return false;
			}
		}
		return true;
	}
	private static boolean isSorted(Comparable[] a)
	{
		return isSorted(a, 0, a.length - 1);
	}
	
	private static void merge(Comparable[] a, Comparable[] aux, int low, int mid, int high)
	{
		assert isSorted(a, low, mid);
		assert isSorted(a, mid + 1, high);
		
		for(int k = low; k <= high; k++)
		{
			aux[k] = a[k];
		}
		
		int i = low, j = mid + 1;
		for(int k = low; k <= high; k++)
		{
			if(i > mid)
			{
				a[k] = aux[j++];
			}
			else if(j > high)
			{
				a[k] = aux[i++];
			}
			else if(less(aux[j], aux[i]))
			{
				a[k] = aux[j++];
			}
			else
			{
				a[k] = aux[i++];
			}
		}
		
		assert isSorted(a, low, high);
	}
	
	private static void sort(Comparable[] a, Comparable[] aux, int low, int high)
	{
		// base case of the recursive call, only 0 or 1 element in the array
		if(high <= low)
		{
			return;
		}
		int mid = low + (high - low) / 2;
		sort(a, aux, low, mid);
		sort(a, aux, mid + 1, high);
		merge(a, aux, low, mid, high);
	}
	public static void mergesort(Comparable[] a)
	{
		Comparable[] aux = new Comparable[a.length];
		sort(a, aux, 0, a.length - 1);
		assert isSorted(a);
	}
	private static void show(Comparable[] a)
	{
		for(int i = 0; i < a.length; i++)
		{
			StdOut.println(a[i]);
		}
	}
	public static void main(String args[])
	{
		Integer a[] = {3, 4, 6, 1, 8, 3};
		mergesort(a);
		show(a);
	}
}