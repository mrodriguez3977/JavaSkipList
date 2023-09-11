public class Main 
{
	public static void main(String []args)
	{
		Integer[] base = {17, 31, 45, 50, 63, 85, 96};
		SkipListEntry[] entries = new SkipListEntry[base.length];
		for(int i = 0; i < base.length; i++)
		{
			entries[i] = new SkipListEntry(base[i], " ", i);
		}
		SkipList s = new SkipList(entries);
		System.out.println("Original Skip List\n" + s.toString());
		s.skipInsert(24, " ");
		s.skipInsert(19, " ");
		System.out.println("List After Adding 19 and 24\n" + s.toString());
		s.skipRemove(19);
		System.out.println("Final Skip List after removing 19\n" + s.toString());
	}
}
