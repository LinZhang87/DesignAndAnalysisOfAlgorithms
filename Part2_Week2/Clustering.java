/*
Greedy Algorithm: single-link clustering

*/
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class Clustering
{	
	private static void parseSmallClusteringInput(String fileName, ClusteringGraph g, ArrayList<ClusteringEdge> edges)
	{
		//Debug variables
		int numOfNodes = 0;
		int numOfEdges = 0;
		
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			int label1, label2, weight, numOfVertices;
			ClusteringVertex v1, v2;
			
			//Initialize union find data structure
			strLine = br.readLine();
			numOfVertices = Integer.parseInt(strLine);
			g.InitGraphClusteringUnion(numOfVertices);
			
			while((strLine = br.readLine()) != null)
			{			
				parse = strLine.split("\\s+", 0);
				if(parse.length != 3)
				{
					System.err.println("Error: Invalid input file");
					return;
				}

				label1 = Integer.parseInt(parse[0]) - 1;
				if(g.GetAllVertices().containsKey(label1) == false)
				{
					v1 = new ClusteringVertex(label1);
					g.AddVertex(label1, v1);
					numOfNodes++;
				}
				else
				{
					v1 = g.GetVertex(label1);
				}
				
				label2 = Integer.parseInt(parse[1]) - 1;
				weight = Integer.parseInt(parse[2]);
				
				if(g.GetAllVertices().containsKey(label2) == false)
				{
					v2 = new ClusteringVertex(label2);
					g.AddVertex(label2, v2);
					numOfNodes++;
				}
				else
				{
					v2 = g.GetVertex(label2);
				}
											
				ClusteringEdge newEdge1 = new ClusteringEdge(v1, v2, weight);
				v1.AddEdge(newEdge1);
				ClusteringEdge newEdge2 = new ClusteringEdge(v2, v1, weight);
				v2.AddEdge(newEdge2);
				
				edges.add(newEdge1);
				numOfEdges++;
			}
			br.close();
			in.close();			
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
		
		System.out.println("Total number of nodes is " + numOfNodes);
		System.out.println("Total number of edges is " + numOfEdges);
	}
	public static int CalculateMaxSpacing(int numOfClustering, ClusteringGraph g, ArrayList<ClusteringEdge> edges)
	{
		int i = 0, label1, label2;
		ClusteringEdge curr = null;
		Collections.sort(edges);
		while(g.GetClusteringsUnion().GetCount() > numOfClustering)
		{
			curr = edges.get(i);
			label1 = curr.GetVertex1().GetLabel();
			label2 = curr.GetVertex2().GetLabel();
			
			g.GetClusteringsUnion().Union(label1, label2);	
			
			i++;
		}
		curr = edges.get(i);
		label1 = curr.GetVertex1().GetLabel();
		label2 = curr.GetVertex2().GetLabel();
		while(g.GetClusteringsUnion().Connected(label1, label2))
		{
			i++;
			curr = edges.get(i);
			label1 = curr.GetVertex1().GetLabel();
			label2 = curr.GetVertex2().GetLabel();
		}
		return curr.GetCost();
	}
	
	public static void main(String[] args)
	{
		ClusteringGraph g = new ClusteringGraph();
		ArrayList<ClusteringEdge> edges = new ArrayList<ClusteringEdge>();
		parseSmallClusteringInput("clustering1.txt", g, edges);
		//System.out.println(g.GetClusteringsUnion().GetCount());
		int result = CalculateMaxSpacing(4, g, edges);
		System.out.println(result);
	}
}