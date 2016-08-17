import java.util.HashMap;

public class DijkstraGraph
{
	private HashMap<Integer, DijkstraVertex> vertices;
	
	public DijkstraGraph()
	{
		this.vertices = new HashMap<Integer, DijkstraVertex>();
	}
	
	public void AddVertex(int l, DijkstraVertex v)
	{
		vertices.put(l, v);
	}
	
	public HashMap<Integer, DijkstraVertex> GetAllVertices()
	{
		return this.vertices;
	}
	
	public DijkstraVertex GetVertex(int l)
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