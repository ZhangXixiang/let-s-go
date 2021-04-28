import java.util.HashMap;

public class LoopTest {



    public int jumpFloor(int target) {

        if(target == 0){
            return 0;
        }
        if(target == 1){
            return 1;
        }
        if(target == 2){
            return 2;
        }

        return jumpFloor(target-1) + jumpFloor(target -2);
    }


    public static void main(String[] args) {
        HashMap<Integer, Integer> map = new HashMap<>();
        int arr[] = {0,1,2,3,4,6,7};
        System.out.println(solve(arr));
    }


    public static int solve (int[] a) {
        // write code here
        HashMap<Integer, Integer> map = new HashMap<>();

        for(int i = 0; i < a.length ; i ++){
            map.put(i,a[i]);
        }

        for(int i = 0 ; i < map.size() ; i ++){
            if(i != map.get(i)){
                return i;
            }
        }
        return 0;

    }
}
