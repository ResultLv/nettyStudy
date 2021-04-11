package concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author Result Lv
 * @date 2020/12/13 9:33 下午
 */
public class AtomicityTest implements Runnable{

    private static final Lock lock = new ReentrantLock();

    private int i = 0;

    public int getValue(){
        return i;
    }

    private synchronized void evenIncrement(){
        i++;
        i++;
    }

    @Override
    public void run() {
        while (true){
            evenIncrement();
        }
    }

    public static void main(String[] args){
        ExecutorService exec = Executors.newCachedThreadPool();
        AtomicityTest at = new AtomicityTest();
        exec.execute(at);
        while (true){
            int val = at.getValue();
            if (val % 2 != 0){
                System.out.println(val);
                System.exit(0);
            }
        }
    }
}
