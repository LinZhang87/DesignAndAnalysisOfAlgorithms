public class PrimMSTEdge
{
	private PrimMSTVertex v1;
	private PrimMSTVertex v2;
	private int weight;
	
	public PrimMSTEdge()
	{
		this.v1 = null;
		this.v2 = null;
		this.weight = -1;
	}
	
	public PrimMSTEdge(PrimMSTVertex v1, PrimMSTVertex v2, int w)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.weight = w;
	}
	
	public PrimMSTVertex GetVertex1()
	{
		return this.v1;
	}
	
	public PrimMSTVertex GetVertex2()
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
}