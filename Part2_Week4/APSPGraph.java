import java.util.HashMap;

public class APSPGraph
{
	private HashMap<Integer, APSPVertex> vertices;
	private int numOfVertices;
	private int numOfEdges;
	
	public APSPGraph()
	{
		this.vertices = new HashMap<Integer, APSPVertex>();
		this.numOfVertices = 0;
		this.numOfEdges = 0;
	}
	
	public int GetNumOfVertices()
	{
		return this.numOfVertices;
	}
	
	public int GetNumOfEdges()
	{
		return this.numOfEdges;
	}
	
	public void SetNumOfVertices(int n)
	{
		this.numOfVertices = n;
	}
	
	public void SetNumOfEdges(int n)
	{
		this.numOfEdges = n;
	}
	
    public void AddVertex(int l, APSPVertex v)
	{
		this.vertices.put(l, v);
	}
			
	public HashMap<Integer, APSPVertex> GetAllVertices()
	{
		return this.vertices;
	}
	
	public APSPVertex GetVertex(int l)
	{
		if(this.vertices.containsKey(l) == false)
		{
			return null;
		}
		
		return this.vertices.get(l);
	}
	
	public float ReturnEdgeCost(int l1, int l2)
	{
		if(this.vertices.containsKey(l1) == false || this.vertices.containsKey(l2) == false)
		{
			return Float.POSITIVE_INFINITY;
		}
		return this.vertices.get(l1).ReturnEdgeCost(l2);
	}
}