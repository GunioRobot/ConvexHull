import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("serial")
public class Graph extends Canvas
{
	private final static double width = 674d,
								height = 448d;
	
	private Point[] points;
	private int minX,
				maxY;
	private double ratioX,
				   ratioY,
				   xAxis,
				   yAxis;
	private Set<Line> borders;
	
	public Graph(Point[] points)
	{
		this.points = points;
		setBackground(Color.WHITE);
		drawPoints();
	}
	
	public void drawLine(Line l)
	{
		borders.add(l);
		repaint();
	}

	public void paint(Graphics g)
	{
		Graphics2D canvas = (Graphics2D)g;
		canvas.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);

		canvas.setPaint(Color.BLACK);
		canvas.draw(new Line2D.Double(yAxis, 0d, yAxis, height));
		canvas.draw(new Line2D.Double(0d, xAxis, width, xAxis));
		
		canvas.setPaint(Color.RED);
		for(Point p : points)
		{
			double x = (p.X-minX)*ratioX,
				   y = (maxY-p.Y)*ratioY;
			Ellipse2D point = new Ellipse2D.Double(x-2, y-2, 4, 4);
			canvas.fill(point);
			canvas.draw(point);
		}
		for(Line l : borders)
		{
			double x1 = (l.x1-minX)*ratioX,
			   	   y1 = (maxY-l.y1)*ratioY,
				   x2 = (l.x2-minX)*ratioX,
		   	       y2 = (maxY-l.y2)*ratioY;
			canvas.draw(new Line2D.Double(x1, y1, x2, y2));
		}
	}

    public void update(Graphics g)
    {
    	Rectangle clip = g.getClipBounds();
    	Image offscreen = createImage(clip.width, clip.height);
    	Graphics canvas = offscreen.getGraphics();
    	
    	canvas.setColor(getBackground());
    	canvas.fillRect(0, 0, clip.width, clip.height);
    	canvas.setColor(getForeground());
    	canvas.translate(-clip.x, -clip.y);
    	
    	paint(canvas);
    	g.drawImage(offscreen, clip.x, clip.y, this);
    }
	
	public void drawPoints(Point[] points)
	{
		this.points = points;
		drawPoints();
	}
	
	private void drawPoints()
	{
		borders = new HashSet<Line>();
		int maxX = Integer.MIN_VALUE,
			minY = Integer.MAX_VALUE;
		minX = Integer.MAX_VALUE;
		maxY = Integer.MIN_VALUE;
		
		for(Point p : points)
		{
			if(p.X > maxX)
				maxX = p.X;
			if(p.Y > maxY)
				maxY = p.Y;
			if(p.X < minX)
				minX = p.X;
			if(p.Y < minY)
				minY = p.Y;
		}
		ratioX = width/(maxX - --minX + 1);
		ratioY = height/(++maxY - minY + 1);
		xAxis = maxY*ratioY;
		yAxis = -minX*ratioX;
		repaint();
	}
}