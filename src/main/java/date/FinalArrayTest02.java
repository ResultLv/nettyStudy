package date;

import java.util.HashMap;
import java.util.Map;

public class FinalArrayTest02 {

    private static void test(){
        String [] strArrs = new String[]{"11", "22", "ss", "44", "55"};
        for (String str : strArrs){
            try {
                int i = Integer.parseInt(str);
                System.out.println(i);
            }catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    private static void lambdaTest(){
        Map<String, String> map = new HashMap<>();
    }

    public static void main(String[] args){
        test();
    }
}
