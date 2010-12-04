public class Point implements Comparable<Point>
{
	private final static int spread = 50;
	public int X,
			   Y;

	public Point()
	{
		java.util.Random r = new java.util.Random();
		X = r.nextInt(2*spread+1)-spread;
		Y = r.nextInt(2*spread+1)-spread;
	}
	
	public Point(int X, int Y)
	{
		this.X = X;
		this.Y = Y;
	}

	public void swap(Point p)
	{
		if(!equals(p))
		{
			X ^= p.X;
			p.X ^= X;
			X ^= p.X;

			Y ^= p.Y;
			p.Y ^= Y;
			Y ^= p.Y;
		}
	}
	
	@Override
	public int compareTo(Point p)
	{
		if(X == p.X) 
			return Y - p.Y;
		else 
			return X - p.X;
	}
	
	@Override
	public boolean equals(Object obj)
	{
		try
		{
			return ((Point)obj).X == X && ((Point)obj).Y == Y;
		}
		catch(ClassCastException e)
		{
			return false;
		}
	}

	@Override
	public String toString()
	{
		return "(" + X + ", " + Y + ")";
	}
}