import java.util.ArrayList;

public class DijkstraVertex implements Comparable
{
	private int label;
	private int shortestPath;
	private ArrayList<DijkstraEdge> edges;
	
	public DijkstraVertex(int l)
	{
		this.label = l;
		this.shortestPath = 1000000;
		this.edges = new ArrayList<DijkstraEdge>();
	}
	
	public void AddEdge(DijkstraEdge e)
	{
		this.edges.add(e);	
	}
	
	public ArrayList<DijkstraEdge> GetEdges()
	{
		return this.edges;
	}
	
	public int GetLabel()
	{
		return this.label;
	}
	
	public void SetLabel(int l)
	{
		this.label = l;
	}
	
	public int GetShortestPath()
	{
		return this.shortestPath;
	}
	
	public void SetShortestPath(int p)
	{
		this.shortestPath = p;
	}
	
	public int compareTo(Object o)
	{
		DijkstraVertex v = (DijkstraVertex)o;
		if(this.shortestPath < v.GetShortestPath())
		{
			return -1;
		}
		else if(this.shortestPath == v.GetShortestPath())
		{
			return 0;
		}
		else
		{
			return 1;
		}
	}
	
	public void Display()
	{
		System.out.print(this.label + ":  ");
		for(DijkstraEdge e : this.edges)
		{
			e.Display();
		}
		
		System.out.print("  shortest path: " + this.shortestPath);
	}
}