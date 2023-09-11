import java.util.*;

public class SkipListLevel 
{
	private ArrayList<SkipListEntry> contents;
	
	public SkipListLevel(SkipListEntry[] base)
	{
		contents = new ArrayList<SkipListEntry>();
		contents.add(new SkipListEntry(Integer.MIN_VALUE, " ", 0));
		Random rand = new Random();
		for(int i = 0; i < base.length; i++)
		{
			double r = rand.nextDouble();
			if(r >= .5)
				contents.add(base[i]);
		}
		contents.add(new SkipListEntry(Integer.MAX_VALUE, " ", contents.size()));
	}
	
	public SkipListLevel(SkipListEntry[] base, double n)
	{
		contents = new ArrayList<SkipListEntry>();
		contents.add(new SkipListEntry(Integer.MIN_VALUE, " ", 0));
		for(int i = 0; i < base.length; i++)
		{
				contents.add(base[i]);
		}
		contents.add(new SkipListEntry(Integer.MAX_VALUE, " ", contents.size()));
	}
	
	public int size()
	{
		return contents.size();
	}
	
	public SkipListEntry get(int i)
	{
		return contents.get(i);
	}
	
	public void addAt(int i, SkipListEntry e)
	{
		contents.add(i, e);
	}
	
	public SkipListEntry removeAt(int i)
	{
		return contents.remove(i);
	}
	
	public String toString()
	{
		String s = "";
		for(int i = 0; i < contents.size(); i++)
		{
			s += contents.get(i).getKey() + " ";
		}
		return s;
	}
}
