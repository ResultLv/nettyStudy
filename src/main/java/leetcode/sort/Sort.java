package leetcode.sort;

import java.util.Arrays;

/**
 * 排序算法
 * @author Result Lv
 * @date 2021/4/10 7:37 下午
 */
public class Sort {

    /**
     * 冒泡排序
     */
    public static int[] bubbleSort(int[] arr){
        for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length - i - 1; j++) {
                if (arr[j] > arr[j+1]){
                    int temp = arr[j+1];
                    arr[j+1] = arr[j];
                    arr[j] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 选择排序
     */
    public static int[] selectSort(int[] arr){
        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = i+1; j < arr.length; j++) {
                if (arr[i] >= arr[j]){
                    int temp = arr[j];
                    arr[j] = arr[i];
                    arr[i] = temp;
                }
            }
        }
        return arr;
    }

    /**
     * 插入排序
     */
    public static int[] insertSort(int[] arr){
        for (int i = 1; i < arr.length; i++) {
            int temp = arr[i];
            int j = i;
            while (j > 0 && arr[j-1] > temp){
                arr[j] = arr[j-1];
                j--;
            }
            arr[j] = temp;
        }
        return arr;
    }

    /**
     * 快速排序
     */
    public static int[] quickSort(int[] arr, int left, int right){
        if (left < right){
            int index = partition(arr, left, right);
            quickSort(arr, left, index-1);
            quickSort(arr, index+1, right);
        }
        return arr;
    }

    /**
     * 划分函数
     */
    private static int partition(int[] arr, int left, int right){
        int anchor = arr[left];
        while (left < right){
            while (left < right && arr[right] >= anchor) right--;
            arr[left] = arr[right];
            while (left < right && arr[left] <= anchor) left++;
            arr[right] = arr[left];
        }
        arr[left] = anchor;
        return left;
    }

    public static void print(int[] arr){
        StringBuilder sb = new StringBuilder("arr=[");
        for (int j : arr) {
            sb.append(j).append(",");
        }
        sb.append("]");
        System.out.println(sb.toString());
    }

    public static void main(String[] args){
        int[] arr = new int[]{5, 1, 4, 9, 0, 3, 2, 8, 4};
        print(bubbleSort(Arrays.copyOf(arr, arr.length)));
        print(selectSort(Arrays.copyOf(arr, arr.length)));
        print(insertSort(Arrays.copyOf(arr, arr.length)));
        print(quickSort(Arrays.copyOf(arr, arr.length), 0, arr.length-1));
    }
}
