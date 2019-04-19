package cn.bugu.algorithm;

public class MyTest7 {

    public static void main(String[] args) {
        int[] A = {1, 3, 5, 7};
        int[] B = new int[10];
        int sum = getSum(A);
        if (sum % 2 == 0)
        {
            boolean JudgeSuccess = foo(A, A.length, 0, sum / 2, B, 0);
            if (JudgeSuccess)
                System.out.println("True");
            else
                System.out.println("False");
        }
        else
            System.out.println("False");
    }

    static public int getSum(int[] array) {
        int sum = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }
        return sum;
    }

    static public boolean foo(int[] A, int len, int curSum, int defSum, int[] B, int curPos) {
        if (curSum == defSum) {
            return true;
        }

        if (len == 0)
            return false;
        boolean bFound = false;
        for (int i = 0; i < len; i++) {
            if (curSum + A[i] <= defSum) {
                B[curPos++] = A[i];

                int[] C = new int[len - i - 1];
                for (int k = 0, j = i + 1; j < A.length; j++, k++) {
                    C[k] = A[j];
                }

                if (foo(C, len - 1, curSum + A[i], defSum, B, curPos)) {
                    bFound = true;
                    break;
                }
                B[curPos] = 0;
                curPos--;
            }
        }

        return bFound;
    }
}
