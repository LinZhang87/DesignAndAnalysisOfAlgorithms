import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Set;
import java.util.Hashtable;

public class TwoSatScc
{
	// For finishing times in 1st pass
	private static int t = 0;
	// For leaders in 2nd pass
	private static Integer leader = null;
	
	private static TwoSatGraph ReverseTwoSatGraph(TwoSatGraph g)
	{
		TwoSatGraph reversed = new TwoSatGraph();
		Set<Integer> oldLabelSet = g.GetTwoSatGraph().keySet();
		for(int i : oldLabelSet)
		{
			TwoSatVertex newEndTwoSatVertex;
			if((newEndTwoSatVertex = reversed.GetTwoSatGraph().get(i)) == null)
			{
				newEndTwoSatVertex = new TwoSatVertex(i);
			}
			
			reversed.AddTwoSatVertex(i, newEndTwoSatVertex);
			
			for(TwoSatVertex j : g.GetTwoSatGraph().get(i).GetEndVertices())
			{
				TwoSatVertex newStartTwoSatVertex;
				int label = j.GetLabel();
				
				if((newStartTwoSatVertex = reversed.GetTwoSatGraph().get(label)) == null)
				{
				    newStartTwoSatVertex = new TwoSatVertex(label);
				    reversed.AddTwoSatVertex(label, newStartTwoSatVertex);
				}
				
				newStartTwoSatVertex.AddEndTwoSatVertex(newEndTwoSatVertex);
			}
		}
		return reversed;
	}
	
	// all vertices have been initialized to not explored
	private static void DFS_FinishTime(TwoSatGraph g, TwoSatVertex startTwoSatVertex)
	{
		startTwoSatVertex.SetExplored();
		for(TwoSatVertex v : startTwoSatVertex.GetEndVertices())
		{
			if(v.IsExplored() == false)
			{
				DFS_FinishTime(g, v);
			}
		}
		t++;
		startTwoSatVertex.SetExploredFinishTime(t);
	}
	
	private static void DFS_TwoSatScc(TwoSatGraph g, TwoSatVertex startTwoSatVertex)
	{
		startTwoSatVertex.SetExplored();
		startTwoSatVertex.SetLeader(leader);
		
		//TwoSatScc_size++;
		
		for(TwoSatVertex v : startTwoSatVertex.GetEndVertices())
		{
			if(v.IsExplored() == false)
			{
				DFS_TwoSatScc(g, v);
			}
		}	
	}
	
	public static void ComputeTwoSatScc(TwoSatGraph g)
	{
		//Reverse TwoSatGraph edges
		TwoSatGraph reversed = ReverseTwoSatGraph(g);
		
		//Debug 
		//reversed.Display();
		
		//Run DFS-loop on reversed TwoSatGraph
		int size = g.Size();
		
		HashMap<Integer, TwoSatVertex> gReverseMap = reversed.GetTwoSatGraph();
		
		for(int i = (size / 2); i >= 1; i--)
		{
			TwoSatVertex curr = gReverseMap.get(i);
			if(curr.IsExplored() == false)
			{
				DFS_FinishTime(reversed, curr);
			}
			
			curr = gReverseMap.get(-i);
			if(curr.IsExplored() == false)
			{
				DFS_FinishTime(reversed, curr);
			}
		}
		
		//the next step is to run dfs-loop on the original graph.
		//processing nodes in decreasing order of finishing times
		
		//What needs to be done: get an array of keys that is sorted
		//based on decreasing order of finishing times from the reversed graph.
		ArrayList<TwoSatVertex> sortByFT = new ArrayList<TwoSatVertex>();
		for(int i = (size / 2); i >= 1; i--)
		{
			sortByFT.add(gReverseMap.get(i));
			sortByFT.add(gReverseMap.get(-i));
		}
		
		Collections.sort(sortByFT);
		HashMap<Integer, TwoSatVertex> vertices = g.GetTwoSatGraph();
		int curr_idx;
		
		for(int i = 0; i < sortByFT.size(); i++)
		{
			curr_idx = sortByFT.get(i).GetLabel();
			TwoSatVertex curr = vertices.get(curr_idx);
			
			if(curr.IsExplored() == false)
			{
				leader = curr.GetLabel();
				DFS_TwoSatScc(g, curr);
			}
		}
	}
	
	private static void ParseTwoSatSccInput(String fileName, TwoSatGraph g)
	{
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			int startKey, endKey;
			
			strLine = br.readLine();
			int n = Integer.parseInt(strLine);
			TwoSatVertex v, neg_v;
			
			//Add 2*n nodes, one for each variable and its negation.
			for(int i = 1; i <= n; i++)
			{
				v = new TwoSatVertex(i);
				g.AddTwoSatVertex(i, v);
				
				neg_v = new TwoSatVertex(-i);
				g.AddTwoSatVertex(-i, neg_v);
			}
			
			HashMap<Integer, TwoSatVertex> ht = g.GetTwoSatGraph();
			
			while((strLine = br.readLine()) != null)
			{
				parse = strLine.split("\\s+", 0);
				if(parse.length != 2)
				{
					System.err.println("Error: Invalid input file");
					br.close();
					return;
				}
								
				startKey = Integer.parseInt(parse[0]);
			    endKey = Integer.parseInt(parse[1]);
			    
			    //Add two edges from this clause
			    ht.get(-startKey).AddEndTwoSatVertex(ht.get(endKey));
			    ht.get(-endKey).AddEndTwoSatVertex(ht.get(startKey));
			}
			br.close();
			in.close();			
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
	}
	
	private static boolean checkForSat(TwoSatGraph g)
	{
		//Assuming every TwoSatVertex's leader is already set
		Hashtable<Integer, TwoSatVertex> ht = new Hashtable<Integer, TwoSatVertex>();
		
		//push each TwoSatVertex into the hash table. the key is the negate idx. e.g, when 
		// checking TwoSatVertex 5, push (-5, TwoSatVertex 5) into ht. 
		TwoSatVertex v;
		for(int i = 1; i <= (g.Size()/2); i++)
		{
			v = g.GetTwoSatGraph().get(i);
			ht.put(v.GetNegVertexId(), v);
			
			v = g.GetTwoSatGraph().get(-i);
			ht.put(v.GetNegVertexId(), v);
		}
		
		//iterate through the original graph, if it has a scc containing both x and ~x for
		//some variable x, then not sat!
		for(int i = 1; i <= (g.Size() / 2); i++)
		{
			v = g.GetTwoSatGraph().get(i);
			if(v.GetLeader() == ht.get(i).GetLeader())
			{
				return false;
			}
		}
		
		return true;
	}
	
	public static void main(String[] args)
	{		
		TwoSatGraph g = new TwoSatGraph();

		ParseTwoSatSccInput("2sat6.txt", g);
		
		ComputeTwoSatScc(g);
		
		boolean r = checkForSat(g);
		System.out.println(r);
	}
}