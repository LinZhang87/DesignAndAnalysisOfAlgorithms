import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class TSP
{
	private static int parseTSPInput(String fileName, ArrayList<Double> xCords, ArrayList<Double> yCords)
	{
		int N = 0;
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			
			strLine = br.readLine();

			N = Integer.parseInt(strLine);
						
			while((strLine = br.readLine()) != null)
			{			
				parse = strLine.split("\\s+", 0);
				if(parse.length != 2)
				{
					System.err.println("Error: Invalid input file");
					br.close();
					return 0;
				}

				xCords.add(Double.parseDouble(parse[0]));
				yCords.add(Double.parseDouble(parse[1]));
			}
			br.close();
			in.close();		
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
		return N;
	}
	
	private static double calDistance(double x1, double x2, double y1, double y2)
	{
		return Math.sqrt(Math.pow((x1 - x2), 2) + Math.pow((y1 - y2), 2));
	}
	
	private static void getSubSets(List<Integer> superSet, int k, int idx,
									Set<Integer> current, List<Set<Integer>> solution)
	{
		if(current.size() == k)
		{
			solution.add(new HashSet<Integer>(current));
			return;
		}
		
		if(idx >= superSet.size())
		{
			return;
		}
		
		Integer x = superSet.get(idx);
		
		//"guess" x is in the subset
		current.add(x);
		getSubSets(superSet, k, idx+1, current, solution);
		
		//"guess" x is not in the subset
		current.remove(x);
		getSubSets(superSet, k, idx+1, current, solution);		
	}
	
	private static List<Set<Integer>> getSubSets(List<Integer> superSet, int k)
	{
		assert k >= 2;
		
		List<Set<Integer>> res = new ArrayList<Set<Integer>>();
		
		getSubSets(superSet, k-1, 1, new HashSet<Integer>(), res);
		
		for(Set<Integer> s : res)
		{
			s.add(1);
		}
		return res;
	}
	
	private static int encodeSubsetIntoArrayIndex(Set<Integer> s, int exclude)
	{
		int idx = 0;
		for(Integer i : s)
		{
			if(i != exclude)
			{
				idx += (1 << (i-1));
			}			
		}
		return idx;
	}
	
	private static int encodeSubsetIntoArrayIndex(Set<Integer> s)
	{
		int idx = 0;
		for(Integer i : s)
		{
			idx += (1 << (i-1));
		}
		return idx;
	}
	
	public static double TSP_DynamicProgramming(ArrayList<Double> xCords, ArrayList<Double> yCords)
	{
		int n = xCords.size();
		int total_subSets = (int)Math.pow(2.0, n);

		double[][] subs = new double[total_subSets][n+1];
		double min = Double.POSITIVE_INFINITY;
		double dist;
		
		List<Set<Integer>> subSets = null;
		List<Integer> superSet = new ArrayList<Integer>();
		
		for(int i = 1; i <= n; i++)
		{
			superSet.add(i);
		}
		
		//Initialize base case
		subs[1][1] = 0;
		
		for(int i = 2; i < total_subSets; i++)
		{
			subs[i][1] = Double.POSITIVE_INFINITY;
		}
		
		//m is subproblem size		
		for(int m = 2; m <= n; m++)
		{
			//get all sub sets of size m that contains start city 1
			subSets = getSubSets(superSet, m);
			
			//for each sub set S of size m that contains start city 1
			for(Set<Integer> subSet : subSets)
			{
				for(Integer j : subSet)
				{
					//for each city in S that != 1
					if(j != 1)
					{
						for(Integer k : subSet)
						{
							//for each city in S that != c
							if(k != j)
							{
								
								dist = subs[encodeSubsetIntoArrayIndex(subSet, j)][k] + 
										calDistance(xCords.get(k-1), xCords.get(j-1),
													yCords.get(k-1), yCords.get(j-1));
							
								
								if(dist < min)
								{
									min = dist;
								}
							}
						}
						
						subs[encodeSubsetIntoArrayIndex(subSet)][j] = min;
						min = Double.POSITIVE_INFINITY;
						
					}
				}
			}
		
		}
		
		min = Double.POSITIVE_INFINITY;
		for(int j = 2; j <= n; j++)
		{
			dist = subs[encodeSubsetIntoArrayIndex(subSets.get(0))][j] + calDistance(xCords.get(j-1), xCords.get(0), yCords.get(j-1), yCords.get(0));
			if(dist < min)
			{
				min = dist;
			}
		}
		
		return min;
}

		
	public static void main(String[] args)
	{
		ArrayList<Double> xCords = new ArrayList<Double>();
		ArrayList<Double> yCords = new ArrayList<Double>();
		
		int n  = parseTSPInput("tsp3.txt", xCords, yCords);
		
		assert n == xCords.size();
				
		double r = TSP_DynamicProgramming(xCords, yCords);
		
		System.out.println(r);		
	}
}