import java.util.Collection;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.concurrent.*;

public class FutureTest {

    static Executor es = new ThreadPoolExecutor(2, 3, 3000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue(1), new ThreadPoolExecutor.DiscardOldestPolicy());

    public static void main(String[] args) throws Exception {

        final long startTime = System.currentTimeMillis();
        FutureTask<Callable> task1 = new FutureTask<>(new Callable<Callable>() {
            @Override
            public Callable call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    System.out.println("aaa");
                }
                return null;
            }
        });

        es.execute(task1);

        FutureTask<Callable> task2 = new FutureTask<>(new Callable<Callable>() {
            @Override
            public Callable call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    System.out.println("aaa");
                }
                return null;
            }
        });
        es.execute(task2);

        FutureTask<Callable> task3 = new FutureTask<>(new Callable<Callable>() {
            @Override
            public Callable call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    System.out.println("aaa");
                }
                return null;
            }
        });
        es.execute(task3);

        task1.get();
        task2.get();
        task3.get();

        System.out.println(System.currentTimeMillis() - startTime);
    }
}
