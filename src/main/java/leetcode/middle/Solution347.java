package leetcode.middle;

import java.util.*;
import java.util.stream.Collectors;

/**
 * 前k个高频元素
 * https://leetcode-cn.com/problems/top-k-frequent-elements/
 * @author Result Lv
 * @date 2021/4/11 10:47 下午
 */
public class Solution347 {

//    输入: nums = [1,1,1,2,2,3], k = 2
//    输出: [1,2]
    /*基于全排序，复杂度O(n)log(n)*/
    public static int[] topKFrequent(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        List<Integer> sortedKeys = map.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
//                .sorted(Comparator.comparing(Map.Entry::getValue, Comparator.reverseOrder()))
//                .sorted((e1, e2) -> e2.getValue().compareTo(e1.getValue()))
                .map(Map.Entry::getKey)
                .collect(Collectors.toList());

        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = sortedKeys.get(i);
        }

        for (int i = 0; i < topK.length; i++) {
            System.out.print(topK[i] + ",");
        }
        System.out.println();
        return topK;
    }

    /*基于小顶堆，复杂度O(n)log(k)*/
    public static int[] topKFrequent1(int[] nums, int k) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }
        PriorityQueue<Integer> queue = new PriorityQueue<>((o1, o2) -> map.get(o1) - map.get(o2));
        for (int key : map.keySet()) {
            queue.offer(key);
            if (queue.size() > k) {
                queue.poll();
            }
        }
        int[] topK = new int[k];
        for (int i = 0; i < k; i++) {
            topK[i] = queue.poll();
        }

        for (int i = 0; i < topK.length; i++) {
            System.out.print(topK[i] + ",");
        }
        System.out.println();
        return topK;
    }

    public static void main(String[] args){
        int[] nums = new int[]{1,1,1,2,2,3};
        topKFrequent(nums, 2);
        topKFrequent1(nums, 2);
    }
}
