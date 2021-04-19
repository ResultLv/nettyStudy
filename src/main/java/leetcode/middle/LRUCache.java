package leetcode.middle;

import java.util.*;
import java.util.concurrent.atomic.AtomicLong;

/**
 * （超时）LRU 缓存机制 - https://leetcode-cn.com/problems/lru-cache/
 *
 * 运用你所掌握的数据结构，设计和实现一个 LRU (最近最少使用) 缓存机制 。
 * 实现 LRUCache 类：
 * - LRUCache(int capacity) 以正整数作为容量 capacity 初始化 LRU 缓存
 * - int get(int key) 如果关键字 key 存在于缓存中，则返回关键字的值，否则返回 -1 。
 * - void put(int key, int value) 如果关键字已经存在，则变更其数据值；如果关键字不存在，则插入该组「关键字-值」。
 * - 当缓存容量达到上限时，它应该在写入新数据之前删除最久未使用的数据值，从而为新的数据值留出空间。
 *
 * @author Result Lv
 * @date 2021/4/17 10:47 下午
 */
public class LRUCache {

    private final Queue<Map.Entry<Integer, Long>> leastUsedQueue;

    private final Map<Integer, Integer> map;

    private final int capacity;

    private final AtomicLong latestUsedCount;

    public LRUCache(int capacity) {
        // 优先级队列实现小顶堆
        leastUsedQueue = new PriorityQueue<>(new Comparator<Map.Entry<Integer, Long>>() {
            @Override
            public int compare(Map.Entry<Integer, Long> o1, Map.Entry<Integer, Long> o2) {
                return (int) (o1.getValue() - o2.getValue());
            }
        });
        this.map = new HashMap<>();
        this.capacity = capacity;
        this.latestUsedCount = new AtomicLong(0);
    }

    public int get(int key) {
        if (map != null && map.containsKey(key)){
            updateLeastUsedQueue(key);
            return map.get(key);
        }
        return -1;
    }

    public void put(int key, int value) {
        if (map.size() < capacity){
            if (!map.containsKey(key)){
                Map.Entry<Integer, Long> entry = new MyEntry(key, latestUsedCount.incrementAndGet());
                leastUsedQueue.offer(entry);
            }else{
                updateLeastUsedQueue(key);
            }
        }else if (!map.containsKey(key)){
            Map.Entry<Integer, Long> removeEntry = leastUsedQueue.poll();
            if (removeEntry != null){
                map.remove(removeEntry.getKey());
            }
            Map.Entry<Integer, Long> entry = new MyEntry(key, latestUsedCount.incrementAndGet());
            leastUsedQueue.offer(entry);
        }else{
            updateLeastUsedQueue(key);
        }
        map.put(key, value);
    }

    private void updateLeastUsedQueue(int key){
        leastUsedQueue.removeIf(entry -> entry.getKey() == key);
        Map.Entry<Integer, Long> entry = new MyEntry(key, latestUsedCount.incrementAndGet());
        leastUsedQueue.offer(entry);
    }

    static class MyEntry implements Map.Entry<Integer, Long>{

        int key;
        long value;

        public MyEntry(int key, long value) {
            this.key = key;
            this.value = value;
        }

        @Override
        public Integer getKey() {
            return key;
        }

        @Override
        public Long getValue() {
            return value;
        }

        @Override
        public Long setValue(Long value) {
            this.value = value;
            return this.value;
        }
    }

    public void print(){
        if (map != null){
            map.forEach((k, v) -> {
                System.out.println("<" + k + "," + v + ">");
            });
        }
    }


    public static void main(String[] args){
//        LRUCache lruCache = new LRUCache(3);
//        lruCache.put(1, 11);
//        lruCache.put(2, 22);
//        lruCache.put(3, 33);
//        System.out.println(lruCache.get(1));
//        lruCache.put(4, 44);
//        System.out.println(lruCache.get(3));
//        System.out.println(lruCache.get(2));
//        lruCache.put(5, 55);
//
//        lruCache.print();

       LRUCache lruCache = new LRUCache(2);
       System.out.println(lruCache.get(2));
       lruCache.put(2, 6);
       System.out.println(lruCache.get(1));
       lruCache.put(1, 5);
       lruCache.put(1, 2);
       System.out.println(lruCache.get(1));
       System.out.println(lruCache.get(2));
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */