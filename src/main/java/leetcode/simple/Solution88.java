package leetcode.simple;

/**
 * 合并两个有序数组
 * https://leetcode-cn.com/problems/merge-sorted-array/
 * @author Result Lv
 * @date 2021/4/11 12:57 下午
 */
public class Solution88 {
    
    public static void merge(int[] nums1, int m, int[] nums2, int n) {
        int[] mergeNums = new int[m+n];
        int index1 = 0, index2 = 0;
        for (int i = 0; i < m + n; i++) {
            if (index1 < m && index2 < n){
                if (nums1[index1] <= nums2[index2]) {
                    mergeNums[i] = nums1[index1];
                    index1++;
                } else {
                    mergeNums[i] = nums2[index2];
                    index2++;
                }
            } else if (index1 == m && index2 < n){
                mergeNums[i] = nums2[index2];
                index2++;
            } else if (index2 == n && index1 < m){
                mergeNums[i] = nums1[index1];
                index1++;
            }
        }
        for (int i = 0; i < nums1.length; i++) {
            nums1[i] = mergeNums[i];
        }
        for (int i : nums1) {
            System.out.print(i + ",");
        }
    }
    
    public static void main(String[] args){
        int[] nums1 = new int[]{1,2,3,0,0,0};
        int[] nums2 = new int[]{2,5,6};
        merge(nums1, 3, nums2, 3);
    }
}
