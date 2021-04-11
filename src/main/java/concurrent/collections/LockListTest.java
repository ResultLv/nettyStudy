package concurrent.collections;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * 加锁可以避免重复消费问题
 *
 * @author Result Lv
 * @date 2020/12/27 5:30 下午
 */
public class LockListTest {

    private static final List<String> messageList = new ArrayList<>();

    private static final Lock lock = new ReentrantLock();

    private static final Random rand = new Random(47);

    public static void main(String[] args) throws InterruptedException {
        Runnable t1 = () -> {
            while (!Thread.interrupted()){
                String message = String.valueOf(System.currentTimeMillis());
                try {
                    lock.lock();
                    messageList.add(message);
                    System.out.println(Thread.currentThread() + " add new message=" + message);
                }finally {
                    lock.unlock();
                }
                try {
                    Thread.sleep(rand.nextInt(3) * 1000 + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable t2 = () -> {
            while (!Thread.interrupted()){
                List<String> tempList = new ArrayList<>();
                try {
                    lock.lock();
                    tempList.addAll(messageList);
//                    System.out.println(Thread.currentThread() +  " consume messages=" + messageList);
                    messageList.clear();
                }finally {
                    lock.unlock();
                }
                try {
                    System.out.println(Thread.currentThread() + " consume messages=" + tempList);
                    Thread.sleep(rand.nextInt(5) * 1000 + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 100; i++) {
            new Thread(t1).start();
            new Thread(t2).start();
            Thread.sleep(1000);
        }
    }
}
