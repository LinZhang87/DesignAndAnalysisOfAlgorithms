import java.util.ArrayList;

public class Vertex
{
	private int label;
	private boolean explored;
	private int exploreFinishTime;
	private Vertex leader;
	private ArrayList<Vertex> connected_vertices;
	
	public Vertex(int l)
	{
		this.label = l;
		this.explored = false;
		this.exploreFinishTime = -1;
		this.leader = null;
		this.connected_vertices = new ArrayList<Vertex>();
	}
	
	public Vertex(Vertex v)
	{
		this.label = v.label;
		this.explored = v.explored;
		this.exploreFinishTime = v.exploreFinishTime;
		this.connected_vertices = new ArrayList<Vertex>(v.connected_vertices);
	}
	
	public void AddEndVertex(Vertex v)
	{
		this.connected_vertices.add(v);
	}
	
	public int EndVertexNum()
	{
		return this.connected_vertices.size();
	}
	
	public int GetLabel()
	{
		return this.label;
	}
	
	public void SetLabel(int l)
	{
		this.label = l;
	}
	
	public Vertex GetLeader()
	{
		return this.leader;
	}
	
	public void SetLeader(Vertex v)
	{
		this.leader = v;
	}
	
	public ArrayList<Vertex> GetEndVertices()
	{
		return this.connected_vertices;
	}
	
	public void SetExplored()
	{
		this.explored = true;
	}
	
	public void SetExploredFinishTime(int t)
	{
		this.exploreFinishTime = t;
	}
	
	public int GetExploredFinishTime()
	{
		return this.exploreFinishTime;
	}
	
	public boolean IsExplored()
	{
		return this.explored;
	}
	
	public void Display()
	{
		System.out.print(this.label + " : ");
		for(int i = 0; i < this.connected_vertices.size(); i++)
		{
			System.out.print(this.connected_vertices.get(i).GetLabel() + " ");
		}
		System.out.print("Finish time : " + this.GetExploredFinishTime() + " ");
		System.out.println();
	}
}