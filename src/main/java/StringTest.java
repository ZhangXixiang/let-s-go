public class StringTest {

    public static void main(String[] args) {
        System.out.println(getLongestPalindrome("baabccc",7));
        System.out.println("baabccc".substring(0,7));
    }


    public static int getLongestPalindrome(String A, int n) {
        int maxLen = 0;
        for (int i = 0; i <= n -1; i++) {
            for (int j=i+1; j<= n; j++) {
                final String substring = A.substring(i, j);
                if (isHuiWen(substring) && substring.length() > maxLen) {
                    maxLen = substring.length();
                }
            }
        }
        return maxLen;
    }


    /**
     * 是否是回文
     * @param str
     * @return
     */
    public static boolean isHuiWen(String str) {
        boolean flag = true;
        for (int i = 0; i <= str.length() / 2; i++) {
            if ((str.length() -1 - i) >= 0 && str.charAt(i) != str.charAt(str.length() -1 - i)) {
                return false;
            }
        }
        return true;
    }
}
