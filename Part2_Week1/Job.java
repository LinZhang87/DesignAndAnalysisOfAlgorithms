public class Job
{
	private int weight;
	private int length;
	private int completionTime;
	private double wl_diff;
	private double wl_ratio;
	
	public Job()
	{
		this.weight = 0;
		this.length = 0;
		this.completionTime = 0;
		this.wl_diff = 0.0;
		this.wl_ratio = 0.0;
	}
	
	public Job(int w, int l)
	{
		this.weight = w;
		this.length = l;
		this.completionTime = l;
		this.wl_diff = (double)this.weight - (double)this.length;
		this.wl_ratio = ((double)this.weight) / ((double)this.length);
	}
	
	public double GetWLDiff()
	{
		return this.wl_diff;
	}
	
	public double GetWLRatio()
	{
		return this.wl_ratio;
	}
	
	public int GetWeight()
	{
		return this.weight;
	}
	
	public int GetLength()
	{
		return this.length;
	}
	
	public void SetWeight(int w)
	{
		this.weight = w;
	}
	
	public void SetLength(int l)
	{
		this.length = l;
	}
	
	public int GetCompletionTime()
	{
		return this.completionTime;
	}
	
	public void SetCompletionTime(int c)
	{
		this.completionTime = c;
	}
}