import java.util.*;

public class SortTest {

    public static void main(String[] args) {

        int[] a = {1, 2, 3, 2, 2, 2, 7, 8, 9};
        int[] b = {1, 6, 3, 2, 2, 2, 7, 8, 9};
        // System.out.println(bsearch(a,9,2));
        System.out.println(bubbleSort(b));
        // new StopWatch("a");
        // 10是初始大小，0.75是装载因子，true是表示按照访问时间排序
        HashMap<Integer, Integer> m = new LinkedHashMap<>(10, 0.75f, true);
        m.put(3, 11);
        m.put(1, 12);
        m.put(5, 23);
        m.put(2, 22);

        m.put(3, 26);
        m.get(5);

        // for (Map.Entry e : m.entrySet()) {
        //     System.out.println(e.getKey());
        // }
        // System.out.println("maxLength=" + maxLength("abcadfgc".toCharArray()));
        // System.out.println("maxLength=" + lengthOfLongestSubstring("abcadfgc"));
        // System.out.println(checkTag("([({})]"));
    }


    public static boolean checkTag(String tagStr) {
        /*boolean flag = true;
        while (flag) {
            int length = tagStr.length();

            tagStr = tagStr.replace("[]", "");
            tagStr = tagStr.replace("{}", "");
            tagStr = tagStr.replace("()", "");
            if (length == tagStr.length()) {
                flag = false;
            }
        }
        return 0 == tagStr.length();*/
        Stack<Character> stack = new Stack<>();

        for(char alp : tagStr.toCharArray()){
            if(alp == '('){
                stack.push(')');
            }else if(alp == '['){
                stack.push(']');
            }else if(alp == '{'){
                stack.push('}');
            }else if(stack.isEmpty() || stack.pop()!= alp){
                return false;
            }
        }
        return stack.isEmpty();
    }






    /**
     * 冒泡排序
     *
     * @param a
     * @return
     */
    public static int[] bubbleSort(int[] a) {
        for (int i = 0; i < a.length - 1; i++) {

            for (int j = i; j < a.length - 1 - i; j++) {
                if (a[j + 1] < a[j]) {
                    int temp = a[j];
                    a[j] = a[j + 1];
                    a[j + 1] = temp;
                }
            }
        }
        return a;
    }


    /**
     * 二分查找
     *
     * @param a
     * @param n
     * @param value
     * @return
     */
    public static int bsearch(int[] a, int n, int value) {
        int low = 0;
        int high = n - 1;
        while (low <= high) {
            int mid = low + ((high - low) >> 1);
            if (a[mid] > value) {
                high = mid - 1;
            } else if (a[mid] < value) {
                low = mid + 1;
            } else {
                if ((mid == 0) || (a[mid - 1] != value)) return mid;
                else high = mid - 1;
                // return mid;
            }
        }
        return -1;
    }


    /**
     * 最小K个数
     *
     * @param input
     * @param k
     * @return
     */
    public ArrayList<Integer> GetLeastNumbers_Solution(int[] input, int k) {
        ArrayList<Integer> result = new ArrayList();
        if (null == input || input.length < k) {
            return result;
        }
        for (int i = 0; i < input.length; i++) {
            for (int j = 0; j < input.length; j++) {

                if (input[i] < input[j]) {
                    int temp = input[i];
                    input[i] = input[j];
                    input[j] = temp;
                }

            }
        }


        for (int i = 0; i < k; i++) {
            result.add(input[i]);
        }
        return result;
    }


    /**
     * @param arr int整型一维数组 the array
     * @return int整型
     */
    public static int maxLength(char[] arr) {
        // write code here
        if (arr == null || arr.length == 0) {
            return 0;
        }
        //用于存放该数字上一次出现的索引
        Map<Character, Integer> map = new HashMap<>();
        int left = 0, max = 1, secMax = 0;
        for (int i = 0; i < arr.length; ++i) {
            //获取上次出现的索引位置，若第一次出现则为-1
            int idx = map.getOrDefault(arr[i], -1);
            //该位置是否在当前区间，如果是 以当前元素结尾的最长无重复子串的left只能从idx + 1计算
            if (idx >= left) {
                left = idx + 1;
            }
            map.put(arr[i], i);
            max = Math.max(max, i - left + 1);
            System.out.println(max);
            System.out.println(max);
        }
        return max;
    }


    public static int lengthOfLongestSubstring(String s) {
        char[] a = s.toCharArray();
        int n = a.length;
        if (n < 2) {
            return n;
        }
        int res = 0;
        HashMap<Character, Integer> map = new HashMap<>();
        int i = 0, j = 0;
        while (j < n) {
            if (!map.containsKey(a[j])) {
                map.put(a[j], j);
            } else {
                i = Math.max(i, map.get(a[j]) + 1);
                map.put(a[j], j);
            }

            res = Math.max(res, j - i + 1);
            j++;
        }
        return res;
    }
}
