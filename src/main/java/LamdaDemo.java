import java.util.Comparator;
import java.util.Optional;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.stream.Stream;

public class LamdaDemo {


    public static void main(String[] args) {

        Optional<Integer> result = Stream.of("f", "ba", "hello")
                .map(s -> s.length())
                .filter(l -> l <= 3)
                .max((o1, o2) -> o1-o2);


        Optional<Integer> result3 = Stream.of("a","ab")
                .map(s -> s.length())
                .filter(s -> s < 3)
                .max((a1,a2)-> a1 - a2);
        // 还原为函数接口的实现方式
        Optional<Integer> result2 = Stream.of("fo", "bar", "hello")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer l) {
                        return l <= 3;
                    }
                })
                .max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });


        Optional<Integer> result4 = Stream.of("a","ab")
                .map(new Function<String, Integer>() {
                    @Override
                    public Integer apply(String s) {
                        return s.length();
                    }
                })
                .filter(new Predicate<Integer>() {
                    @Override
                    public boolean test(Integer integer) {
                        return integer < 3;
                    }
                })
                .max(new Comparator<Integer>() {
                    @Override
                    public int compare(Integer o1, Integer o2) {
                        return o1 - o2;
                    }
                });

        Integer integer = result4.get();
    }
}
