import java.util.ArrayList;

public class Graph
{
	private ArrayList<Vertex> graph;
	
	public Graph()
	{
		this.graph = new ArrayList<Vertex>();
	}
	
	public Graph(Graph g)
	{
		this.graph = new ArrayList<Vertex>(g.graph);
	}
	
	public int size()
	{
		return this.graph.size();
	}
	
	public ArrayList<Vertex> getGraph()
	{
		ArrayList<Vertex> copy = new ArrayList<Vertex>();
		for(Vertex v : this.graph)
		{
			copy.add(new Vertex(v));
		}
		return copy;
	}
	
	public void AddVertex(Vertex v)
	{
		this.graph.add(v);
	}
	
	public void display()
	{	
		int i;
		for(i = 0; i < this.graph.size(); i++)
		{
			this.graph.get(i).display();
		}
	}
}