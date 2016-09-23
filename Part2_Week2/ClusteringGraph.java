import java.util.HashMap;
import java.util.HashSet;

public class ClusteringGraph
{
	private HashMap<Integer, ClusteringVertex> vertices;
	private WeightedQuickUnionUF clusterings;
	
	public ClusteringGraph()
	{
		this.vertices = new HashMap<Integer, ClusteringVertex>();
		this.clusterings = null;
	}
	
	public void InitGraphClusteringUnion(int n)
	{
		this.clusterings = new WeightedQuickUnionUF(n);
	}
	
	public void AddVertex(int l, ClusteringVertex v)
	{
		vertices.put(l, v);
	}
	
	public WeightedQuickUnionUF GetClusteringsUnion()
	{
		return this.clusterings;
	}
		
	public HashMap<Integer, ClusteringVertex> GetAllVertices()
	{
		return this.vertices;
	}
	
	public ClusteringVertex GetVertex(int l)
	{
		if(this.vertices.containsKey(l) == false)
		{
			return null;
		}
		
		return this.vertices.get(l);
	}
	
	public void Display()
	{
		for(int i = 0; i < this.vertices.size(); i++)
		{
			this.vertices.get(i).Display();
			System.out.println();
		}
	}
}