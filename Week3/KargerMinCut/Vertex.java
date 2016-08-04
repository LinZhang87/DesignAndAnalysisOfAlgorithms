import java.util.LinkedList;

public class Vertex
{
	private int label;
	private LinkedList<Integer> edges;
	
	public Vertex(int l)
	{
		this.label = l;
		this.edges = new LinkedList<Integer>();
	}
	
	public Vertex(Vertex v)
	{
		this.label = v.label;
		this.edges = new LinkedList<Integer>(v.edges);
	}
	
	public void AddEdge(int e)
	{
		this.edges.add(e);
	}
	
	public int edgesNum()
	{
		return this.edges.size();
	}
	
	public int getLabel()
	{
		return this.label;
	}
	
	public LinkedList<Integer> getEdges()
	{
		return this.edges;
	}
	
	public void display()
	{
		System.out.print(this.label + " : ");
		for(int i = 0; i < this.edges.size(); i++)
		{
			System.out.print(this.edges.get(i) + " ");
		}
		System.out.println();
	}
}