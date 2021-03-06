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


        //???????????????
        /*ExecutorService es = Executors.newSingleThreadExecutor();
        //??????Callable????????????
        CallableDemo calTask = new CallableDemo();
        //?????????????????????????????????
        Future<Integer> future1 = es.submit(calTask);
        //???????????????
        es.shutdown();*/


        //???????????????
        ExecutorService es = Executors.newSingleThreadExecutor();
        //??????Callable????????????
        CallableDemo calTask=new CallableDemo();
        //??????FutureTask
        FutureTask<Integer> future1=new FutureTask<>(calTask);
        //????????????
        es.submit(future1);
        //???????????????
        es.shutdown();

        try {
            Thread.sleep(2000);
            System.out.println("??????????????????????????????");

            if (future1.get() != null) {
                //????????????????????????
                System.out.println("future1.get()-->" + future1.get());
            } else {
                //????????????????????????
                System.out.println("future1.get()??????????????????");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("????????????????????????");
    }




    /**
     * ????????????????????????
     */
    private static void threadPoolDesc(ThreadPoolExecutor executor) {
        String threadPoolDesc = "CurrentPoolSize ?????????????????????: " + executor.getPoolSize() +
                " - CorePoolSize ???????????????: " + executor.getCorePoolSize() +
                " - MaximumPoolSize ???????????????: " + executor.getMaximumPoolSize() +
                " - ActiveCount ???????????????: " + executor.getActiveCount() +
                " - CompletedTaskCount ????????????????????????: " + executor.getCompletedTaskCount() +
                " - TotalTaskCount ????????????: " + executor.getTaskCount() +
                " - isTerminated ????????????: " + executor.isTerminated() +
                " - queueTaskCount ????????????????????????: " + executor.getQueue().size();
        System.out.println(threadPoolDesc);
    }

}
