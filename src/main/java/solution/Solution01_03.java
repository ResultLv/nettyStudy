package solution;

public class Solution01_03 {

    public static String replaceSpaces(String S, int length) {
        char[] arr = S.toCharArray();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            if (arr[i] == ' '){
                sb.append("%20");
            }else{
                sb.append(arr[i]);
            }
        }
        return sb.toString();
    }

    private static String clearNoChinese(String str){
        String tempStr = str.replaceAll("[^a-zA-Z\\u4E00-\\u9FA5]", "");
        return tempStr.trim();
    }

    public static void main(String[] args) {
//        System.out.println(Solution01_03.replaceSpaces("Mr John Smith    ", 13));
//        System.out.println(Solution01_03.replaceSpaces("               ", 5));

        System.out.println(clearNoChinese("0日用品"));
        System.out.println(clearNoChinese("生日用品"));
        System.out.println(clearNoChinese("日用品。"));
        System.out.println(clearNoChinese("日用品,易碎品"));
        System.out.println(clearNoChinese("日用品易碎品"));
        System.out.println(clearNoChinese("日用品|"));
        System.out.println(clearNoChinese("0日用品,"));
        System.out.println(clearNoChinese("0日用品dsfs32,"));
    }
}
