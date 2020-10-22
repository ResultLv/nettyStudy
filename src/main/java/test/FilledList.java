package test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Result Lv
 * @date 2020/10/18 11:22 下午
 */
public class FilledList<T> {

    private Class<T> type;

    private FilledList(Class<T> type) {
        this.type = type;
    }

    public List<T> create(int nElements){
        List<T> result = new ArrayList<>();
        try {
            for (int i = 0; i < nElements; i++) {
                T t = type.newInstance();
                result.add(t);
            }
        }catch (Exception e){
            throw new RuntimeException();
        }
        return result;
    }

    public static void main(String[] args){
        FilledList<CountedInteger> filledList = new FilledList<>(CountedInteger.class);
        System.out.println(filledList.create(15));
    }
}
