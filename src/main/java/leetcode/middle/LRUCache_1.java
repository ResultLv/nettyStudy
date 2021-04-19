package leetcode.middle;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;

/**
 * （优化后）LRU 缓存机制 - https://leetcode-cn.com/problems/lru-cache/
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
public class LRUCache_1 {

    private final int capacity;

    private final Map<Integer, Integer> map;

    private final LinkedList<Integer> usedList;

    public LRUCache_1(int capacity) {
        this.capacity = capacity;
        this.map = new HashMap<>();
        this.usedList = new LinkedList<>();
    }

    public int get(int key) {
        if (map.containsKey(key)){
            updateUsedList(key);
            return map.get(key);
        }

        return -1;
    }

    public void put(int key, int value) {
        if (map.size() < capacity){
            if (map.containsKey(key)){
                updateUsedList(key);
            }else{
                usedList.addFirst(key);
            }
        }else if (map.containsKey(key)){
            updateUsedList(key);
        }else{
            Integer removedKey = usedList.removeLast();
            map.remove(removedKey);
            usedList.addFirst(key);
        }
        map.put(key, value);
    }

    private void updateUsedList(int key){
        usedList.remove(new Integer(key));
        usedList.addFirst(key);
    }


    public static void main(String[] args){
        LRUCache lruCache = new LRUCache(3);
        lruCache.put(1, 11);
        lruCache.put(2, 22);
        lruCache.put(3, 33);
        System.out.println(lruCache.get(1));
        lruCache.put(4, 44);
        System.out.println(lruCache.get(3));
        System.out.println(lruCache.get(2));
        lruCache.put(5, 55);
    }
}
/**
 * Your LRUCache object will be instantiated and called as such:
 * LRUCache obj = new LRUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */