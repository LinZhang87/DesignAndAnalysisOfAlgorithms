public class DijkstraEdge
{
	private DijkstraVertex v1;
	private DijkstraVertex v2;
	private int weight;
	
	public DijkstraEdge()
	{
		this.v1 = null;
		this.v2 = null;
		this.weight = -1;
	}
	
	public DijkstraEdge(DijkstraVertex v1, DijkstraVertex v2, int w)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.weight = w;
	}
	
	public DijkstraVertex GetVertex1()
	{
		return this.v1;
	}
	
	public DijkstraVertex GetVertex2()
	{
		return this.v2;
	}
	
	public int GetWeight()
	{
		return this.weight;
	}
	
	public void SetWeight(int w)
	{
		this.weight = w;
	}	
	
	public void Display()
	{
		System.out.print(this.v1.GetLabel() + "->" + this.v2.GetLabel() + " w: " + this.weight + "   ");
	}
	
	/*public boolean equals(DijkstraEdge e)
	{
		if(e == null)
		{
			return false;
		}
		if(this.getClass() != e.getClass())
		{
			return false;
		}
		if(this.GetWeight() != e.GetWeight())
		{
			return false;
		}
		if(this.v1.GetLabel() != e.GetVertex1().GetLabel() && this.v1.GetLabel() != e.GetVertex2().GetLabel())
		{
			return false;
		}
		if(this.v2.GetLabel() != e.GetVertex1().GetLabel() && this.v2.GetLabel() != e.GetVertex2().GetLabel())
		{
			return false;
		}
		return true;
	}*/
}