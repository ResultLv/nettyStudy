package concurrent.collections;

import java.util.List;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * CopyOnWriteArrayList无法避免重复消费问题
 *
 * @author Result Lv
 * @date 2020/12/27 5:06 下午
 */
public class CopyOnWriteListTest {

    private static final List<String> messageList = new CopyOnWriteArrayList<>();

    private static final Random rand = new Random(47);

    public static void main(String[] args) throws InterruptedException {
        Runnable t1 = () -> {
            while (!Thread.interrupted()){
                String message = String.valueOf(System.currentTimeMillis());
                messageList.add(message);
                System.out.println(Thread.currentThread() + " add new message=" + message);
                try {
                    Thread.sleep(rand.nextInt(3) * 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        Runnable t2 = () -> {
            while (!Thread.interrupted()){
                System.out.println(Thread.currentThread() + " consume messages=" + messageList);
                messageList.clear();
                try {
                    Thread.sleep(rand.nextInt(5) * 1000 + 1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };

        for (int i = 0; i < 5; i++) {
            new Thread(t1).start();
            new Thread(t2).start();
            Thread.sleep(1000);
        }
    }
}
