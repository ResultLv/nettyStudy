package solution;

import java.util.HashMap;
import java.util.Map;

public class Solution01_02 {

    public static boolean CheckPermutation(String s1, String s2) {
        Map<Character, Integer> map1 = new HashMap<>();
        Map<Character, Integer> map2 = new HashMap<>();
        if (s1.length() != s2.length()) {
            return false;
        }
        for (int i = 0; i < s1.length(); i++) {
            char c1 = s1.charAt(i);
            if (!map1.containsKey(c1)){
                map1.put(c1, 1);
            }else{
                map1.put(c1, map1.get(c1)+1);
            }
            char c2 = s2.charAt(i);
            if (!map2.containsKey(c2)){
                map2.put(c2, 1);
            }else {
                map2.put(c2, map2.get(c2) + 1);
            }
        }
        for (Map.Entry<Character, Integer> entry : map1.entrySet()){
            if (!map2.containsKey(entry.getKey())){
                return false;
            }else if (!map2.get(entry.getKey()).equals(entry.getValue())){
                return false;
            }
        }
        return true;
    }
    public static void main(String[] args) {
        System.out.println(Solution01_02.CheckPermutation("a", "ab"));
        System.out.println(Solution01_02.CheckPermutation("jzvthzihsvghjhbrpfhdwixmyaxjrdzfvnhpmyrbqjpdffykqgahgzpjwvouurr",
                "hhqhxjyrghjjsmduaxppwrqkikqnfdrzjowapehtbyrgrfyprrfrebzduxvvhhu"));
    }
}
