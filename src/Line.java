public class Line
{
	public final int x1,
					 y1,
					 x2,
					 y2;
		
	public Line(Point p1, Point p2)
	{
		x1 = p1.X;
		y1 = p1.Y;
		x2 = p2.X;
		y2 = p2.Y;
	}
}
