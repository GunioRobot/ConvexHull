import java.util.Arrays;

public class Field
{ 
	private Point[] allPoints;
	
	public Field(int n)
	{
		allPoints = new Point[n];
		for(int i = 0; i < n; i++)
			allPoints[i] = new Point();
	}
	
	public int GRAHAM_INPLACE_SCAN(int S, int n, int d)
	{
		GRAHAM_INPLACE_SORT(S, n, d);
		int h = 1;
		for(int i = 1; i < n; i++)
		{ 
			while(h >= 2 && !right_turn(allPoints[S + h-2], allPoints[S + h-1], allPoints[S + i]))
				h--;
			allPoints[S + h].swap(allPoints[S + i]);
			h++;
		}
		return h;
	}
	
	public void GRAHAM_INPLACE_SORT(int S, int n, int d)
	{
		//Smoothsort.sort(allPoints, S, n);
		Arrays.sort(allPoints,S,S+n);
		if(d == -1)
		{
			System.out.println(Arrays.toString(allPoints));
			for(int left=S, right=S+n-1; left < right; left++, right--) 
				allPoints[left].swap(allPoints[right]);
			System.out.println(Arrays.toString(allPoints));
		}
	}
	
	public int GRAHAM_INPLACE_HULL(int S, int n)
	{
		int h = GRAHAM_INPLACE_SCAN(S, n, 1);
		for(int i = 0; i <= h-2; i++)
			allPoints[S + i].swap(allPoints[S + h]);
		int i = GRAHAM_INPLACE_SCAN(S + h-2, n - h+2, -1);
		return h + i - 2;
	}
	
	public boolean right_turn(Point a, Point b, Point c)
	{
		return (b.X - a.X)*(c.Y - a.Y) - (b.X - a.Y)*(c.X - a.Y) > 0;
	}
	
	public Point[] getPoints()
	{
		return allPoints;
	}
	
	@Deprecated
	public void test()
	{
		allPoints[0].swap(allPoints[1]);
	}
	/*
	public Point[] findHull(Point[] points)
	{
		int n = points.length;
		Arrays.sort(points);
		Point[] ans = new Point[2*n];		// In between we may have a 2n points
		int k = 0;
		int start = 0;						// start is the first insertion point


		for(int i = 0; i < n; i++)			// Finding lower layer of hull
		{
			Point p = points[i];
			while(k - start >= 2 && p.minus(ans[k-1]).cross(p.minus(ans[k-2])) > 0)
				k--;
			ans[k++] = p; 
		}

		k--;								// drop off last point from lower layer
		start = k ;						

		for(int i = n-1; i >= 0; i--)		// Finding top layer from hull
		{
			Point p = points[i];
			while(k - start >= 2 && p.minus(ans[k-1]).cross(p.minus(ans[k-2])) > 0)
				k--;
			ans[k++] = p;
		}
		k--;								// drop off last point from top layer

		Point [] ret = new Point[k];		// ret would contain our convex hull to be returned.
		for(int i = 0; i < k; i++)
			ret[i] = ans[i];
		return ret;
	}*/
	
/*	public Set<Group> partition(int h)
	{
		int minPartitionSize = allPoints.size()/h;
		int bigGroups = allPoints.size() % h;
		Set<Group> partitions = new HashSet<Group>(minPartitionSize);
		TreeSet<Point> copy = new TreeSet<Point>();
		copy.addAll(allPoints);
		for(int i = 0; i < h; i++)
		{
			Group g = new Group();
			partitions.add(g);
			if(bigGroups != 0)
			{
				for(int j = 0; j <= minPartitionSize; j++)
				{
					if(!copy.isEmpty())
					{
						g.add(copy.first());
						copy.remove(copy.first());
					}
				}
				bigGroups--;
			}
			else
				for(int j = 0; j < minPartitionSize; j++)
				{
					if(!copy.isEmpty())
					{
						g.add(copy.first());
						copy.remove(copy.first());
					}
				}
		}
		return partitions;
	}*/
}
