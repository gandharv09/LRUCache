
public class LRUNode {
	
	private int nodeValue;
	private int key;
	LRUNode prevLruNode;
	LRUNode nextLruNode;
	
	public LRUNode(int key, int value) {
		this.nodeValue = value;
		this.key = key;
	}

	public int getKey(){
		return this.key;
	}
	
	public int getValue(){
		return this.nodeValue;
	}
}