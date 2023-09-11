import java.util.ArrayList;
import java.util.Random;

public class SkipList
{
	ArrayList<SkipListLevel> list;
	int topLevelIndex;
	
	public SkipList(SkipListEntry[] base)
	{
		list = new ArrayList<SkipListLevel>();
		SkipListEntry[] current = base;
		list.add(new SkipListLevel(base, 0.0));
		int levelIndex = 1;
		while(current.length > 1)
		{
			list.add(new SkipListLevel(current));
			SkipListEntry[] temp = new SkipListEntry[list.get(levelIndex).size() - 2];
			for(int i = 0; i < temp.length; i++)
			{
				temp[i] = list.get(levelIndex).get(i + 1);
			}
			current = temp;
			levelIndex++;
		}
		list.add(new SkipListLevel(current));
		topLevelIndex = levelIndex;
	}
	
	public int skipSearch(int k)
	{
		int currentLevelIndex = topLevelIndex;
		int currentEntryIndex = 1;
		while(currentLevelIndex  > 0 && currentEntryIndex < list.get(currentLevelIndex).size())
		{
			if(list.get(currentLevelIndex).get(currentEntryIndex).getKey() == k)
			{
				return list.get(currentLevelIndex).get(currentEntryIndex).index + 1;
			}
			
			if(currentEntryIndex == list.get(currentLevelIndex).size() - 1)
			{
				currentEntryIndex--;
				currentLevelIndex--;
				//System.out.println("Went here");
			}
			else
				currentEntryIndex++;
		}
		for(int i = 0; i < list.get(currentLevelIndex).size(); i++)
		{
			//System.out.println("Last level index " + i);
			//System.out.println("val : " + list.get(currentLevelIndex).get(i).getKey());
			if(list.get(currentLevelIndex).get(i).getKey() == k)
			{
				return list.get(currentLevelIndex).get(i).index;
			}
		}
		return -1;
	}
	
	public void skipInsert(int k, String v)
	{
		//good
		if(skipSearch(k) != -1)
		{
			System.out.println("Element already in list");
			for(int i = list.size() - 1; i >= 0; i--)
				list.get(i).get(skipSearch(k)).setValue(v);
			return;
		}
		
		int currentLevelIndex = topLevelIndex;
		int currentEntryIndex = 1;
		
		while(currentLevelIndex > 0 && currentEntryIndex < list.get(currentLevelIndex).size())
		{
			if(list.get(currentLevelIndex).get(currentEntryIndex).getKey() > k)
			{
				//System.out.println("Found place for it at level: " + currentLevelIndex + " Position: " + list.get(currentLevelIndex).get(currentEntryIndex).index);
				currentEntryIndex = list.get(currentLevelIndex).get(currentEntryIndex).index;
				currentLevelIndex = 0;
				while(currentEntryIndex >= list.get(currentLevelIndex).size())
				{
					currentEntryIndex--;
				}
				while(list.get(currentLevelIndex).get(currentEntryIndex).getKey() > k)
				{
					currentEntryIndex--;
				}
				list.get(currentLevelIndex).addAt(currentEntryIndex + 1, new SkipListEntry(k, v, currentEntryIndex));
				Random rand = new Random();
				int n = currentLevelIndex + 1;
				SkipListEntry e = new SkipListEntry(k, v, currentEntryIndex);
				shiftAfter(list.get(currentLevelIndex).get(currentEntryIndex).index);
				while(n != topLevelIndex)
				{
					if(rand.nextDouble() > 0.5)
					{
						for(int i = 1; i < list.get(n).size() - 1; i++)
						{
							
							if(list.get(n).get(i).getKey() > k)
							{
								list.get(n).addAt(i, e);
								while(list.get(n).get(i).getKey() > k)
								{
									i--;
								}
								n++;
							}
						}
					}
					else
						break;
				}
				return;
			}
			
			if(currentEntryIndex == list.get(currentLevelIndex).size() - 1)
			{
				currentEntryIndex--;
				currentLevelIndex--;
				//System.out.println("Moved down a level");
			}
			else
				currentEntryIndex++;
		}
	}
	
	public SkipListEntry skipRemove(int k)
	{
		SkipListEntry test = new SkipListEntry(k, "", 0);
		if(skipSearch(k) == -1)
		{
			System.out.println("Element not in list");
			return null;
		}
		int currentEntryIndex = skipSearch(k);
		SkipListEntry e = null;
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = 0; j < list.get(i).size() - 1; j++)
			{
				if(list.get(i).get(j).getKey() == test.getKey())
				{
					e = list.get(i).removeAt(j);
				}
			}
		}
		lowerAfter(currentEntryIndex);
		return e;
	}
	
	public void lowerAfter(int n)
	{
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = 0; j < list.get(i).size(); j++)
			{
				if(list.get(i).get(j).index > n)
				{
					list.get(i).get(j).index--;
				}
			}
		}
	}
	
	public void shiftAfter(int n)
	{
		/*for(int i = n; i < list.get(0).size(); i++)
		{
			list.get(0).get(i).index++;
		}*/
		
		for(int i = 0; i < list.size(); i++)
		{
			for(int j = 0; j < list.get(i).size(); j++)
			{
				if(list.get(i).get(j).index > n)
				{
					list.get(i).get(j).index++;
				}
			}
		}
	}
	
	public String toString()
	{
		String s = "";
		for(int i = 0; i < list.size(); i++)
		{
			s += list.get(i).toString() + "\n";
		}
		return s;
	}
}
