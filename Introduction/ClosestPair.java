

/**********************************************************************
Divide and Conquer design paradigm

O(n log n) algorithm for closet pair

Description of the problem
Input: a set p = {p1, p2, ....., pn} of n points in the plane (R^2)
Output: a pair of distinct points that has the minimal distance 

Assumption(for convenience):
all points have distinct x-coordinates and y-coordinates

Brute-force search takes theta of n^2 time

1-D version of closet pair:
1. sort points (O(n log n) time)
2. return closest pair of adjacent points (O(n) time)

Goal: O(n log n) time algorithm for 2-D version

**********************************************************************/
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class ClosestPair
{
	static private Comparator<Point> compareByX
	= new Comparator<Point>() {
		public int compare(Point p1, Point p2)
		{
			return Integer.compare(p1.getX(), p2.getX());
		}
	};
	
	static private Comparator<Point> compareByY
	= new Comparator<Point>() {
		public int compare(Point p1, Point p2)
		{
			return Integer.compare(p1.getY(), p2.getY());
		}
	};
	
	private static List<Point> getClosestSplitPair(List<Point> points, int nonSplitMinDistance)
	{
		Point[] Sy = new Point[points.size()];
		points.toArray(Sy);
		
		for(int i = 0; i < Sy.length - 1; i++)
		{
			for(int j = 1; j < Math.min(7, Sy.length - i); j++)
			{
				Point p_i = Sy[i];
				Point p_ij = Sy[i+j];
				if(p_i.Distance(p_ij) < nonSplitMinDistance)
				{
					List<Point> minDistancePair = new ArrayList<Point>();
					minDistancePair.add(p_i);
					minDistancePair.add(p_ij);
					return minDistancePair;
				}
			}
		}
		return null;
	}
	
	public static List<Point> getMinDistancePair(List<Point> points)
	{		
		if(points.size() <= 1)
		{
			return null;
		}
		else if(points.size() == 2)
		{
			List<Point> minDistancePair = new ArrayList<Point>();
			for(Point p : points)
			{
				minDistancePair.add(p);
			}
			return minDistancePair;
		}
		else if(points.size() == 3)
		{
			Point[] pArray = new Point[3];
			points.toArray(pArray);
			int minD = Integer.MAX_VALUE;
			int minId1 = 0, minId2 = 0;
			
			for(int i = 0; i <= 1; i++)
			{
				for(int j = 1; j < (3-i); j++)
				{
					if(pArray[i].Distance(pArray[i+j]) < minD)
					{
						minD = pArray[i].Distance(pArray[i+j]);
						minId1 = i;
						minId2 = i + j;
					}
				}
			}
			
			List<Point> minDistancePair = new ArrayList<Point>();
			minDistancePair.add(pArray[minId1]);
			minDistancePair.add(pArray[minId2]);
			return minDistancePair;
		}
		else
		{
			//Make copies of points sorted by x and y respectively
			//Both sorting takes O(n log n)
			List<Point> sortedByX = new ArrayList<Point>(points);
			List<Point> sortedByY = new ArrayList<Point>(points);
			Collections.sort(sortedByX, compareByX);
			Collections.sort(sortedByY, compareByY);
			
			List<Point> leftSortedByX = sortedByX.subList(0, sortedByX.size() / 2 + 1);
			List<Point> rightSortedByX = sortedByX.subList(sortedByX.size() / 2 + 1, sortedByX.size());
			

			List<Point> leftClosestPair  = getMinDistancePair(leftSortedByX);
			List<Point> rightClosestPair = getMinDistancePair(rightSortedByX);
			
			Point[] leftClosestPairArray = new Point[2];
			Point[] rightClosestPairArray = new Point[2];
			
			leftClosestPair.toArray(leftClosestPairArray);
			rightClosestPair.toArray(rightClosestPairArray);
			
			int minDistanceInLeft = leftClosestPairArray[0].Distance(leftClosestPairArray[1]);
			int minDistanceInRight = rightClosestPairArray[0].Distance(rightClosestPairArray[1]);
			int delta = Math.min(minDistanceInLeft, minDistanceInRight);
			
			Point[] sortedByXArray = new Point[points.size()];
			sortedByX.toArray(sortedByXArray);
			
			int midX = sortedByXArray[sortedByXArray.length / 2].getX();
			
			// Construct a set of points with x-coordinate in [midX - delta, midX + delta], 
			// sorted by y-coordinate
			List<Point> Sy = new ArrayList<Point>();
			for(Point p : sortedByY)
			{
				if(p.getX() >= (midX - delta) && p.getX() <= (midX + delta))
				{
					Sy.add(p);
				}
			}
			
			List<Point> splitClosestPair = getClosestSplitPair(Sy, delta);
			
			if(splitClosestPair == null)
			{
				return minDistanceInLeft > minDistanceInRight ? rightClosestPair : leftClosestPair;
			}
			else
			{
				return splitClosestPair;
			}
		}
	}
	
	public static void main(String[] args)
	{
		/*Point[] points = new Point[5];
		points[0] = new Point(-4, -1);
		points[1] = new Point(0, 0);
		points[2] = new Point(1,1);
		points[3] = new Point(3,1);
		points[4] = new Point(1,5);*/
		
		List<Point> points = new ArrayList<Point>();
		points.add(new Point(-4,-1));
		points.add(new Point(1,0));
		points.add(new Point(1,1));
		points.add(new Point(3,1));
		points.add(new Point(1,5));
		
		/*
		 * test case 0
		 */
		List<Point> min = getMinDistancePair(points);
		for(Point p : min)
		{
			System.out.println(p);
		}
	}
}