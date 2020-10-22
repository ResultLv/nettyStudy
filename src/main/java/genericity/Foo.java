package genericity;

import test.Circle;

/**
 * @author Result Lv
 * @date 2020/10/22 11:25 下午
 */
public class Foo<T> {
    T t;

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public static void main(String[] args) throws Exception{
        Foo<Circle> foo = new Foo<>();
        foo.setT(new Circle());

        //Foo<Circle> 被擦除为 Foo<Object>, 使用反射在运行时可以设置String
        foo.getClass().getMethod("setT", Object.class).invoke(foo, "Daisy Su");
        //调用报错，因为实际已经变为了String（java.lang.String cannot be cast to test.Circle）
        Circle t = foo.getT();
        System.out.println(t);
    }
}
