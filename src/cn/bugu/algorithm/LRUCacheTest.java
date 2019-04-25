package cn.bugu.algorithm;

public class LRUCacheTest {
    public static void main(String[] args) {
        LRUCache lruCache = new LRUCache(5);
        lruCache.set('A', 65);
        lruCache.set('B', 66);
        lruCache.set('C', 67);
        lruCache.set('D', 68);
        lruCache.set('E', 69);
        System.out.println(lruCache.get('A'));
        lruCache.set('F', 70);
        System.out.println(lruCache.get('B'));
    }
}
