import java.util.ArrayList;

public class ClusteringVertex
{
	private int label;
	private ClusteringVertex leader;
	private ArrayList<ClusteringEdge> edges;
	
	public ClusteringVertex(int l)
	{
		this.label = l;
		this.leader = this;
		this.edges = new ArrayList<ClusteringEdge>();
	}
	
	public void AddEdge(ClusteringEdge e)
	{
		this.edges.add(e);	
	}
	
	public ArrayList<ClusteringEdge> GetEdges()
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
	
	public ClusteringVertex GetLeader()
	{
		return this.leader;
	}
	
	public void SetLeader(ClusteringVertex v)
	{
		this.leader = v;
	}
	
	public void Display()
	{
		System.out.print(this.label + ":  ");
		for(ClusteringEdge e : this.edges)
		{
			e.Display();
		}
	}
}