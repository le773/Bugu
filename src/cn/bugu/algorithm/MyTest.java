package cn.bugu.algorithm;

public class MyTest {
    public static void main(String[] args) {
        char[] chars = new char[256];
        chars['A'] = 1;
        chars['1'] = 1;
        chars[1] = 0;

        for (int i = 0; i < chars.length; i++) {
            if (chars[i] >=0 && chars[i]<=256) {
                System.out.println(chars[i]);
            }
        }
    }
}
