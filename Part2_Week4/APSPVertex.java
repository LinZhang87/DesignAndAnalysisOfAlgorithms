import java.util.ArrayList;

public class APSPVertex
{
	private int label;
	private ArrayList<APSPEdge> edges;
	
	public APSPVertex(int l)
	{
		this.label = l;
		this.edges = new ArrayList<APSPEdge>();
	}
	
	public void AddEdge(APSPEdge e)
	{
		this.edges.add(e);	
	}
	
	public ArrayList<APSPEdge> GetEdges()
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
	
	public float ReturnEdgeCost(int l)
	{
		for(APSPEdge e : this.edges)
		{
			if(l == e.GetVertex2().GetLabel())
			{
				return e.GetCost();
			}
		}
		return Float.POSITIVE_INFINITY;
	}
}