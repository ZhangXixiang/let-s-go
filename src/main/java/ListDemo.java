import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListDemo {

    public static void main(String[] args) {
        String str = "1,2,3,4";
        final List<String> objects = new ArrayList<>(Arrays.asList(str.split(",")));
        objects.add("1");
        System.out.println(objects);
    }
}
