package concurrent.cyclicBarrier;

import java.util.Random;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author Result Lv
 * @date 2020/12/20 5:15 下午
 */
public class Horse implements Runnable{

    private static int counter = 0;
    private final int id = counter++;
    private int strides = 0;
    private static Random rand = new Random(47);
    private static CyclicBarrier barrier;

    public Horse(CyclicBarrier b){
        barrier = b;
    }

    @Override
    public void run() {
        try {
            while (!Thread.interrupted()){
                synchronized (this){
                    strides += rand.nextInt(3);
                }
                barrier.await();
            }
        }catch (InterruptedException | BrokenBarrierException e){
            throw new RuntimeException(e);
        }
    }

    public synchronized int getStrides() {
        return strides;
    }

    public String toString(){
        return "Horse " + id + " ";
    }

    public String tracks(){
        StringBuilder s = new StringBuilder();
        for (int i = 0; i < getStrides(); i++) {
            s.append("*");
        }
        s.append(id);
        return s.toString();
    }
}
