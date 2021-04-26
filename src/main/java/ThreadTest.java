import java.util.concurrent.*;

public class ThreadTest {

    static ExecutorService es = new ThreadPoolExecutor(1, 20, 1000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<>(20), new MyRejectHandle());

    ExecutorService es1 = Executors.newFixedThreadPool(30);

    public static void main(String[] args) throws Exception {

        try {
            for (int i = 0; i < 450; i++) {
                es.execute(/*() -> {
                    try {
                        Thread.sleep(30000);
                    } catch (Exception e) {

                    }
                    System.out.println("abc");

                }*/

                        new Runnable() {
                            @Override
                            public void run() {
                                System.out.println(Thread.currentThread().getName() + " is running ..");
                            }
                        }

                        );
            }
        } catch (Exception e) {
            System.out.println("err");
        }

    }


}
