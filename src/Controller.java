import java.util.Arrays;

abstract class Controller
{
	public static void main(String args[])
	{
		/*Field f = new Field(6);
		int h = 3;
		Set<Group> partitions = f.partition(h);
		for(Group a : partitions)
		{
			System.out.println(a.toString());
		}*/
		//final Display d;// = new Display();
		//int a = 5; 
		//int b = ~a;
		//System.out.println(b);
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                Display d = new Display();
                d.setVisible(true);
                //d.prepare(6);
                //Point[] allPoints = f.getPoints();
            }
        });/*
		Field f = new Field(10);
		System.out.println(Arrays.toString(f.getPoints()));
		System.out.println(f.GRAHAM_INPLACE_SCAN(0, f.getPoints().length, 1));
		//System.out.println(f.GRAHAM_INPLACE_HULL(0, f.getPoints().length));
		System.out.println(Arrays.toString(f.getPoints()));*/
	}
}
