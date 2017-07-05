package com.wynprice.chemistry;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.InputEvent;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.UIManager;

public class Main {
	
	public static void main(String[] args)
	{
		createGui();
	}
	
	private static JTextField textInput;
	private static JLabel outputText;
	private static JFrame frame;
	private static String fText = "";
	
	
	private static void createGui()
	{
		frame = new JFrame("Enter Atomic Number");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		textInput = new JTextField();
		outputText = new JLabel("", SwingConstants.CENTER);
		String spacing = "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp ";
		JButton button = new JButton("<html>" + spacing + "Click to submit<br>Shift + Click for infomation per shell</html>");
		JPanel panel = new JPanel();
		
		frame.add(textInput);
		frame.add(button);
		frame.pack();
		frame.add(outputText);
		frame.setVisible(true);
		
		panel.add(textInput);
		frame.add(panel);

		frame.setSize(new Dimension(900, 600));
		textInput.setPreferredSize(new Dimension(100, 30));
		
		outputText.setBackground(UIManager.getColor ( "Panel.background" ));
		
		
		button.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	calc((e.getModifiers() & InputEvent.SHIFT_MASK) != 0);
		    } 
		});
		frame.addComponentListener(new ComponentListener() {
			@Override
			public void componentHidden(ComponentEvent e) {
			}

			@Override
			public void componentMoved(ComponentEvent e) {				
			}

			@Override
			public void componentResized(ComponentEvent e) {
				outputText.setSize(frame.getSize());
				button.setLocation(frame.getSize().width / 2 - 125, frame.getSize().height - 90);
				resizeTest();
			}

			@Override
			public void componentShown(ComponentEvent e) {				
			}
		});

	}
	
	private static void calc(boolean isShift)
	{
		int electrons = 0;
		try
		{
			electrons = Integer.parseInt(textInput.getText());
		}
		catch (NumberFormatException e) {
			outputText.setText("<html>Error: '" + textInput.getText() + "' is not a number</html>");
			return;
		}
		if(electrons < 0 )
		{
			outputText.setText("<html>Error: '" + textInput.getText() + "' is not a number</html>");
			return;
		}
		fText = isShift ? new Atom(electrons).getCompStruc() : new Atom(electrons).getNormStruc();
		resizeTest();
	}
	
	private static void resizeTest()
	{
		String st = "";
		int num = fText.startsWith("s") ? 3 : 25;
		long n = Math.round(frame.getSize().getWidth() / num);
		List<String> splitString =  Arrays.asList(fText.split(", "));
		if(splitString.size() > n)
			for(int i = 0; i < splitString.size(); i++)
				if(i == splitString.size()-1) st += splitString.get(i);
				else if(i % n != 0) st += splitString.get(i) + ", ";
				else st += splitString.get(i) + ",<br>";
		else st = fText;
		outputText.setText("<html>" + st + "</html>");
	}
}

class Atom 
{
	private ArrayList<Shell> shells = new ArrayList<Shell>();
	private int e;
	public Atom(int totalElectrons)
	{
		
		ArrayList<Integer> j = new ArrayList<Integer>(Arrays.asList(1,3,11,19,37,44,87,119));
		boolean extendListDone = false;
		while(!extendListDone)
		{
			if(totalElectrons >= j.get(j.size()-1))
				j.add(32 + j.get(j.size()-1));
			else
				extendListDone = true;
		}
			
		for(int i = 0; i < j.size(); i ++)
		{
			if(totalElectrons >= j.get(i))
				shells.add(new Shell(i));
		}	
		e = totalElectrons;
		for(int i = 0; i < shells.size(); i ++)
		{
			simpleAdd(i);
			if(i+1==shells.size() || i < 2 || totalElectrons < 21)
				continue;
			addS(i+1,2);
			Shell prev = shells.get(i - 1);
			Shell next = shells.get(i + 1);
			if(next.s == 2)
			{
				if(prev.hasF())
				{
					addD(i, 1);
					addF(i-1, 14);
					addD(i, 13);
				}
				else
					addD(i, 10);
			}
		}
		
		
	}
	
	public String getNormStruc()
	{
		String st = "";
		for(Shell s : shells)
			st+= s.addAll() + ", ";
		return st.substring(0, st.length() - 2);
	}
	
	public String getCompStruc()
	{
		String st = "";
		String spacing = "&nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp &nbsp ";
		for(Shell s : shells)
			st += "<u>Shell " + s.getPosition() + "</u>: " + s.all() + ", " + (s.getPosition() % 3 == 2? "<br>" : spacing);

		return st;
	}
	
	private void simpleAdd(int shellPosition)
	{
		addS(shellPosition,2);
		addP(shellPosition,6);
	}
	
	private void addS(int shellPosition, int addative)
	{
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addS(num);
		e -= num;		
	}
	
	private void addP(int shellPosition, int addative)
	{
		if(shellPosition == 0)
			return;
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addP(num);
		e -= num;		
	}
	
	private void addD(int shellPosition, int addative)
	{
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addD(num);
		e -= num;	
	}
	
	private void addF(int shellPosition, int addative)
	{
		int num = e >= addative? addative : e;
		e += shells.get(shellPosition).addF(num);
		e -= num;	
	}
}
class Shell 
{
	private final int shellPosition;
	public int s=0, p=0, d=0, f=0;
	
	public Shell(int shellPosition)
	{
		this.shellPosition = shellPosition;
	}
	
	public int getPosition()
	{
		return shellPosition;
	}
	
	public String all()
	{
		return "s: " + s + "&nbsp p: " + p + "&nbsp d: " + d + "&nbsp f: " + f;
	}
	
	public String addAll()
	{
		return String.valueOf(s+p+d+f);
	}
	
	public int addS(int s)
	{
		this.s += s;
		if(this.s > 2)
		{
			int r = this.s - 2;
			this.s = 2;
			return r;
		}
		return 0;
	}
	
	public int addP(int p)
	{
		this.p += p;
		if(this.p > 6)
		{
			int r = this.p - 6;
			this.p = 6;
			return r;
		}
		return 0;
	}

	public int addD(int d)
	{
		this.d += d;
		if(this.d > 10)
		{
			int r = this.d - 10;
			this.d = 10;
			return r;
		}
		return 0;
	}
	
	public int addF(int f)
	{
		this.f += f;
		if(this.f > 14)
		{
			int r = this.f - 14;
			this.f = 14;
			return r;
		}
		return 0;
	}
	
	public Boolean hasF()
	{
		return shellPosition > 2;
	}
	
}
