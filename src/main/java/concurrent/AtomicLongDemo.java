package concurrent;

import java.util.concurrent.atomic.AtomicLong;

/**
 * @author Result Lv
 * @date 2020/8/2 9:28 下午
 */
public class AtomicLongDemo {

    private static final AtomicLong atomicLong = new AtomicLong();

    private static final Integer[] arr1 = new Integer[]{0, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    private static final Integer[] arr2 = new Integer[]{10, 1, 2, 3, 0, 5, 6, 0, 56, 0};

    public static void main(String[] args) throws InterruptedException {
        Thread t1 = new Thread(() -> {
            for (int i = 0; i < arr1.length; i++) {
                if (arr1[i] == 0){
                    atomicLong.incrementAndGet();
                }
            }
        });

        Thread t2 = new Thread(() -> {
            for (int i = 0; i < arr2.length; i++) {
                if (arr2[i] == 0){
                    atomicLong.incrementAndGet();
                }
            }
        });

        t1.start();
        t2.start();

        t1.join();
        t2.join();

        System.out.println("count 0:" + atomicLong.get());
    }
}
