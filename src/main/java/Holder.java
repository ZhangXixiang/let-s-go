public class Holder<T, A, B> {

     T val;
     B code;
     A msg;

    public Holder(T val, B code, A msg) {
        this.val = val;
        this.code = code;
        this.msg = msg;
    }

    public T getVal() {
        return val;
    }

    public void setVal(T val) {
        this.val = val;
    }

    public static void main(String[] args) {
        Holder<String,String,String> strHolder = new Holder("abc","1","2");
        Holder<Integer,String,Long> intHolder = new Holder(1,"3",3L);
        String s = strHolder.getVal();
    }



}
