package genericity;

import test.Circle;
import test.Shape;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Result Lv
 * @date 2020/10/25 5:49 下午
 */
public class GenericListTest {

    public static void main(String[] args) {
        List<? extends Shape> list = new ArrayList<>();
//        list.add(new Circle());
        list.add(null);
        Shape shape = list.get(0);
        System.out.println(shape);

        List<Circle> circleList = new ArrayList<>();
        circleList.add(new Circle());
        list = circleList;
        System.out.println(list);
        Circle circle = new Circle();
//        list.add(circle);
        circleList.add(circle);
        System.out.println(list);
    }
}
