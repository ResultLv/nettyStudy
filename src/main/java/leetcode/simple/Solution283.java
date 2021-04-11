package leetcode.simple;

/**
 * 移动零
 * https://leetcode-cn.com/problems/move-zeroes/
 * @author Result Lv
 * @date 2021/4/11 1:39 下午
 */
public class Solution283 {

//    输入: [0,1,0,3,12]
//    输出: [1,3,12,0,0]
    public static void moveZeroes(int[] nums) {
        int zeroIndex = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == 0 && nums[zeroIndex] != 0){
                zeroIndex = i;
            }else if (nums[i] != 0 && nums[zeroIndex] == 0){
                nums[zeroIndex] = nums[i];
                nums[i] = 0;
                zeroIndex++;
            }
        }

        for (int i = 0; i < nums.length; i++) {
            System.out.print(nums[i] + ",");
        }
    }
    
    public static void main(String[] args){
        int[] nums = new int[]{0,1,0,3,12};
        moveZeroes(nums);
    }
}
