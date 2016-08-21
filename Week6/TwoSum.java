import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
//import java.util.Hashtable;
import java.util.HashSet;

public class TwoSum
{
	private static void ParseTwoSumInput(String fileName, Double[] source)
	{
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int i = 0;
			
			while((strLine = br.readLine()) != null)
			{
				source[i] = Double.parseDouble(strLine);
				i++;
			}
			in.close();
			br.close();
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static boolean IfExistDistinctTwoSum(int target, Double[] source, HashSet<Double> ht)
	{
		for(int i = 0; i < source.length; i++)
		{
			if(source[i] != (target - source[i]) && ht.contains(target-source[i]))
			{
				return true;
			}
		}
		return false;
	}
	
	
	public static void main(String[] args)
	{
		Double[] IntegerArray = new Double[1000000];
		int sum = 0;
		ParseTwoSumInput("twosum.txt", IntegerArray);
		HashSet<Double> ht = new HashSet<Double>(1000000);
		for(int i = 0; i < 1000000; i++)
		{
			if(ht.contains(IntegerArray[i]) == false)
			{
				ht.add(IntegerArray[i]);
			}			
		}
		
		for(int t = -10000; t <= 10000; t++)
		{
			if(IfExistDistinctTwoSum(t, IntegerArray, ht))
			{
				//debug 
				//System.out.println(sum);
				sum += 1;
			}
		}
		
		System.out.println(sum);
	}
}