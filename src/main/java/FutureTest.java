import java.util.concurrent.*;

public class FutureTest {
    static ThreadPoolExecutor es = new ThreadPoolExecutor(2, 3, 3000, TimeUnit.MICROSECONDS, new LinkedBlockingQueue<Runnable>(10), new ThreadPoolExecutor.AbortPolicy());

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
        // futrureTask
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
        es.submit(task2);
        // future
        Future future = es.submit(new Callable<String>() {
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
        System.out.println(future.get());
        System.out.println(task2.get());
        System.out.println(task3.get());
        System.out.println(task4.get());

        System.out.println(System.currentTimeMillis() - startTime);


        //创建线程池
        /*ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask = new CallableDemo();
        //提交任务并获取执行结果
        Future<Integer> future1 = es.submit(calTask);
        //关闭线程池
        es.shutdown();*/


        //创建线程池
        ExecutorService es = Executors.newSingleThreadExecutor();
        //创建Callable对象任务
        CallableDemo calTask=new CallableDemo();
        //创建FutureTask
        FutureTask<Integer> future1=new FutureTask<>(calTask);
        //执行任务
        es.submit(future1);
        //关闭线程池
        es.shutdown();

        try {
            Thread.sleep(2000);
            System.out.println("主线程在执行其他任务");

            if (future1.get() != null) {
                //输出获取到的结果
                System.out.println("future1.get()-->" + future1.get());
            } else {
                //输出获取到的结果
                System.out.println("future1.get()未获取到结果");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("主线程在执行完成");
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
