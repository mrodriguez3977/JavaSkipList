public class SkipListEntry 
{
	private int key;
	private String value;
	public int index;
	
	public SkipListEntry(int key, String value, int index)
	{
		this.key = key;
		this.value = value;
		this.index = index;
	}
	
	public String getValue() {
		return value;
	}
	public void setValue(String value) {
		this.value = value;
	}
	public int getKey() {
		return key;
	}
	public void setKey(int key) {
		this.key = key;
	}
}
