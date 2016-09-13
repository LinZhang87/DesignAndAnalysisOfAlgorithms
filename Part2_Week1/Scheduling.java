import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Scheduling
{
	public static void CalculateWeightedSum(ArrayList<Job> jobs)
	{
		Comparator<Job> rankByDiff = new Comparator<Job>(){
			public int compare(Job j1, Job j2)
			{
				if(j1.GetWLDiff() == j2.GetWLDiff())
				{
					return j2.GetWeight() - j1.GetWeight();
				}
				else if(j1.GetWLDiff() > j2.GetWLDiff())
				{
					return -1;
				}
				else
				{
					return 1;
				}
			}
		};
		
		Comparator<Job> rankByRatio = new Comparator<Job>(){
			public int compare(Job j1, Job j2)
			{
				if(j1.GetWLRatio() > j2.GetWLRatio())
				{
					return -1;
				}
				else if(j1.GetWLRatio() == j2.GetWLRatio())
				{
					return 0;
				}
				else
				{
					return 1;
				}
			}
		};
		
		/*Collections.sort(jobs, rankByDiff);
		UpdateCompletionTime(jobs);
		double sum_diff = 0.0;
		for(int i = 0; i < jobs.size(); i++)
		{
			sum_diff += jobs.get(i).GetWeight() * jobs.get(i).GetCompletionTime();
		}
		
		System.out.println(sum_diff);*/
		
		Collections.sort(jobs, rankByRatio);
		UpdateCompletionTime(jobs);
		double sum_diff = 0.0;
		for(int i = 0; i < jobs.size(); i++)
		{
			sum_diff += jobs.get(i).GetWeight() * jobs.get(i).GetCompletionTime();
		}
		
		System.out.println(sum_diff);
	}
	
	private static void UpdateCompletionTime(ArrayList<Job> jobs)
	{
		int c = jobs.get(0).GetCompletionTime();
		
		for(int i = 1; i < jobs.size(); i ++)
		{
			Job j = jobs.get(i);
			j.SetCompletionTime(c + j.GetCompletionTime());
			c = j.GetCompletionTime();
		}
	}
	
	private static ArrayList<Job> ParseJobInputByDifference(String fileName)
	{
		ArrayList<Job> inputs = null;
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			int n = Integer.parseInt(br.readLine());
			inputs = new ArrayList<Job>(n);
			String[] parse;
			
			while((strLine = br.readLine()) != null)
			{
				parse = strLine.split("\\s+", 0);
				if(parse.length != 2)
				{
					System.err.println("Error: Invalid input file");
					return null;
				}
				
				inputs.add(new Job(Integer.parseInt(parse[0]), Integer.parseInt(parse[1])));
			}
			in.close();
			br.close();
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
		return inputs;
	}
	
	public static void main(String[] args)
	{
		ArrayList<Job> jobs = ParseJobInputByDifference("jobs.txt");
		CalculateWeightedSum(jobs);
	}
}