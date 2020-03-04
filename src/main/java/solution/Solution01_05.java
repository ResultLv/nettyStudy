package solution;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class Solution01_05 {

    public static boolean oneEditAway(String first, String second) {
        if (Math.abs(first.length() - second.length()) > 1){
            return false;
        }
        int i = 0, j = 0, count = 0;
        while (i < first.length() && j < second.length()){
            //长度相同情况,双指针一直递增，累计不同个数
            if (first.length() == second.length()){
                if (first.charAt(i) != second.charAt(j)){
                    count++;
                }
                i++; j++;
            }else{
                //长度差一位情况，若字符不同则较长字符串指针递增，同时累加不同个数
                if (first.charAt(i) == second.charAt(j)){
                    i++; j++;
                }else if (first.length() < second.length()){
                    j++; count++;
                }else {
                    i++; count++;
                }
            }

        }
        return count < 2;
    }

    public static void main(String[] args) {
//        System.out.println(Solution01_05.oneEditAway("pale", "ple"));
//        System.out.println(Solution01_05.oneEditAway("pales", "pal"));
        System.out.println(Solution01_05.oneEditAway("", "a"));
        System.out.println(Solution01_05.oneEditAway("", ""));
//        System.out.println(Solution01_05.oneEditAway("mark", "karma"));
//        System.out.println(Solution01_05.oneEditAway("teacher", "bleacher"));
        System.out.println(Solution01_05.oneEditAway("a", "b"));
        System.out.println(Solution01_05.oneEditAway("ab", "ba"));
        System.out.println(Solution01_05.oneEditAway("aba", "aab"));
        System.out.println(Solution01_05.oneEditAway("aab", "aba"));
        System.out.println(Solution01_05.oneEditAway("teacher", "beacher"));
    }
}
