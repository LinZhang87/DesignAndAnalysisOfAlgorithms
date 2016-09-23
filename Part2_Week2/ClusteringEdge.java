public class ClusteringEdge implements Comparable
{
	private ClusteringVertex v1;
	private ClusteringVertex v2;
	private int cost;
	
	public ClusteringEdge()
	{
		this.v1 = null;
		this.v2 = null;
		this.cost = -1;
	}
	
	public ClusteringEdge(ClusteringVertex v1, ClusteringVertex v2, int w)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.cost = w;
	}
	
	public ClusteringVertex GetVertex1()
	{
		return this.v1;
	}
	
	public ClusteringVertex GetVertex2()
	{
		return this.v2;
	}
	
	public int GetCost()
	{
		return this.cost;
	}
	
	public void SetCost(int w)
	{
		this.cost = w;
	}	
	
	public void Display()
	{
		System.out.print(this.v1.GetLabel() + "--" + this.v2.GetLabel() + " cost: " + this.cost + "   ");
	}

	@Override
	public int compareTo(Object o) {
		ClusteringEdge e = (ClusteringEdge) o;
		return this.cost - e.GetCost();
	}
}