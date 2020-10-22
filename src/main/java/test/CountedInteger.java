package test;

/**
 * @author Result Lv
 * @date 2020/10/18 11:21 下午
 */
public class CountedInteger {

    private static long counter;

    private final long id = counter++;

    public String toString(){
        return Long.toString(id);
    }
}
