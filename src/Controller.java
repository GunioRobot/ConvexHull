abstract class Controller
{
	public static void main(String args[])
	{
        java.awt.EventQueue.invokeLater(new Runnable() 
        {
            public void run() 
            {
                Display d = new Display();
                d.setVisible(true);
            }
        });
	}
}