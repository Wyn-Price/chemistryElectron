package com.wynprice.chemistry;

import java.util.ArrayList;
import java.util.Collections;

public class Shell 
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
