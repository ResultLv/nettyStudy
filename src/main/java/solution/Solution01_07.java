package solution;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Solution01_07 {

    public static void rotate(int[][] matrix) {
        int m = matrix.length;
        int n = matrix[0].length;
        //对角线翻转
        for (int i = 0; i < m; i++) {
            for (int j = i; j < n; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[j][i];
                matrix[j][i] = temp;
            }
        }
        //左右翻转
//        print(matrix);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n/2; j++) {
                int temp = matrix[i][j];
                matrix[i][j] = matrix[i][n-j-1];
                matrix[i][n-j-1] = temp;
            }
        }
//        print(matrix);
    }

    private static void print(int[][] matrix){
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                System.out.print(matrix[i][j] + " ");
            }
            System.out.println();
        }
        System.out.println();
    }

    /**
     * 根据正则表达式判断字符是否为汉字
     * 字符串中包含汉字时返回true
     */
    public static boolean hasChinese(String value) {

        // 汉字的Unicode取值范围
        String regex = "[\u4e00-\u9fa5]";
        Pattern pattern = Pattern.compile(regex);
        Matcher match = pattern.matcher(value);

        return match.find();

    }

    public static void main(String[] args) {
//        int[][] matrix = new int[][]{{1,2,3},{4,5,6},{7,8,9}};
//        int[][] matrix2 = new int[][]{{5,1,9,11},{2,4,8,10},{13,3,6,7},{15,14,12,16}};
//        int[][] matrix3 = new int[][]{{1,2,3,4},{5,6,7,8},{9,10,11,12},{13,14,15,16}};
//
//        rotate(matrix);
//        rotate(matrix2);
//        print(matrix3);
//        rotate(matrix3);
        System.out.println(hasChinese(null));
    }
}
