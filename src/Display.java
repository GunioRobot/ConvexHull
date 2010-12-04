import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.GroupLayout;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.JToggleButton;
import javax.swing.LayoutStyle;
import javax.swing.UIManager;
import javax.swing.UnsupportedLookAndFeelException;
import javax.swing.WindowConstants;

@SuppressWarnings("serial")
public class Display extends JFrame 
{
	private final static int size = 50;
	
	private JToggleButton buttonPlayPause;
	private JButton buttonNew, 
					buttonSpeedPlus,
					buttonSpeedMinus;
	private JTextField settingSpeed;
	private JSeparator separator;
	private Graph graph;
	private String oldSpeed = "1.0";
	private Field field;

	public Display() 
	{
		buttonPlayPause = new JToggleButton("Play", false);
		buttonNew = new JButton("New");
		buttonSpeedMinus = new JButton("-");
		buttonSpeedPlus = new JButton("+");
		settingSpeed = new JTextField(oldSpeed);
		separator = new JSeparator();
		field = new Field(size);
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
		buttonPlayPause.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				buttonPlayPauseActionPerformed(evt);
			}
		});
		buttonSpeedPlus.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				buttonSpeedPlusActionPerformed(evt);
			}
		});
		settingSpeed.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent evt) 
			{
				settingSpeedActionPerformed(evt);
			}
		});
		buttonSpeedMinus.addActionListener(new ActionListener() 
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
						.addComponent(buttonPlayPause, 70, 70, 70)
						.addGap(69, 69, 69)
						.addComponent(buttonSpeedMinus)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(settingSpeed, 35, 35, 35)
						.addPreferredGap(LayoutStyle.ComponentPlacement.RELATED)
						.addComponent(buttonSpeedPlus)
						.addContainerGap(329, Short.MAX_VALUE))
						.addComponent(separator, GroupLayout.Alignment.TRAILING, GroupLayout.DEFAULT_SIZE, 640, Short.MAX_VALUE)
						.addComponent(graph, GroupLayout.Alignment.TRAILING, 640, 640, 674)
		);
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING)
				.addGroup(layout.createSequentialGroup()
						.addGroup(layout.createParallelGroup(GroupLayout.Alignment.BASELINE)
								.addComponent(buttonSpeedMinus, GroupLayout.PREFERRED_SIZE, 25, GroupLayout.PREFERRED_SIZE)
								.addComponent(settingSpeed, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
								.addComponent(buttonSpeedPlus)
								.addComponent(buttonNew)
								.addComponent(buttonPlayPause))
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
		generatePoints();
	}

	private void buttonPlayPauseActionPerformed(ActionEvent evt) 
	{
		if(buttonPlayPause.getText().equals("Play"))
			buttonPlayPause.setText("Pause");
		else
			buttonPlayPause.setText("Play");
		
		int h = field.grahamHull(0, field.getPoints().length);
        Point[] p = field.getPoints();
        for(int i = 0; i < h; i++)
        {
        	if(i == 0)
        		graph.drawLine(new Line(p[0], p[h-1]));
        	else
        		graph.drawLine(new Line(p[i], p[i-1]));
        }
	}
	
	private void buttonSpeedMinusActionPerformed(ActionEvent evt) 
	{
		Float f = new Float(oldSpeed);
		if(f <= 1f)
			oldSpeed = f == 0f ? oldSpeed : formatSpeed(f - .05f);
		else
			oldSpeed = formatSpeed(f.intValue() - 1); 
		settingSpeed.setText(oldSpeed);
	}
	
	private void buttonSpeedPlusActionPerformed(ActionEvent evt) 
	{
		Float f = new Float(oldSpeed);
		if(f >= 1f && f < 99f)
			oldSpeed = formatSpeed(f.intValue() + 1);
		else
			oldSpeed = formatSpeed(f + .1f);
		settingSpeed.setText(oldSpeed);
	}

	private void settingSpeedActionPerformed(ActionEvent evt) 
	{
		try
		{
			float f = new Float(settingSpeed.getText());
			oldSpeed = (f >= 99.95f || f < 0f) ? oldSpeed : new Float((float)Math.round(f * 10)/10).toString();
			settingSpeed.setText(oldSpeed);
			if(oldSpeed.equals("0.0") && buttonPlayPause.getText().equals("Play"))
				buttonPlayPause.doClick();
			else if(buttonPlayPause.getText().equals("Pause"))
				buttonPlayPause.doClick();
		}
		catch(NumberFormatException e)
		{
			settingSpeed.setText(oldSpeed);
		}
	}
	
	private String formatSpeed(float f)
	{
		Float g = f;
		return g.toString().substring(0, g.toString().indexOf(".")+2);
	}
	
	private void generatePoints()
	{
		field = new Field(size);
		graph.drawPoints(field.getPoints());
	}
	
	public Field getField()
	{
		return field;
	}
}