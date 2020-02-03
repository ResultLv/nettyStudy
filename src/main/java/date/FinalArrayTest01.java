package date;

public class FinalArrayTest01 {

    public static final String [] love = {"I", "Love", "swy"};

    @Override
    public String toString(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("[");
        for (int i = 0; i < love.length; i++) {
            stringBuilder.append(love[i]);
            if (i != love.length-1){
                stringBuilder.append(",");
            }
        }
        stringBuilder.append("]");
        return stringBuilder.toString();
    }

    private static void test(){
        love[2] = "苏婉怡";
    }

    public static void main(String[] args){
        FinalArrayTest01 test = new FinalArrayTest01();
        System.out.println(test.toString());
        test();
        System.out.println(test.toString());
    }
}
