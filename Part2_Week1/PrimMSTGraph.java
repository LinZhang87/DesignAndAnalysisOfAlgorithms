import java.util.HashMap;

public class PrimMSTGraph
{
	private HashMap<Integer, PrimMSTVertex> vertices;
	
	public PrimMSTGraph()
	{
		this.vertices = new HashMap<Integer, PrimMSTVertex>();
	}
	
	public void AddVertex(int l, PrimMSTVertex v)
	{
		vertices.put(l, v);
	}
	
	public HashMap<Integer, PrimMSTVertex> GetAllVertices()
	{
		return this.vertices;
	}
	
	public PrimMSTVertex GetVertex(int l)
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