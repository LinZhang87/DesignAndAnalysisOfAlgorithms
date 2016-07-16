/*
 * The problem
 * Input: array A containing the numbers 1,2,3,...,n in some arbitrary order
 * Output: number of inversions = number of pairs(i,j) of array indices with i < j and A[i] > A[j]
 * 
* Divide and conquer paradigm
* O(n * log(n)) algorithm for counting inversions
* 
* Idea: piggybacking on merge sort
*/

public class InversionCount
{
	private static int countSplitInv(int[] a, int[] aux, int lo, int mid, int hi)
	{
		for(int k = lo; k <= hi; k++)
		{
			aux[k] = a[k];
		}
		
		int i = lo, j = mid + 1, invCount = 0;
		for(int k = lo; k <= hi; k++)
		{
			if(i > mid)
			{
				a[k] = aux[j++];
			}
			else if(j > hi)
			{
				a[k] = aux[i++];
			}
			else if(aux[j] < aux[i])
			{
				a[k] = aux[j++];
				// the split inversions involving element aux[j] of the 2nd array is precisely
				// the numbers left in the 1st array when aux[j] is copied to a
				invCount += (mid - i + 1);
			}
			else
			{
				a[k] = aux[i++];
			}
		}
		
		return invCount;
	}
	private static int sortAndCount(int[] a, int[] aux, int lo, int hi)
	{
		if(hi <= lo)
		{
			return 0;
		}
		int mid = lo + (hi - lo) / 2;
		int leftInv, rightInv, splitInv;
		leftInv = sortAndCount(a, aux, lo, mid);
		rightInv = sortAndCount(a, aux, mid+1, hi);
		splitInv = countSplitInv(a, aux, lo, mid, hi);
		return leftInv + rightInv + splitInv;
	}
	public static int invCount(int[] a)
	{
		int[] aux = new int[a.length];
		return sortAndCount(a, aux, 0, a.length - 1);
	}
	
	public static void main(String[] args)
	{
		int a[] = {1,3,5,2,4,6};
		int b[] = {1,2,3,4,5,6};
		int c[] = {6,5,4,3,2,1};
		System.out.println(invCount(a));
		System.out.println(invCount(b));
		System.out.println(invCount(c));
	}		
}