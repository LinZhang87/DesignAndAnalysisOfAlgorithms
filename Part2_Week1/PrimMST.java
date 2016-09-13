import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.HashSet;

public class PrimMST
{
	
	private static void ParsePrimInput(String fileName, PrimMSTGraph g)
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
			int label1, label2, weight;
			PrimMSTVertex v1, v2;
			
			br.readLine();
			
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
					v1 = new PrimMSTVertex(label1);
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
					v2 = new PrimMSTVertex(label2);
					g.AddVertex(label2, v2);
					numOfNodes++;
				}
				else
				{
					v2 = g.GetVertex(label2);
				}
											
				PrimMSTEdge newEdge1 = new PrimMSTEdge(v1, v2, weight);
				v1.AddEdge(newEdge1);
				PrimMSTEdge newEdge2 = new PrimMSTEdge(v2, v1, weight);
				v2.AddEdge(newEdge2);
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
	
	public static double PrimMSTAlgorithm(PrimMSTGraph g)
	{
		double mst_sum = 0.0;
		PriorityQueue<PrimMSTVertex> toExplore = new PriorityQueue<PrimMSTVertex>();
		HashSet<PrimMSTVertex> explored = new HashSet<PrimMSTVertex>();
		
		//Set vertex 0's min key to 0; add vertex 0 to explored set
		PrimMSTVertex v1 = g.GetAllVertices().get(0);
		v1.SetMinKey(0);
		explored.add(v1);
		
		//Update the minimum key of the vertices that are directly connected with vertex 0
		for(PrimMSTEdge e : v1.GetEdges())
		{
			e.GetVertex2().SetMinKey(e.GetWeight());
		}
		
		//Add all other vertices into the priority queue
		for(int i = 1; i < g.GetAllVertices().size(); i++)
		{
			toExplore.add(g.GetAllVertices().get(i));
		}
		
		PrimMSTVertex temp1 = null;
		PrimMSTVertex temp2 = null;
		
		while(toExplore.isEmpty() == false)
		{
			temp1 = toExplore.remove();
			explored.add(temp1);
			mst_sum += temp1.GetMinKey();
			
            for(PrimMSTEdge e : temp1.GetEdges())
            {
            	temp2 = e.GetVertex2();
            	if(explored.contains(temp2) == false)
            	{
            		if(toExplore.remove(temp2) == false)
            		{
            			System.err.println("Error!");
            			return 0.0;
            		}
            		
            		if(e.GetWeight() < temp2.GetMinKey())
            		{
            			temp2.SetMinKey(e.GetWeight());
            		}
            		toExplore.add(temp2);
            	}
            }
		}
		
		return mst_sum;
	}
	
	public static void main(String[] args)
	{
		PrimMSTGraph g = new PrimMSTGraph();
		ParsePrimInput("edges.txt", g);
		//g.Display();
		double r = PrimMSTAlgorithm(g);
		System.out.println(r);
	}
}