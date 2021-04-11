package leetcode.simple;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 * 有效的字母异位词
 * https://leetcode-cn.com/problems/valid-anagram/
 * @author Result Lv
 * @date 2021/4/11 6:00 下午
 */
public class Solution242 {

    public static boolean isAnagram(String s, String t) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        for (int i = 0; i < s.length(); i++) {
            if (map1.containsKey(s.charAt(i))){
                map1.put(s.charAt(i), map1.get(s.charAt(i)) + 1);
            }else{
                map1.put(s.charAt(i), 1);
            }
        }
        for (int i = 0; i < t.length(); i++) {
            if (map2.containsKey(t.charAt(i))){
                map2.put(t.charAt(i), map2.get(t.charAt(i)) + 1);
            }else{
                map2.put(t.charAt(i), 1);
            }
        }
        if (map1.size() == map2.size()){
            for (Map.Entry<Character, Integer> entry : map1.entrySet()) {
                if (!entry.getValue().equals(map2.get(entry.getKey()))){
                    return false;
                }
            }
        }else {
            return false;
        }
        return true;
    }

    public static boolean isAnagram1(String s, String t) {
        char[] sChars = s.toCharArray();
        char[] tChars = t.toCharArray();
        Arrays.sort(sChars);
        Arrays.sort(tChars);
        return Arrays.equals(sChars, tChars);
    }

    public static void main(String[] args){
        System.out.println(isAnagram("anagram", "nagaram"));
        System.out.println(isAnagram1("anagram", "nagaram"));
    }
}
