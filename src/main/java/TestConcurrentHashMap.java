import java.util.ArrayList;
import java.util.HashMap;
import java.util.concurrent.ConcurrentHashMap;

public class TestConcurrentHashMap {


    public static void main(String[] args) {
        // final ConcurrentHashMap<Object, Object> objectObjectConcurrentHashMap = new ConcurrentHashMap<>();
        // objectObjectConcurrentHashMap.put(null,"");
        // objectObjectConcurrentHashMap.put("null",null);
        HashMap<String, Object> map = new HashMap<>(2);
        String key = "1";
        int h;
        map.put("1",1);
        map.put("2",2);
        map.put("3",3);
        map.put("4",4);
        map.put(null,5);
        map.put("5",6);
        System.out.println( (key == null) ? 0 : (h = key.hashCode()) ^ (h >>> 16));
        System.out.println("abc");
        System.out.println(Integer.MAX_VALUE);


        ArrayList<Object> list = new ArrayList<Object>();
        final int N = 10000000;
        list = new ArrayList<Object>();
        long startTime1 = System.currentTimeMillis();
        list.ensureCapacity(N);
        for (int i = 0; i < N; i++) {
            list.add(i);
        }
        long endTime1 = System.currentTimeMillis();
        System.out.println("使用ensureCapacity方法后："+(endTime1 - startTime1));
    }
}
