public class APSPEdge
{
	private APSPVertex v1;
	private APSPVertex v2;
	private float cost;
	
	public APSPEdge(APSPVertex v1, APSPVertex v2, float w)
	{
		this.v1 = v1;
		this.v2 = v2;
		this.cost = w;
	}
	
	public APSPVertex GetVertex1()
	{
		return this.v1;
	}
	
	public APSPVertex GetVertex2()
	{
		return this.v2;
	}
	
	public float GetCost()
	{
		return this.cost;
	}
	
	public void SetCost(float w)
	{
		this.cost = w;
	}	
}