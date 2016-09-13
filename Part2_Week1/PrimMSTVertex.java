import java.util.ArrayList;

public class PrimMSTVertex implements Comparable
{
	private int label;
	private double minKey;
	private ArrayList<PrimMSTEdge> edges;
	
	public PrimMSTVertex(int l)
	{
		this.label = l;
		this.minKey = Double.POSITIVE_INFINITY;
		this.edges = new ArrayList<PrimMSTEdge>();
	}
	
	public void AddEdge(PrimMSTEdge e)
	{
		this.edges.add(e);	
	}
	
	public ArrayList<PrimMSTEdge> GetEdges()
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
	
	public double GetMinKey()
	{
		return this.minKey;
	}
	
	public void SetMinKey(double p)
	{
		this.minKey = p;
	}
	
	public int compareTo(Object o)
	{
		PrimMSTVertex v = (PrimMSTVertex)o;
		if(this.minKey < v.GetMinKey())
		{
			return -1;
		}
		else if(this.minKey > v.GetMinKey())
		{
			return 1;
		}
		else
		{
			return 0;
		}
	}
	
	public void Display()
	{
		System.out.print(this.label + ":  ");
		for(PrimMSTEdge e : this.edges)
		{
			e.Display();
		}
	}
}