package leetcode.middle;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 寻找重复数
 * https://leetcode-cn.com/problems/find-the-duplicate-number/
 * @author Result Lv
 * @date 2021/4/11 10:24 下午
 */
public class Solution287 {

//    输入：nums = [1,3,4,2,2]
//    输出：2
    public static int findDuplicate(int[] nums) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < nums.length; i++) {
            map.put(nums[i], map.getOrDefault(nums[i], 0) + 1);
        }
        for (Map.Entry<Integer, Integer> entry : map.entrySet()) {
            if (entry.getValue() > 1){
                return entry.getKey();
            }
        }
        return 0;
    }

    /*排序+二进制异或*/
    public static int findDuplicate1(int[] nums) {
        Arrays.sort(nums);
        for (int i = 1; i < nums.length; i++) {
            if((nums[i-1] ^ nums[i])==0){
                return nums[i];
            }
        }
        return -1;
    }

    public static void main(String[] args){
        int[] nums = new int[]{3,1,3,4,2};
        System.out.println(findDuplicate(nums));
        System.out.println(findDuplicate1(nums));
    }
}
