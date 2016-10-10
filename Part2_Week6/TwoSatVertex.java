import java.util.ArrayList;

public class TwoSatVertex implements Comparable<TwoSatVertex>
{
	private int label;
	private int fixedVarId;
	private boolean explored;
	private int exploreFinishTime;
	private Integer leader;
	private ArrayList<TwoSatVertex> connected_vertices;
	private int neg_vertex;
	
	public TwoSatVertex(int l)
	{
		this.label = l;
		this.fixedVarId = l;
		this.explored = false;
		this.exploreFinishTime = -1;
		this.leader = null;
		this.connected_vertices = new ArrayList<TwoSatVertex>();
		this.neg_vertex = -l;
	}
	
	public TwoSatVertex(TwoSatVertex v)
	{
		this.label = v.label;
		this.fixedVarId = v.GetFixedVarId();
		this.explored = v.explored;
		this.exploreFinishTime = v.exploreFinishTime;
		this.connected_vertices = new ArrayList<TwoSatVertex>(v.connected_vertices);
		this.neg_vertex = v.GetNegVertexId();
	}
	
	public int GetFixedVarId()
	{
		return this.fixedVarId;
	}
	
	public void SetFixedVarId(int i)
	{
		this.fixedVarId = i;
	}
	
	public int GetNegVertexId()
	{
		return this.neg_vertex;
	}
	
	public void SetNegVertexId(int id)
	{
		this.neg_vertex = id;
	}
	
	public void AddEndTwoSatVertex(TwoSatVertex v)
	{
		this.connected_vertices.add(v);
	}
	
	public int EndTwoSatVertexNum()
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
	
	public Integer GetLeader()
	{
		return this.leader;
	}
	
	public void SetLeader(Integer v)
	{
		this.leader = v;
	}
	
	public ArrayList<TwoSatVertex> GetEndVertices()
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
		//System.out.print("Finish time : " + this.GetExploredFinishTime() + " ");
		System.out.println();
	}


	@Override
	public int compareTo(TwoSatVertex o) {
		// TODO Auto-generated method stub
		if(this.exploreFinishTime < o.GetExploredFinishTime())
		{
			return 1;
		}
		else if(this.exploreFinishTime > o.GetExploredFinishTime())
		{
			return -1;
		}
		else
		{
			return 0;
		}		
	}
}