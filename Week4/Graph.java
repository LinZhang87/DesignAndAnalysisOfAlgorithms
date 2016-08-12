//import java.util.ArrayList;
import java.util.HashMap;

public class Graph
{
	private HashMap<Integer, Vertex> graph;
	
	public Graph()
	{
		this.graph = new HashMap<Integer, Vertex>();
	}
	
	public Graph(Graph g)
	{
		this.graph = new HashMap<Integer, Vertex>(g.graph);
	}
	
	public int Size()
	{
		return this.graph.size();
	}
	
	public HashMap<Integer, Vertex> GetGraph()
	{
		/*HashMap<Integer, Vertex> copy = new HashMap<Integer, Vertex>();
		this.graph.putAll(copy);
		return copy;*/
		return this.graph;
	}
	
	public void AddVertex(Integer key, Vertex v)
	{
		this.graph.put(key, v);
	}
	
	public void Display()
	{	
		int i;
		for(i = 0; i < this.graph.size(); i++)
		{
			this.graph.get(i).Display();
		}
	}
}