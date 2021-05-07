import java.util.concurrent.locks.LockSupport;

public class TestLockSupport {


    public static void main(String[] args) {
        Thread.currentThread().suspend();
        Thread.currentThread().resume();

        System.out.println("resume");
        new Thread(()->{
            try {

                Thread.sleep(2000);
            }catch (Exception e){
                System.out.println("ex");
            }
            // LockSupport.unpark(Thread.currentThread());
            Thread.currentThread().suspend();
            Thread.currentThread().resume();

            System.out.println("resume");
        }).start();
        LockSupport.park();

        System.out.println("hahah");
    }
}
