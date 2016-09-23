/*
Greedy Algorithm: single-link clustering

*/
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.util.ArrayList;

public class ClusteringBig
{	
	private static void parseBigClusteringInput(String fileName, Hashtable<String, Integer> verticesHT,
												ArrayList<String> verticesArr, int[] graphInfo)
	{		
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			
			//Initialize graph info : number of vertices and number of bits of each vertex
			strLine = br.readLine();
			parse = strLine.split("\\s+", 0);
			if(parse.length != 2)
			{
				System.err.println("Error: Invalid input file");
				return;
			}			
			graphInfo[0] = Integer.parseInt(parse[0]);
			graphInfo[1] = Integer.parseInt(parse[1]);
						
			//Read in all the vertices and store them in hash table
			int idx = 0;
			while((strLine = br.readLine()) != null)
			{	
				strLine = strLine.replaceAll("\\s+","");
				verticesArr.add(strLine);
				verticesHT.put(strLine, idx);
				idx++;			
			}
			br.close();
			in.close();			
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
	}
	
	private static char switchBetweenZeroAndOne(char c)
	{
		if(c == '0')
		{
			return '1';
		}
		else
		{
			return '0';
		}
	}
	
	private static void generateAllDiffStrOfDistOne(String str, ArrayList<String> arr)
	{
		char[] charArr = str.toCharArray();
		
		for(int i = 0; i < charArr.length; i++)
		{
			charArr[i] = switchBetweenZeroAndOne(charArr[i]);
			arr.add(String.valueOf(charArr));
			charArr[i] = switchBetweenZeroAndOne(charArr[i]);		
		}
	}
	
	private static void generateAllDiffStrOfDistTwo(String str, ArrayList<String> arr)
	{
		char[] charArr = str.toCharArray();
		
		for(int i = 0; i < charArr.length; i++)
		{
			charArr[i] = switchBetweenZeroAndOne(charArr[i]);
			for(int j = (i + 1); j < charArr.length; j++)
			{
				charArr[j] = switchBetweenZeroAndOne(charArr[j]);
				arr.add(String.valueOf(charArr));
				charArr[j] = switchBetweenZeroAndOne(charArr[j]);
			}
			charArr[i] = switchBetweenZeroAndOne(charArr[i]);
		}
	}
	
	public static int CalClusteringNumForSpacingThree(Hashtable<String, Integer> verticesHT,
											 ArrayList<String> verticesArr, WeightedQuickUnionUF uf)
	{
		ArrayList<String> temp = new ArrayList<String>();
		Integer idx;
		
		//Cluster all vertex pairs of hamming distance 0
		for(int i = 0; i < verticesArr.size(); i++)
		{
			idx = verticesHT.get(verticesArr.get(i));
			if(idx != null)
			{
				uf.Union(i, idx);
			}
		}
		
		// Cluster all vertex pairs of hamming distance 1
		for(int i = 0; i < verticesArr.size(); i++)
		{
			generateAllDiffStrOfDistOne(verticesArr.get(i), temp);
			
			for(int j = 0; j < temp.size(); j++)
			{
				idx = verticesHT.get(temp.get(j));
				if(idx != null)
				{
					uf.Union(i, idx);
				}
			}
			temp.clear();
		}
		
		// Cluster all vertex pairs of hamming distance 2
		for(int i = 0; i < verticesArr.size(); i++)
		{
			generateAllDiffStrOfDistTwo(verticesArr.get(i), temp);
			
			for(int j = 0; j < temp.size(); j++)
			{
				idx = verticesHT.get(temp.get(j));
				if(idx != null)
				{
					uf.Union(i, idx);
				}
			}
			temp.clear();
		}
		return uf.GetCount();
	}
	
	//An algorithm that repeatedly finds and clears the lowest-order nonzero bit.
	private static int hamming_distance(int x, int y)
	{
		int dist = 0;
		int val = x ^ y;
		
		//count the number of bits set
		while(val != 0)
		{
			//A bit is set, so increment the count and clear the bit
			dist++;
			val &= (val - 1);
		}
		return dist;
	}
	
	public static void main(String[] args)
	{
		ArrayList<String> verticesArr = new ArrayList<String>();
		Hashtable<String, Integer> verticesHT = new Hashtable<String, Integer>();
		int[] graphInfo = new int[2];
		parseBigClusteringInput("clustering_big.txt", verticesHT, verticesArr, graphInfo);
		
		WeightedQuickUnionUF uf = new WeightedQuickUnionUF(graphInfo[0]);
		int result = CalClusteringNumForSpacingThree(verticesHT, verticesArr, uf);
		System.out.println(result);
	}
}