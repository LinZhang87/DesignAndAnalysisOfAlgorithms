public class Item implements Comparable<Item>
{
	private int id;
	private int value;
	private int weight;
	
	public Item(int id, int v, int w)
	{
		this.id = id;
		this.value = v;
		this.weight = w;
	}
	
	public int GetId()
	{
		return this.id;
	}
	
	public int GetValue()
	{
		return this.value;
	}
	
	public int GetWeight()
	{
		return this.weight;
	}

	@Override
	public int compareTo(Item o) {
		// TODO Auto-generated method stub
		return this.weight - o.GetWeight();
	}	
}