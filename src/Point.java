public class Point implements Comparable<Point>
{
	private final static int spread = 3;
	public final double X,
						Y,
						len,
						angle;

	public Point()
	{
		java.util.Random r = new java.util.Random();
		X = (r.nextInt(2*spread+1)-spread) * r.nextDouble(); 
		Y = (r.nextInt(2*spread+1)-spread) * r.nextDouble();
		len = Math.sqrt(X*X+Y*Y);
		angle = (X >= 0 ? (Y >= 0 ? Math.atan(Y/X) : 2*Math.PI+Math.atan(Y/X)) : (Y >= 0 ? Math.PI+Math.atan(Y/X) : Math.PI+Math.atan(Y/X)));
	}
	
	public Point(double X, double Y)
	{
		this.X = X;
		this.Y = Y;
		len = Math.sqrt(X*X+Y*Y);
		angle = (X >= 0 ? (Y >= 0 ? Math.atan(Y/X) : 2*Math.PI+Math.atan(Y/X)) : (Y >= 0 ? Math.PI+Math.atan(Y/X) : Math.PI+Math.atan(Y/X)));
	}

	@Override
	public int compareTo(Point p)
	{
		if(p.X < X)
			return -1;
		if(p.X > X)
			return 1;
		return 0;
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