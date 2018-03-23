package com.wynprice.chemistry;

import java.util.ArrayList;

public class Atom 
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
			Main.repaint(s.getPosition(), Integer.parseInt(s.addAll()), shells.size());

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
