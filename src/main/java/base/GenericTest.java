package base;

import java.util.ArrayList;
import java.util.List;

public class GenericTest {


    public static void main(String[] args) throws Exception {
        List<Integer> list1 = new ArrayList<Integer>();
        List<String> list2 = new ArrayList<String>();

        System.out.println(list1.getClass().getName() == list2.getClass().getName());
        System.out.println(list1.getClass().getName());


        ArrayList<Integer> list = new ArrayList<Integer>();

        list.add(1);  //这样调用 add 方法只能存储整形，因为泛型类型的实例为 Integer

        list.getClass().getMethod("add", Object.class).invoke(list, "asd");

        for (int i = 0; i < list.size(); i++) {
            System.out.println(list.get(i));
        }
    }
}
