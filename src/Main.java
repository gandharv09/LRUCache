
public class Main {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		LRUCacheList lruCacheList = new LRUCacheList(3);
		
		//test cache
		lruCacheList.put(1,1);
		lruCacheList.put(2,2);
		lruCacheList.put(3,3);
		lruCacheList.put(4,4);
		lruCacheList.put(5,5);
		lruCacheList.put(2,2);
		lruCacheList.put(4,4);
		
		lruCacheList.get(1);
		lruCacheList.get(3);
		lruCacheList.get(4);
		
		lruCacheList.printCurrentCache();
	}

}
