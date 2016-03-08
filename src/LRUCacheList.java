import java.util.HashMap;
import java.util.HashSet;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import javax.xml.validation.Validator;


public class LRUCacheList {

	private HashMap<Integer, LRUNode> hashMap = new HashMap();
	private HashMap<Integer, LRUNode> diskMap = new HashMap();
	private int cacheCapacity;
	private LRUNode head = null;
	private LRUNode tail = null;
	
	public LRUCacheList(int capacity){
		this.cacheCapacity = capacity;
	}
	
	public void put(int key, int val){
		synchronized(hashMap){
			if(hashMap.containsKey(key)){
				LRUNode currentNode = hashMap.get(key);
				removePage(currentNode);
				setHead(currentNode);
			}else{
				LRUNode newPage = new LRUNode(key, val);
				if(hashMap.size()==cacheCapacity){
					hashMap.remove(tail.getKey());
					removePage(tail);
					setHead(newPage);
				}else{
					hashMap.put(key, newPage);
					setHead(newPage);
				}
				
				hashMap.put(key, newPage);
				diskMap.put(key, newPage);
			}
		}
	}

	private void removePage(LRUNode node){
		if(node.prevLruNode!=null){
			node.prevLruNode.nextLruNode = node.nextLruNode;
		}else{
			head = node.nextLruNode;
		}
		
		if(node.nextLruNode!=null){
			node.nextLruNode.prevLruNode = node.prevLruNode;
		}else{
			tail = node.prevLruNode;
		}
	}

	private void setHead(LRUNode currentNode) {
		currentNode.nextLruNode = head;
		currentNode.prevLruNode = null;
		if(head!=null){
			head.prevLruNode = currentNode;
		}
		
		head = currentNode;
		
		if(tail == null){
			tail = head;
		}
	}

	public void printCurrentCache() {
		if(head != null){
			LRUNode node = head;
			while(node!=null){
				System.out.println(node.getValue());
				node = node.nextLruNode;
			}
		}
	}
	
	public int get(int key){
		synchronized(hashMap){
		int val;
		if(hashMap.containsKey(key)){
			val = hashMap.get(key).getValue();
		}else{
			val = getFromDisk(key);
		}
		put(key, val);
		return val;
		}
	}

	private int getFromDisk(int key) {
		if(diskMap.containsKey(key)){
			int val = diskMap.get(key).getValue();
			put(key, val);
			return val;
		}else{
			return -1;
		}
	}
}
