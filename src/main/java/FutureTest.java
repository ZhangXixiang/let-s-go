import java.util.concurrent.*;

public class FutureTest {
    static ThreadPoolExecutor es = new ThreadPoolExecutor(2, 3, 3000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(1), new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws Exception {

        final long startTime = System.currentTimeMillis();
        FutureTask<String> task1 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    System.out.println("aaa");
                }
                return "b";
            }
        });

        es.execute(task1);
        threadPoolDesc(es);

        FutureTask<String> task2 = new FutureTask<String>(new Callable<String>() {
            @Override
            public String call() throws Exception {
                try {
                    Thread.sleep(4000);
                } catch (Exception e) {
                    System.out.println("aaa");
                }
                return "a";
            }
        });
        es.execute(task2);
        threadPoolDesc(es);

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
        threadPoolDesc(es);

        FutureTask<Callable> task4 = new FutureTask<>(new Callable<Callable>() {
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
        es.execute(task4);
        threadPoolDesc(es);
        System.out.println(task1.get());
        System.out.println(task2.get());
        System.out.println(task3.get());
        System.out.println(task4.get());

        System.out.println(System.currentTimeMillis() - startTime);
    }

    /**
     * 线程池的使用情况
     */
    private static void threadPoolDesc(ThreadPoolExecutor executor) {
        String threadPoolDesc = "CurrentPoolSize 当前线程池大小: " + executor.getPoolSize() +
                " - CorePoolSize 核心线程数: " + executor.getCorePoolSize() +
                " - MaximumPoolSize 最大线程数: " + executor.getMaximumPoolSize() +
                " - ActiveCount 活动线程数: " + executor.getActiveCount() +
                " - CompletedTaskCount 执行完的任务数量: " + executor.getCompletedTaskCount() +
                " - TotalTaskCount 任务总数: " + executor.getTaskCount() +
                " - isTerminated 是否停止: " + executor.isTerminated() +
                " - queueTaskCount 队列中的任务数量: " + executor.getQueue().size();
        System.out.println(threadPoolDesc);
    }

}
