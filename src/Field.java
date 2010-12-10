import java.util.Arrays;

public class Field
{ 
	private final Point[] allPoints;
	
	public Field(int n)
	{
		allPoints = new Point[n];
		for(int i = 0; i < n; i++)
			allPoints[i] = new Point();
	}
	
	private int grahamScan(int S, int n, int d)
	{
		grahamSort(S, n, d);
		int h = 1;
		for(int i = 1; i < n; i++)
		{ 
			while(h >= 2 && isClockwise(allPoints[S + h-2], allPoints[S + h-1], allPoints[S + i]))
				h--;
			allPoints[S + h].swap(allPoints[S + i]);
			h++;
		}
		return h;
	}
	
	private void grahamSort(int S, int n, int d)
	{
		Arrays.sort(allPoints,S,S+n);
		if(d == -1)
			for(int left=S, right=S+n-1; left < right; left++, right--) 
				allPoints[left].swap(allPoints[right]);
	}
	
	public int grahamHull(int S, int n)
	{
		int h = grahamScan(S, n, 1);
		for(int i = 0; i < h-1; i++)
			allPoints[S + i].swap(allPoints[S + i + 1]);
		int i = grahamScan(S + h-2, n - h+2, -1);
		return h + i - 2;
	}
	
	private boolean isClockwise(Point a, Point b, Point c)
	{
		return (b.X - a.X)*(c.Y - a.Y) - (b.Y - a.Y)*(c.X - a.X) < 0;
	}
	
	public Point[] getPoints()
	{
		return allPoints;
	}
}