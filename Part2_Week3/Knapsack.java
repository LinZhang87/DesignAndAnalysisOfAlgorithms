import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Hashtable;

public class Knapsack
{
	//private static Hashtable<Integer, Integer> sub_results = new Hashtable<Integer, Integer>();
	private static int[][] resultTable;
	private static ArrayList<Integer> values;
	private static ArrayList<Integer> weights;
	private static Hashtable<Integer, Integer> resultHt;
	private static int array_row;
	private static int array_col;
	
	private static int[] parseKnapsackInput(String fileName, ArrayList<Integer> values, 
											ArrayList<Integer> weights)
	{	
		int [] sizeAndItemNum = new int[2];
		try
		{			
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));			
			String strLine;
			String[] parse;
			
			//Initialize knapsack size and items number
			strLine = br.readLine();
			parse = strLine.split("\\s+", 0);
			if(parse.length != 2)
			{
				System.err.println("Error: Invalid input file");
				br.close();
				return null;
			}
			sizeAndItemNum[0] = Integer.parseInt(parse[0]);
			sizeAndItemNum[1] = Integer.parseInt(parse[1]);
						
			while((strLine = br.readLine()) != null)
			{			
				parse = strLine.split("\\s+", 0);
				if(parse.length != 2)
				{
					System.err.println("Error: Invalid input file");
					br.close();
					return null;
				}

				values.add(Integer.parseInt(parse[0]));
				weights.add(Integer.parseInt(parse[1]));
			}
			br.close();
			in.close();
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
		return sizeAndItemNum;
	}
	
	public static ArrayList<Integer> ReconstructKnapsackOptimalSolution(int[] sizeAndItemNum, 
																		int[][] subproblems, ArrayList<Integer> weights)
	{
		ArrayList<Integer> optimalSolution = new ArrayList<Integer>();
		int x = sizeAndItemNum[0];
		int i = sizeAndItemNum[1];
		
		while(x > 0 && i > 0)
		{
			if(subproblems[i][x] > subproblems[i-1][x])
			{
				optimalSolution.add(i);
				x -= weights.get(i-1);
			}
			i--;
		}
				
		return optimalSolution;
	}
	
	public static int CalculateKnapsackOptimalValue(int[] sizeAndItemNum, ArrayList<Integer> values, 
										ArrayList<Integer> weights, int[][] subproblems)
	{
		int totalSize = sizeAndItemNum[0];
		int itemNum = sizeAndItemNum[1];
						
		for(int x = 0; x <= sizeAndItemNum[0]; x++)
		{
			subproblems[0][x] = 0;
		}
		
		for(int i = 1; i <= itemNum; i++)
		{
			for(int x = 0; x <= totalSize; x++)
			{
				if(weights.get(i-1) > x)
				{
					subproblems[i][x] = subproblems[i-1][x];
				}
				else
				{
					subproblems[i][x] = Math.max(subproblems[i-1][x], 
													subproblems[i-1][x-weights.get(i-1)] + values.get(i-1));
				}
				
			}
		}
		return subproblems[itemNum][totalSize];
	}
	
	public static int CalculateKnapsackOptimalValueForBigInput(int num, int totalSize)															
	{
		int r;
		int key = num * array_col + totalSize;
		if(resultHt.containsKey(key) == false)
		{
			//System.out.println("key : " + key + " num : " + num + " totalSize : " + totalSize);
			
			if(totalSize < weights.get(num-1))
			{
				r = CalculateKnapsackOptimalValueForBigInput(num-1, totalSize);
			}
			else
			{
				r = Math.max(CalculateKnapsackOptimalValueForBigInput(num-1, totalSize),
						values.get(num-1) + CalculateKnapsackOptimalValueForBigInput(num-1, totalSize-weights.get(num-1)));
			}
			
			resultHt.put(key, r);
		}
		return resultHt.get(key);
	}
	
	public static void main(String[] args)
	{
		values = new ArrayList<Integer>();
		weights = new ArrayList<Integer>();
		
		int[] sizeAndItemNum = parseKnapsackInput("knapsack_big.txt", values, weights);
		int totalSize = sizeAndItemNum[0];
		int itemNum = sizeAndItemNum[1];
		array_row = itemNum + 1;
		array_col = totalSize + 1;	
		//int[][] subproblems = new int[array_row][array_col];
		
		//int optimalValue = CalculateKnapsackOptimalValue(sizeAndItemNum, values, weights, subproblems);
		//System.out.println(optimalValue);
		
		//ArrayList<Integer> optimalSolution = ReconstructKnapsackOptimalSolution(sizeAndItemNum, subproblems, weights);
		//System.out.println(optimalSolution.toString());
		
		//knapsack big input
		/*ArrayList<Item> allItems = new ArrayList<Item>();
		for(int i = 0; i < itemNum; i++)
		{
			Item curr = new Item(i + 1, values.get(i), weights.get(i));
			allItems.add(curr);
		}	
		Collections.sort(allItems);*/
		
		//sub_results.put(0, 0);
		//resultTable = new int[itemNum + 1][totalSize + 1];
				
		resultHt = new Hashtable<Integer, Integer>();
		for(int i = 0; i < array_row; i++)
		{
			resultHt.put(i * array_col, 0);
			//System.out.println("key : " + i * array_col + " init");
		}
		
		for(int i = 0; i < array_col; i++)
		{
			resultHt.put(i, 0);
			//System.out.println("key : " + i + " init");
		}
				
		/*for(int i = 0; i <= itemNum; i++)
		{
			resultTable[i][0] = 0;
		}
		for(int j = 0; j <= totalSize; j++)
		{
			resultTable[0][j] = 0;
		}
		
		for(int i = 1; i <= itemNum; i++)
		{
			for(int j = 1; j <= totalSize; j++)
			{
				resultTable[i][j] = -1;
			}
		}*/
		int test = CalculateKnapsackOptimalValueForBigInput(itemNum, totalSize);	
		System.out.println(test);
	}
}