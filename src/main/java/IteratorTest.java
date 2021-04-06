import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class IteratorTest {


    public static void main(String[] args) {
        final List<String> strings = new ArrayList<>(Arrays.asList("1", "2", "3", "4222","3212"));

        for (String str : strings) {
            if (str.length() == 4) {
                strings.remove(str);
            }
            System.out.println(str);
        }
        System.out.println("=======" + strings);


        final Iterator<String> iterator = strings.iterator();
        while (iterator.hasNext()) {
            String next = iterator.next();
            if (next.length() == 4) {
                iterator.remove();
            }
            System.out.println(next);
        }
        System.out.println("=======" + strings);

    }


}
