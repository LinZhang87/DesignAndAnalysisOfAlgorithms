//import java.util.ArrayList;
import java.util.HashMap;

public class TwoSatGraph
{
	private HashMap<Integer, TwoSatVertex> TwoSatGraph;
	
	public TwoSatGraph()
	{
		this.TwoSatGraph = new HashMap<Integer, TwoSatVertex>();
	}
	
	public TwoSatGraph(TwoSatGraph g)
	{
		this.TwoSatGraph = new HashMap<Integer, TwoSatVertex>(g.TwoSatGraph);
	}
	
	public int Size()
	{
		return this.TwoSatGraph.size();
	}
	
	public HashMap<Integer, TwoSatVertex> GetTwoSatGraph()
	{
		return this.TwoSatGraph;
	}
	
	public void AddTwoSatVertex(Integer key, TwoSatVertex v)
	{
		this.TwoSatGraph.put(key, v);
	}
	
	public void Display()
	{	
		int i;
		for(i = 1; i <= (this.TwoSatGraph.size() / 2); i++)
		{
			this.TwoSatGraph.get(i).Display();
			this.TwoSatGraph.get(-i).Display();
		}
	}
}