import java.util.concurrent.RejectedExecutionHandler;
import java.util.concurrent.ThreadPoolExecutor;

public class MyRejectHandle implements RejectedExecutionHandler {

    @Override
    public void rejectedExecution(Runnable r, ThreadPoolExecutor executor) {
        System.out.println("hahaha");

        String desc = "CurrentPoolSize 当前线程池大小: " + executor.getPoolSize() +
                " - CorePoolSize 核心线程数: " + executor.getCorePoolSize() +
                " - MaximumPoolSize 最大线程数: " + executor.getMaximumPoolSize() +
                " - ActiveCount 活动线程数: " + executor.getActiveCount() +
                " - CompletedTaskCount 执行完的任务数量: " + executor.getCompletedTaskCount() +
                " - TotalTaskCount 任务总数: " + executor.getTaskCount() +
                " - isTerminated 是否停止: " + executor.isTerminated() +
                " - queueTaskCount 队列中的任务数量: " + executor.getQueue().size();
        System.out.println(desc);
    }

}
