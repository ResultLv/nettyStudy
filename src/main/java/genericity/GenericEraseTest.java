package genericity;

import test.Circle;

import java.util.ArrayList;
import java.util.List;

/**
 * 范型擦除例子
 * @author Result Lv
 * @date 2020/10/22 11:25 下午
 */
public class GenericEraseTest {
    public static void main(String[] args) throws Exception {
        //范型擦除的证明，运行期java的范型信息全被擦除了 ArrayList<Integer>  -> ArrayList
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer
        list.getClass().getMethod("add", Object.class).invoke(list, "asd");
        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
