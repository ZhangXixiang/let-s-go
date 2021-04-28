import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class ArrayTest {


    public static void main(String[] args) {
        int[] arr = {1, 2, 3, 4, 4, 4, 4, 4, 5};
        MoreThanHalfNum_Solution(arr);
    }


    public static int MoreThanHalfNum_Solution(int[] array) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0; i < array.length; i++) {
            final Integer orDefault = map.getOrDefault(array[i], -1);
            if (-1 == orDefault) {
                map.put(array[i], 1);
            } else {
                map.put(array[i], map.get(array[i]) + 1);
            }
        }
        Iterator iterator = map.keySet().iterator();
        while (iterator.hasNext()) {
            Integer key = (Integer) iterator.next();
            if (map.get(key) > array.length / 2) {
                return array[key];
            }
        }
        return 0;
    }

    // 给出两个有序的整数数组 和 ，请将数组 合并到数组 中，变成一个有序的数组
    // 注意：
    // 可以假设 数组有足够的空间存放 数组的元素， A和B 中初始的元素数目分别为 m和n
    // 输入:
    // nums1 = [1,2,3,0,0,0], m = 3
    // nums2 = [2,5,6],       n = 3
    //
    // 输出: [1,2,2,3,5,6]
    public void merge(int A[], int m, int B[], int n) {
        int a = m - 1;
        int b = n - 1;
        int c = m + n - 1;
        while (c >= 0) {
            if (b < 0 || (a >= 0 && A[a] > B[b])) {
                A[c--] = A[a--];
            } else {
                A[c--] = B[b--];
            }
        }
    }

}
