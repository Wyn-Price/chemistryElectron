package com.wynprice.chemistry;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.KeyEventDispatcher;
import java.awt.KeyboardFocusManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

@SuppressWarnings("serial")
public class Main extends JFrame
{
	
	public static void main(String[] args)
	{
		createGui();
		IsKeyPressed.main();

		for(Color color : preColors)
			colors.add(color);
		for(String s : elementNames)
			elementNameArray.add(s);
		for(String s : elementSymbols)
			elementSymbolArray.add(s);
		for(double d : elementMass)
			elementMassArray.add(d);
	}
	
	public Main()
	{
		super("Enter Atomic Number");
		main = this;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	private static int zoomIndex = 20;
	public static ArrayList<Integer> sizes = new ArrayList<Integer>();
	public static ArrayList<Integer> electrons = new ArrayList<Integer>();
	public static Main main;
	public static ArrayList<String> preString = new ArrayList<String>();
	private static String[] elementNames = {"hydrogen","helium","lithium","beryllium","boron","carbon","nitrogen",
		"oxygen","fluorine","neon","sodium","magnesium","aluminum","silicon","phosphorus","sulfur","chlorine",
		"argon","potassium","calcium","scandium","titanium","vanadium","chromium","manganese","iron","cobalt",
		"nickel","copper","zinc","gallium","germanium","arsenic","selenium","bromine","krypton","rubidium",
		"strontium","yttrium","zirconium","niobium","molybdenum","technetium","ruthenium","rhodium","palladium",
		"silver","cadmium","indium","tin","antimony","tellurium","iodine","xenon","cesium","barium","lanthanum",
		"cerium","praseodymium","neodymium","promethium","samarium","europium","gadolinium","terbium","dysprosium",
		"holmium","erbium","thulium","ytterbium","lutetium","hafnium","tantalum","tungsten","rhenium","osmium",
		"iridium","platinum","gold","mercury","thallium","lead","bismuth","polonium","astatine","radon","francium",
		"radium","actinium","thorium","protactinium","uranium","neptunium","plutonium","americium","curium",
		"berkelium","californium","einsteinium","fermium","mendelevium","nobelium","lawrencium","rutherfordium",
		"dubnium","seaborgium","bohrium","hassium","meitnerium","darmstadtium","roentgenium","copernicium",
		"nihonium","flerovium","moscovium","livermorium","tennessine","oganesson"};
	private static ArrayList<String> elementNameArray = new ArrayList<>();
	
	private static String[] elementSymbols = {"h","he","li","be","b","c","n","o","f","ne","na","mg","al","si","p",
		"s","cl","ar","k","ca","sc","ti","v","cr","mn","fe","co","ni","cu","zn","ga","ge","as","se","br","kr","rb",
		"sr","y","zr","nb","mo","tc","ru","rh","pd","ag","cd","in","sn","sb","te","i","xe","cs","ba","la","ce","pr",
		"nd","pm","sm","eu","gd","tb","dy","ho","er","tm","yb","lu","hf","ta","w","re","os","ir","pt","au","hg","tl",
		"pb","bi","po","at","rn","fr","ra","ac","th","pa","u","np","pu","am","cm","bk","cf","es","fm","md","no","lr",
		"rf","db","sg","bh","hs","mt","ds","rg","cn","nh","fl","mc","lv","ts","og"};
	private static ArrayList<String> elementSymbolArray = new ArrayList<>();

	private static double[] elementMass = {1.00794D, 4.002602D, 6.941D, 9.012182D, 10.811D, 12.0107D, 14.00674D, 15.9994D,
			18.9984032D, 20.1797D, 22.989770D, 24.3050D, 26.981538D, 28.0855D, 30.973761D, 32.066D, 35.4527D, 39.948D, 39.0983D,
			40.078D, 44.955910D, 47.867D, 50.9415D, 51.9961D, 54.938049D, 55.845D, 58.933200D, 58.6934D, 63.546D, 65.39D, 69.723D,
			72.61D, 74.92160D, 78.96D, 79.904D, 83.80D, 85.4678D, 87.62D, 88.90585D, 91.224D, 92.90638D, 95.94D, 98D, 101.07D,
			102.90550D, 106.42D, 107.8682D, 112.411D, 114.818D, 118.710D, 121.760D, 127.60D, 126.90447D, 131.29D, 132.90545D,
			137.327D, 138.9055D, 140.116D, 140.90765D, 144.24D, 145D, 150.36D, 151.964D, 157.25D, 158.92534D, 162.50D, 164.93032D,
			167.26D, 168.93421D, 173.04D, 174.967D, 178.49D, 180.9479D, 183.84D, 186.207D, 190.23D, 192.217D, 195.078D, 196.96655D,
			200.59D, 204.3833D, 207.2D, 208.98038D, 209D, 210D, 222D, 223D, 226D, 227D, 232.0381D, 231.03588D, 238.0289D, 237D, 244D,
			243D, 247D, 247D, 251D, 252D, 257D, 258D, 259D, 262D, 261D, 262D, 263D, 262D, 265D, 266D, 269D, 272D, 277D, 286D, 289D,
			288D, 292D, 294D, 294D};
	private static ArrayList<Double> elementMassArray = new ArrayList<>();

	public static void repaint(int position, int ele)
	{
		sizes.add((int) ((position + 1) * (60 * (zoomIndex / 20D))));
		electrons.add(ele);
	}
	
	
	private static Color[] preColors = {Color.RED, Color.ORANGE, new Color(0x1c681d), new Color(0x1813ad), Color.PINK};
	public static ArrayList<Color> colors = new ArrayList<Color>();
	
	
	 @Override
	public void paint(Graphics g) {
		 super.paint(g);
		 if(sizes.isEmpty())
			 return;
		 for(int i = 1; i < sizes.size(); i++)
		 {
			 g.setColor(Color.BLACK);
			 ArrayList<Integer> colorChanges = new ArrayList<Integer>();
			 colorChanges.add(2);
			 colorChanges.add(8);
			 g.drawOval((g.getClipBounds().width / 2) - 300 - (sizes.get(i) / 2), (g.getClipBounds().height / 2) - (sizes.get(i) / 2), sizes.get(i) * 1, sizes.get(i) * 1);
			 if(perShellRaw.length >= i && zoomIndex > 5)
				 g.drawString(perShellRaw[i - 1], (g.getClipBounds().width / 2) - 299 + (sizes.get(i) / 2), (g.getClipBounds().height / 2) + 15);
			 int l = 0;
			 for(int k = 0; k < electrons.get(i); k++)
			 {
				 if(l == 0)
					 g.setColor(Color.RED);
				 if(k >= colorChanges.get(l))
				 {
					 l++;
					 colorChanges.add(colorChanges.get(colorChanges.size() - 1) + 6 + (l*4));
					 if(l == colors.size())
						 addNewColor();
					 g.setColor(colors.get(l));
				 }
				 g.fillOval((int) Math.floor(sizes.get(i) / 2 * Math.cos(((Math.PI*2) / electrons.get(i)) * k) + (g.getClipBounds().width / 2) - 302.5d), 
						 (int) Math.floor(sizes.get(i) / 2 * Math.sin(((Math.PI*2) / electrons.get(i)) * k) +  (g.getClipBounds().height / 2d) - 2.5d), 7, 7);
			 }
				 
		 }
		 sizes.clear();
		 electrons.clear();
		 sizes.add((int) (60 * (zoomIndex / 20D)));
		 electrons.add(0);
	 }
	 
	 private static Random rand = new Random(45707867644L);
	 public static void addNewColor()
	 {
		 colors.add(new Color(rand.nextFloat(), rand.nextFloat(), rand.nextFloat()));
	 }

	private static JTextField textInput;
	private static JLabel outputText;
	private static JLabel elementName;
	private static Main frame;
	private static String fText = "";
	private static JButton button;
	
	
	private static void createGui()
	{
		frame = new Main();
		textInput = new JTextField();
		outputText = new JLabel("", SwingConstants.RIGHT);
		elementName = new JLabel("", SwingConstants.CENTER);
		button = new JButton("<html>Click to submit</html>");
		JPanel panel = new JPanel();
		
		frame.add(textInput);
		frame.add(outputText);
		frame.add(elementName);
		frame.add(button);
		frame.setVisible(true);
		frame.pack();
		panel.add(textInput);
		frame.add(panel);

		frame.setSize(new Dimension(1200, 600));
		textInput.setPreferredSize(new Dimension(120, 30));
		button.addActionListener(new ActionListener() { 
		    public void actionPerformed(ActionEvent e) { 
		    	calc();
		    } 
		});
		frame.addComponentListener(new ComponentListener() {
			
			public void componentHidden(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {				
			}

			public void componentResized(ComponentEvent e) {
				outputText.setSize(new Dimension(frame.getSize().width + 100, frame.getSize().height));
				outputText.setLocation(-200, 0);
				elementName.setSize(frame.getWidth(), 150);
				elementName.setLocation(0, 120);
				button.setLocation(frame.getSize().width / 2 - 75, frame.getSize().height - 90);
				button.setSize(150,50);
				resizeTest();
				try {
					Thread.sleep(5);
				} catch (InterruptedException e1) {
					;
				}
				calc();
			}

			public void componentShown(ComponentEvent e) {				
			}
		});

	}
	

	public static void zoom(boolean isIn)
	{
		zoomIndex += isIn ? -1 : 1;
		if(zoomIndex < 1)
			zoomIndex = 1;
		Main.calc();
	}
	
	private static String[] perShellRaw;
	
	public static void calc()
	{
		int electrons = 0;
		if(elementNameArray.contains(textInput.getText().toLowerCase()))
			electrons = elementNameArray.indexOf(textInput.getText().toLowerCase())+1;
		else if(elementSymbolArray.contains(textInput.getText().toLowerCase()))
			electrons = elementSymbolArray.indexOf(textInput.getText().toLowerCase())+1;
		else
			try
			{
				electrons = Integer.parseInt(textInput.getText());
			}
			catch (NumberFormatException e) {
				outputText.setText("<html>Error: '" + textInput.getText() + "' is not a number</html>");
				return;
			}
		if(electrons <= 0 )
		{
			outputText.setText("<html>Error: '" + textInput.getText() + "' is not an accepted number</html>");
			return;
		}
		fText = new Atom(electrons).structure();
		perShellRaw = fText.split("@")[1].split(" ");
		fText = fText.split("@")[0];
		if(electrons - 1 < elementNameArray.size())
			elementName.setText("<html><u><p align=\"center\">" + capatilizeFirstLetter(elementNameArray.get(electrons - 1)) + 
					"</u><br><p align=\"center\">Symbol:  " + capatilizeFirstLetter(elementSymbolArray.get(electrons - 1)) + 
					"</p><br><p align=\"center\">Mass Number:  " + elementMassArray.get(electrons - 1)  + "</html>");
		else
			elementName.setText("");
		resizeTest();
		
		main.repaint();		

	}
		
	private static String capatilizeFirstLetter(String name)
	{
		return name.substring(0, 1).toUpperCase() + name.substring(1).toLowerCase();
	}
	
	private static void resizeTest()
	{
		String st = "";
		int num = fText.startsWith("s") ? 3 : 25;
		long n = Math.round(frame.getSize().getWidth() / num);
		String[] splitString =  fText.split(", ");
		if(splitString.length > n)
			for(int i = 0; i < splitString.length; i++)
				if(i == splitString.length-1) st += splitString[i];
				else if(i % n != 0) st += splitString[i] + ", ";
				else st += splitString[i] + ",<br>";
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
		shellNoticableChange.clear();
		shellNoticableChange.add(18);
		k = 0;
		ArrayList<Integer> j = new ArrayList<Integer>();
		int[] p = {1,3,11,19,37,55,87,119};
		for (int l : p)
			j.add(l);
		boolean extendListDone = false;
		int s = 14;
		int o = 0;
		while(!extendListDone)
			if(totalElectrons >= j.get(j.size()-1))
			{
				if(o % 2 == 1)
					s += 4;
				j.add(s + j.get(j.size()-1));
				o++;
			}
			else
				extendListDone = true;
		
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
					addF(i-1, 14);
				addD(i, 10);
				if(prev.hasF())
				{
					addF(i-1, 14);
					if(shells.get(i - 2).getMax() > 14)
						createCustomShells(shells.get(i - 2));
				}
			}
		}
		
		ArrayList<Shell> finishShell = new ArrayList<Shell>();
		for(Shell shell : shells)
			if(shell.getAdded() != 0)
				finishShell.add(shell);
		shells = finishShell;
		
	}
	
	private void createCustomShells(Shell shell) 
	{
		int k = ((shell.getMax() - 2) / 4) - 4;
		for(int j = 0; shell.customSubs.size() < k; j++)
			shell.createNewSub((j * 4) + 18);
		
		addToCustomShell(shell, shell.getMax());
	}
	
	private ArrayList<Integer> shellNoticableChange = new ArrayList<Integer>();
	int k = 0;
	
	private void addToCustomShell(Shell shell, int amount)
	{
		k++;
		if(shellNoticableChange.get(shellNoticableChange.size()-1) < amount)
			shellNoticableChange.add(amount);
		for(int i = 0; i < k; i++)
		{
			if(shells.get(shell.getPosition() - i).getMax() > shellNoticableChange.get(i))
			{
				int n;
				if(e <= shellNoticableChange.get(i))
					n = e;
				else
					n = shellNoticableChange.get(i);
				e -=shells.get(shell.getPosition() - i).addCustomSub(i, n);
			}
				
		}	
	}

	public String structure()
	{
		for(Shell s : shells)
			Main.repaint(s.getPosition(), Integer.parseInt(s.addAll()));

		return getCompStruc() + "@" + getAddedAll();
	}
	
	public String getAddedAll()
	{
		String st = "";
		for(Shell shell : shells)
			st += shell.addAll() + " ";
		return st.substring(0, st.length() - 1);
	}
	
	public String getCompStruc()
	{
		String st = "";
		for(Shell s : shells)
			st+= "<u>Shell " + s.getPosition() + "</u>: " + s.all() + "<br>";
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
	public static ArrayList<Shell> allShells = new ArrayList<Shell>();
	public ArrayList<Integer> customSubs = new ArrayList<Integer>();
	public ArrayList<Integer> customSubHolding = new ArrayList<Integer>();

	
	public int getMax()
	{
		return 6 + (shellPosition * 4);
	}
	
	public int getAdded()
	{
		int i = s + p + d + f;
		for(int j : customSubHolding)
			i += j;
		return i;
	}
	
	public Shell(int shellPosition)
	{
		this.shellPosition = shellPosition;
		allShells.add(this);
		
	}
	
	public int getPosition()
	{
		return shellPosition;
	}
	
	public String all()
	{
		ArrayList<String> fAl = new ArrayList<String>();
		ArrayList<String> alphabet = new ArrayList<String>();
		Collections.addAll(fAl, "abcdefghijklmnopqrstuvwxyz".split(""));
		Collections.addAll(alphabet, "abceghijklmnoqrtuvwxyz".split(""));
		String preffix = "";
		String suffix = "";
		int o = 0;
		char[] m = "spdf".toCharArray();
		int[]sf = {s,p,d,f};
		for(int i = 0; i < sf.length; i++)
		{
			if(sf[i] == 0)
				continue;
			if(i==0)
				suffix += "<font color='#" +Integer.toHexString(Main.colors.get(i).getRGB()).substring(2) + "'>s: " + sf[0] + "</font>";
			else
				suffix += ",&nbsp<font color='#" +Integer.toHexString(Main.colors.get(i).getRGB()).substring(2) + "'>" + m[i] + ": " + sf[i] + "</font>";
		}
		for(int i = 0; i < customSubHolding.size(); i ++)
		{
			if(customSubHolding.get(i) == 0)
				continue;
			if(alphabet.size() == i)
			{
				o++;
				ArrayList<String> w = new ArrayList<String>();
				for(int l = 0; l < fAl.size(); l++)
					w.add(String.valueOf(fAl.get(l)) + o);
				for(String s : w)
					alphabet.add(s);
			}
			if(Main.colors.size() == i + 4)
				Main.addNewColor();
			preffix += ",&nbsp<font color='#" +Integer.toHexString(Main.colors.get(i + 4).getRGB()).substring(2) + "'>" + alphabet.get(i) + ": " + customSubHolding.get(i) + "</font>";
		}
			
		
		return suffix + preffix;
	}
	
	public String addAll()
	{
		return String.valueOf(getAdded());
	}
	
	public void createNewSub(int initialLimit)
	{
		customSubs.add(initialLimit);
	}
	
	public int addCustomSub(int positionNumber, int amount)
	{
		while(customSubHolding.size() < positionNumber + 1)
			customSubHolding.add(0);
		
		customSubHolding.set(positionNumber, amount);
		return amount;
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

class IsKeyPressed {
    public static void main() {
        KeyboardFocusManager.getCurrentKeyboardFocusManager().addKeyEventDispatcher(new KeyEventDispatcher() {

            public boolean dispatchKeyEvent(KeyEvent ke) {
                synchronized (IsKeyPressed.class) {
                    switch (ke.getID()) {
                    case KeyEvent.KEY_PRESSED:
                        if (ke.getKeyCode() == KeyEvent.VK_ENTER) 
                        	Main.calc();
                        if(ke.getKeyCode() == KeyEvent.VK_UP || ke.getKeyCode() == KeyEvent.VK_DOWN)
                        	Main.zoom(ke.getKeyCode() == KeyEvent.VK_DOWN);
                        break;
                    }
                    return false;
                }
            }
        });
    }
}
