
public class Point
{
	private int x;
	private int y;
		
	Point(int x, int y)
	{
		this.x = x;
		this.y = y;
	}
	
	Point(Point p)
	{
		this.x = p.x;
		this.y = p.y;
	}
	
	public int getX()
	{
		return this.x;
	}
	
	public int getY()
	{
		return this.y;
	}
	
	public int Distance(Point p)
	{
		return (int)Math.sqrt(Math.pow((this.x - p.x), 2) + Math.pow((this.y - p.y), 2));
	}
	
	public String toString()
	{
		return this.x + "    " + this.y;
	}
}