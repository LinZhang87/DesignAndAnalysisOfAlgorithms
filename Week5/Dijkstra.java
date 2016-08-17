import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.HashSet;

public class Dijkstra
{
	
	public static void DijkstraAlgorithm(DijkstraGraph g, int startVertexLabel)
	{
		g.GetAllVertices().get(startVertexLabel).SetShortestPath(0);
		PriorityQueue<DijkstraVertex> toExplore = new PriorityQueue<DijkstraVertex>();
		HashSet<DijkstraVertex> visited = new HashSet<DijkstraVertex>();
		for(int i = 0; i < g.GetAllVertices().size(); i++)
		{
			toExplore.add(g.GetAllVertices().get(i));
		}
		
		while(toExplore.isEmpty() == false)
		{
			DijkstraVertex v = toExplore.remove();
			visited.add(v);
			
			for(DijkstraEdge e : v.GetEdges())
			{
				if(toExplore.contains(e.GetVertex2()))
				{
					// Perform the relaxation procedure
					if(v.GetShortestPath() + e.GetWeight() < e.GetVertex2().GetShortestPath())
					{
						if(toExplore.remove(e.GetVertex2()) == false)
						{
							System.err.println("Error: something is wrong!");
							return;
						}
						e.GetVertex2().SetShortestPath(v.GetShortestPath() + e.GetWeight());
						toExplore.add(e.GetVertex2());
					}
				}
			}
		}
	}
	
	private static void ParseDijkstraInput(String fileName, DijkstraGraph g)
	{
		try
		{
			FileInputStream fstream = new FileInputStream(fileName);
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			String[] parse1;
			
			while((strLine = br.readLine()) != null)
			{
				DijkstraVertex v1, v2;
				parse = strLine.split("\\s+", 0);

				int label1 = Integer.parseInt(parse[0]) - 1;
				if(g.GetAllVertices().containsKey(label1) == false)
				{
					v1 = new DijkstraVertex(label1);
					g.AddVertex(label1, v1);
				}
				else
				{
					v1 = g.GetVertex(label1);
				}
				
				for(int i = 1; i < parse.length; i++)
				{
					parse1 = parse[i].split(",", 0);
					if(parse1.length != 2)
					{
						System.err.println("Error: Invalid input file");
						return;
					}
					int label2 = Integer.parseInt(parse1[0]) - 1;
					int weight = Integer.parseInt(parse1[1]);
					
					if(g.GetAllVertices().containsKey(label2) == false)
					{
						v2 = new DijkstraVertex(label2);
						g.AddVertex(label2, v2);
					}
					else
					{
						v2 = g.GetVertex(label2);
					}
												
					DijkstraEdge newEdge = new DijkstraEdge(v1, v2, weight);
					v1.AddEdge(newEdge);
				}
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
		DijkstraGraph g = new DijkstraGraph();
		ParseDijkstraInput("dijkstraData.txt", g);
		DijkstraAlgorithm(g, 0);
		//g.Display();
		
		//Report the shortest path distance to the following ten vertices
		//6, 36, 58, 81, 98, 114, 132, 164, 187, 196
		System.out.println(g.GetAllVertices().get(6).GetShortestPath());
		System.out.println(g.GetAllVertices().get(36).GetShortestPath());
		System.out.println(g.GetAllVertices().get(58).GetShortestPath());
		System.out.println(g.GetAllVertices().get(81).GetShortestPath());
		System.out.println(g.GetAllVertices().get(98).GetShortestPath());
		System.out.println(g.GetAllVertices().get(114).GetShortestPath());
		System.out.println(g.GetAllVertices().get(132).GetShortestPath());
		System.out.println(g.GetAllVertices().get(164).GetShortestPath());
		System.out.println(g.GetAllVertices().get(187).GetShortestPath());
		System.out.println(g.GetAllVertices().get(196).GetShortestPath());
	}
}