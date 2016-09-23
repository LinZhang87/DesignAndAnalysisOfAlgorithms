public class WeightedQuickUnionUF
{
	private int[] id;
	private int[] size;	
	private int count;	//number of components
	
	public WeightedQuickUnionUF(int N)
	{
		this.count = N;
		id = new int[N];
		for(int i = 0; i < N; i++)
		{
			id[i] = i;
		}
		this.size = new int[N];
		for(int i = 0; i < N; i++)
		{
			size[i] = 1;
		}
	}
	
	public int GetCount()
	{
		return this.count;
	}
	
	private int Find(int p)
	{
		//follow links to find a root
		while(p != id[p])
		{
			p = id[p];
		}
		return p;
	}
	
	public boolean Connected(int p, int q)
	{
		return Find(p) == Find(q);
	}
	
	public void Union(int p, int q)
	{
		int i = Find(p);
		int j = Find(q);
		if(i == j)
		{
			return;
		}
		
		//make smaller root point to larger one
		if(size[i] < size[j])
		{
			id[i] = j;
			size[j] += size[i];
		}
		else
		{
			id[j] = i;
			size[i] += size[j];
		}
		this.count--;
	}
}