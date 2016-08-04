import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.Random;


public class KargerMinCut
{
	
	private static void replaceVertexLabel(LinkedList<Integer>edges, 
											int startVertexIndex, int endVertexIndex)
	{
		for(int i = 0; i < edges.size(); i++)
		{
			if(edges.get(i) == endVertexIndex)
			{
				edges.set(i, startVertexIndex);
			}
		}
	}
	
	private static Vertex getVertexByLabel(ArrayList<Vertex>v, int label)
	{
		for(Vertex w : v)
		{
			if(w.getLabel() == label)
			{
				return w;
			}
		}
		return null;
	}
	
	private static void removeSelfLoops(Vertex v)
	{
		LinkedList<Integer>edges = v.getEdges();
		int i = 0;
		while(i < edges.size())
		{
			if(v.getLabel() == edges.get(i))
			{
				edges.remove(i);
				continue;
			}
			i++;
		}
	}
	
	private static void displayContractedGraph(ArrayList<Vertex> c)
	{
		System.out.println();
		for(int j = 0; j < c.size(); j++)
		{
			System.out.print(c.get(j).getLabel() + " : ");
			for(int i = 0; i < c.get(j).edgesNum(); i++)
			{
				System.out.print(c.get(j).getEdges().get(i) + " ");
			}
			System.out.println();
		}
	}
	
	public static int RandomContraction(Graph g)
	{
		// start and end index of randomly chosen vertices
		// they are not equal to their vertex label all the time
		int startVertexIndex, endVertexIndex, endVertexIndexInEdges;		
		Vertex startVertex, endVertex;
		
		// get a copy of the original graph, modifications on this
		// copy does not change the original graph
		ArrayList<Vertex> contracted = g.getGraph();
				
		while(contracted.size() > 2)
		{			
			Random rand = new Random();
			startVertexIndex = rand.nextInt(contracted.size());
			startVertex = contracted.get(startVertexIndex);
			endVertexIndexInEdges = rand.nextInt(startVertex.edgesNum());
			endVertexIndex = startVertex.getEdges().get(endVertexIndexInEdges);
			endVertex = getVertexByLabel(contracted, endVertexIndex);
			
			// for each vertex that has an edge to the endVertex[endVertexIndex]
			// update their edges to point to startVertex[startVertexIndex]
			for(int label : endVertex.getEdges())
			{
				LinkedList<Integer>edges = getVertexByLabel(contracted, label).getEdges();
				replaceVertexLabel(edges, startVertex.getLabel(), endVertex.getLabel());
			}
						
			// merge endVertex's neighboring vertices into startVertex's neighboring vertices list
			for(int label : endVertex.getEdges())
			{
				startVertex.AddEdge(label);
			}
			
			// remove self loops from startVertex's neighboring vertices list if any
			removeSelfLoops(startVertex);
			
			// remove endVertex from the copy of the graph vertices
			contracted.remove(endVertex);
		}
		
		if(contracted.size() == 2)
		{
			return contracted.get(0).edgesNum();
		}
		else
		{
			return 0;
		}
	}
	
	public static void main(String[] args)
	{
		Graph g = new Graph();
		try
		{
			FileInputStream fstream = new FileInputStream("KargerMinCut.txt");
			DataInputStream in = new DataInputStream(fstream);
			BufferedReader br = new BufferedReader(new InputStreamReader(in));
			String strLine;
			String[] parse;
			int i;
			
			while((strLine = br.readLine()) != null)
			{
				parse = strLine.split("\\s+", 0);
				Vertex v = new Vertex(Integer.parseInt(parse[0]) - 1);
				for(i = 1; i < parse.length; i++)
				{
					v.AddEdge(Integer.parseInt(parse[i]) - 1);
				}
				g.AddVertex(v);
			}
			in.close();
		}
		catch(Exception e)
		{
				System.err.println("Error: " + e.getMessage());
		}
		
		int trials = g.size();
		ArrayList<Integer> results = new ArrayList<Integer>();
		for(int i = 0; i < trials; i++)
		{	
			results.add(RandomContraction(g));	
		}
		
		Collections.sort(results);
		System.out.println("Min cut is " + results.get(0));
	}
}