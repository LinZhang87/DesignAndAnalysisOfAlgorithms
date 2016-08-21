import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;

public class Median
{
	public static int CalculateMedian(PriorityQueue<Integer> low, PriorityQueue<Integer> high, int newNum)
	{
		int total_size;
		int headOfHigh; 
		int headOfLow;
		
        if(high.size() > 0)
        {
        	headOfHigh = high.peek();
        }
        else
        {
        	headOfHigh = Integer.MIN_VALUE;
        }
        
        if(low.size() > 0)
        {
        	headOfLow = low.peek();
        }
        else
        {
        	headOfLow = Integer.MAX_VALUE;
        }
		
		if(newNum > headOfHigh)
		{
			high.add(newNum);
		}
		else
		{
			low.add(newNum);
		}
		
		
        if(low.size() > high.size() + 1)
        {
        	high.add(low.remove());
        }

        if(high.size() > low.size() + 1)
        {
        	low.add(high.remove());
        }
        
        if(high.size() > 0)
        {
        	headOfHigh = high.peek();
        }
        
        if(low.size() > 0)
        {
        	headOfLow = low.peek();
        }
        
		total_size = high.size() + low.size();
		
		if(total_size % 2 == 0)
		{
			return headOfLow;
		}
		else if(low.size() > high.size())
		{
			return headOfLow;
		}
		else
		{
			return headOfHigh;
		}
	}
	
	private static void ParseMedianInput(String fileName, int[] source)
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
				source[i] = Integer.parseInt(strLine);
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
	
	
	public static void main(String[] args)
	{
		int sum = 0;
		int[] IntegerArray = new int[10000];
		ParseMedianInput("Median.txt", IntegerArray);
		
		PriorityQueue<Integer> pq_low = new PriorityQueue<Integer>(Collections.reverseOrder());
		PriorityQueue<Integer> pq_high = new PriorityQueue<Integer>();
		
        for(int i = 0; i < 10000; i++)
        {
        	sum += CalculateMedian(pq_low, pq_high, IntegerArray[i]);
        }
        
        System.out.println(sum %  10000);
	}
}