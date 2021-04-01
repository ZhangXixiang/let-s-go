import java.io.IOException;
import java.util.List;

public class TestNPE {

    public static void main(String[] args) {

        List<String> strList = null;
        throw new RuntimeException();
        // throw new IOException();
        /*for (String str : strList){
            System.out.println("ahaha");
        }*/

    }
}
