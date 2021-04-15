import com.google.common.base.Stopwatch;
import com.google.common.util.concurrent.RateLimiter;


public class StopWatchTest {

    private static final RateLimiter rateLimiter = RateLimiter.create(1);

    public static void main(String[] args) throws Exception {
        Stopwatch stopwatch = Stopwatch.createStarted();
        System.out.println("hahah");
        // Thread.sleep(1);
        stopwatch.stop();
        // Duration duration = stopwatch.elapsed();
        System.out.println(stopwatch);
        for (int i = 0; i < 5; i++) {
            if (rateLimiter.tryAcquire()) {
                System.out.println("abc");
                Thread.sleep(1000);
            } else {
                System.out.println("limit");
            }
        }

    }
}
