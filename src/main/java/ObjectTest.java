public class ObjectTest extends Object implements Cloneable {

    private String name;
    private Integer age;

    private ObjectTest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
        // 单例模式
        // return getInstance();
    }

    /**
     * 禁止指令重排序
     */
    private static volatile ObjectTest objectTest;

    /**
     * 双重锁
     * instance = new Singleton()这句，这并非是一个原子操作，事实上在 JVM 中这句话大概做了下面 3 件事情。
     * <p>
     * 1.给 instance 分配内存
     * 2.调用 Singleton 的构造函数来初始化成员变量
     * 3.将instance对象指向分配的内存空间（执行完这步 instance 就为非 null 了）
     *
     * @return
     */
    public ObjectTest getInstance() {
        if (null == objectTest) {
            synchronized (ObjectTest.class) {
                if (null == objectTest) {
                    objectTest = new ObjectTest("1", 1);
                }
            }
        }
        return objectTest;
    }

    @Override
    public String toString() {
        return "ObjectTest{" +
                "name='" + name + '\'' +
                ", age=" + age +
                '}';
    }

    public static synchronized ObjectTest lock() throws Exception {
        Thread.sleep(20000);
        return objectTest;
    }


    public static void main(String[] args) throws Exception {
        final ObjectTest objectTest = new ObjectTest("1", 1);
        // final Object clone = objectTest.clone();
        // final Object clone2 = objectTest.clone();
        // System.out.println(clone2.equals(clone));
        System.out.println(objectTest.toString());

        final Object object = new Object();
        Thread t1 = new Thread() {
            public void run() {
                synchronized (object) {
                    System.out.println("T1 start!");
                    try {
                        object.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("T1 end!");
                }
            }
        };

        Thread t3 = new Thread() {
            public void run() {
                synchronized (object) {
                    System.out.println("T3 start!");
                    try {
                        object.wait(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    /*try {
                        Thread.sleep(10000);
                    } catch (Exception e){
                        e.printStackTrace();
                    }*/
                    System.out.println("T3 end!");
                }
            }
        };
        Thread t2 = new Thread() {
            public void run() {
                synchronized (object) {
                    try {
                        Thread.sleep(1000);
                    } catch (Exception e) {
                        System.out.println("err");
                    }
                    System.out.println("T2 start!");
                    // object.notifyAll();
                    // object.notify();
                    // try {
                    //     object.wait();
                    // } catch (InterruptedException e) {
                    //     e.printStackTrace();
                    // }
                    System.out.println("T2 end!");
                }
            }
        };

        t1.start();
        t3.start();
        t2.start();


        System.out.println("hahahah");

    }
}
