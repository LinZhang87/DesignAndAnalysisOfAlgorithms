import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Set;

public class SCC
{
	// For finishing times in 1st pass
	private static int t = 0;
	// For leaders in 2nd pass
	private static Vertex leader = null;
	// For counting SCC's size
	private static int scc_size = 0;
	
	private static Graph ReverseGraph(Graph g)
	{
		Graph reversed = new Graph();
		Set<Integer> oldLabelSet = g.GetGraph().keySet();
		for(int i : oldLabelSet)
		{
			Vertex newEndVertex;
			if((newEndVertex = reversed.GetGraph().get(i)) == null)
			{
				newEndVertex = new Vertex(i);
			}
			
			reversed.AddVertex(i, newEndVertex);
			
			for(Vertex j : g.GetGraph().get(i).GetEndVertices())
			{
				Vertex newStartVertex;
				int label = j.GetLabel();
				
				if((newStartVertex = reversed.GetGraph().get(label)) == null)
				{
				    newStartVertex = new Vertex(label);
				    reversed.AddVertex(label, newStartVertex);
				}
				
				newStartVertex.AddEndVertex(newEndVertex);
			}
		}
		return reversed;
	}
	
	// all vertices have been initialized to not explored
	private static void DFS_FinishTime(Graph g, Vertex startVertex)
	{
		startVertex.SetExplored();
		for(Vertex v : startVertex.GetEndVertices())
		{
			if(v.IsExplored() == false)
			{
				DFS_FinishTime(g, v);
			}
		}
		t++;
		startVertex.SetExploredFinishTime(t);
	}
	
	private static void DFS_SCC(Graph g, Vertex startVertex)
	{
		startVertex.SetExplored();
		startVertex.SetLeader(leader);
		scc_size++;
		
		for(Vertex v : startVertex.GetEndVertices())
		{
			if(v.IsExplored() == false)
			{
				DFS_SCC(g, v);
			}
		}	
	}
	
	public static void ComputeSCC(Graph g, int[] result)
	{
		//Reverse graph edges
		Graph reversed = ReverseGraph(g);
		
		//Debug 
		//reversed.Display();
		
		//Run DFS-loop on reversed graph
		int size = g.Size();
		HashMap<Integer, Vertex> gReverseMap = reversed.GetGraph();
		
		for(int i = size - 1; i >= 0; i--)
		{
			Vertex curr = gReverseMap.get(i);
			if(curr.IsExplored() == false)
			{
				DFS_FinishTime(reversed, curr);
			}
		}
		
		//Debug 
		//reversed.Display();
		
		//Change the original graph's vertices labels to their finished time
		HashMap<Integer, Vertex> gMap = g.GetGraph();
		for(int i = 0; i < size; i ++)
		{
			gMap.get(i).SetLabel(gReverseMap.get(i).GetExploredFinishTime() - 1);
		}
		
		//Debug
		//g.Display();
		
		//Run DFS-loop on original graph in decreasing order of finishing time
		
		//Update the map keys
		Graph newKeyGraph = new Graph();
		HashMap<Integer, Vertex> gNewKeyMap = newKeyGraph.GetGraph();
		for(int i = 0; i <= size - 1; i++)
		{
			gNewKeyMap.put(gMap.get(i).GetLabel(), gMap.get(i));
		}
		
		int scc_count = 0;
		for(int i = size - 1; i >= 0; i--)
		{
			Vertex curr = gNewKeyMap.get(i);
			if(curr.IsExplored() == false)
			{
				leader = curr;
				DFS_SCC(newKeyGraph, curr);
				result[scc_count] = scc_size;
				scc_size = 0;
				scc_count++;
			}
		}
	}
	
	private static void ParseSCCInput(String fileName, Graph g)
	{
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			
			while((strLine = br.readLine()) != null)
			{
				parse = strLine.split("\\s+", 0);
				if(parse.length != 2)
				{
					System.err.println("Error: Invalid input file");
					return;
				}
				int startKey = Integer.parseInt(parse[0]) - 1;
				int endKey = Integer.parseInt(parse[1]) - 1;
				Vertex startVertex, endVertex;
				
				if(g.GetGraph().containsKey(startKey) == false)
				{
					startVertex = new Vertex(startKey);
					g.GetGraph().put(startKey, startVertex);
				}
				
				if(g.GetGraph().containsKey(endKey) == false)
				{
					endVertex = new Vertex(endKey);
					g.GetGraph().put(endKey, endVertex);
				}
				else
				{
					endVertex = g.GetGraph().get(endKey);
				}
				
				g.GetGraph().get(startKey).AddEndVertex(endVertex);;
			}
			br.close();
			in.close();			
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
	}
	
	public static void main(String[] args)
	{
		/*
		 * test case 1 : SCC_test1.txt, expected output: 3,3,3,0,0
		 */
		
		/*
		 * test case 2: SCC_test2.txt, expected output: 3,3,2,0,0
		 */
		
		/*
		 * test case 3: SCC_test3.txt, expected output: 3,3,1,1,0
		 */
		
		/*
		 * test case 4: SCC_test4.txt, expected output: 7,1,0,0,0
		 */
		
		/*
		 * test case 5: SCC_test5.txt, expected output: 6,3,2,1,0
		 */
		
		Graph g = new Graph();
		ParseSCCInput("SCC.txt", g);
		//g.Display();
		
		int[] result = new int[g.Size()];
		
		//System.out.println("\n\n\n");
		//Graph reversed = ReverseGraph(g);
		//reversed.Display();
		ComputeSCC(g, result);
		
		Arrays.sort(result);
		
		for(int i = g.Size() - 1; i >= (g.Size() - 5); i--)
		{
			System.out.println(result[i]);
		}
	}
}