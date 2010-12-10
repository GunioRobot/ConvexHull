import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Display extends JFrame 
{	
	private JButton buttonNew,
					buttonHull,
					buttonPointsPlus,
					buttonPointsMinus;
	private JTextField numberPoints;
	private Integer pointsBackup = 50;
	private JSeparator separator;
	private Graph graph;
	private Field field;

	public Display() 
	{
		buttonNew = new JButton("New");
		buttonHull = new JButton("Find...");
		buttonPointsMinus = new JButton("-");
		buttonPointsPlus = new JButton("+");
		numberPoints = new JTextField(new Integer(pointsBackup).toString());
		separator = new JSeparator();
		field = new Field(new Integer(numberPoints.getText()));
		graph = new Graph(field.getPoints());

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		try
		{
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		}
		catch (ClassNotFoundException e){}
		catch (InstantiationException e){}
		catch (IllegalAccessException e){}
		catch (UnsupportedLookAndFeelException e){}

		buttonNew.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				buttonNewActionPerformed(evt);
			}
		});
		buttonHull.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				buttonHullActionPerformed(evt);
			}
		});
		buttonPointsPlus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				buttonSpeedPlusActionPerformed(evt);
			}
		});
		numberPoints.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				fieldSize();
			}
		});
		buttonPointsMinus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				buttonSpeedMinusActionPerformed(evt);
			}
		});		
		
		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);
		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addComponent(buttonNew)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonHull, 70, 70, 70)
						.addGap(69, 69, 69)
						.addComponent(buttonPointsMinus)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(numberPoints, 35, 35, 35)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonPointsPlus)
						.addContainerGap(329, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(graph, GroupLayout.Alignment.TRAILING, 640, 640, 674)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(buttonPointsMinus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(numberPoints, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonPointsPlus)
								.addComponent(buttonNew)
								.addComponent(buttonHull))
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(separator, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
								.addComponent(graph, 436, 436, 448))
		);
		pack();
		setResizable(false);
	}

	private void buttonNewActionPerformed(ActionEvent evt) 
	{
		field = new Field(fieldSize());
		graph.drawPoints(field.getPoints());
	}

	private void buttonHullActionPerformed(ActionEvent evt) 
	{
		int h = field.grahamHull(0, field.getPoints().length);
        Point[] p = field.getPoints();
        for(int i = 0; i < h; i++)
        	if(i == 0)
        		graph.drawLine(new Line(p[0], p[h-1]));
        	else
        		graph.drawLine(new Line(p[i], p[i-1]));
	}
	
	private void buttonSpeedMinusActionPerformed(ActionEvent evt) 
	{ 
		if(fieldSize() > 3)
		{
			pointsBackup--;
			numberPoints.setText(pointsBackup.toString());
		}
	}
	
	private void buttonSpeedPlusActionPerformed(ActionEvent evt) 
	{
		if(fieldSize() < Integer.MAX_VALUE)
		{
			pointsBackup++;
			numberPoints.setText(pointsBackup.toString());
		}
	}
	
	private int fieldSize()
	{
		try
		{
			pointsBackup = new Integer(numberPoints.getText());
		}
		catch(NumberFormatException e)
		{
			numberPoints.setText(pointsBackup.toString());
		}
		return pointsBackup;
	}
}