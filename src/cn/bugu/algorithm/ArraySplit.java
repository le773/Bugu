package cn.bugu.algorithm;

public class ArraySplit {
    static void solution(int[] array, int[][][] s, int n, int sum) {
        for (int i = 0; i < sum / 2 + 1; i++) {
            s[0][0][i] = 0;
            s[1][0][i] = 0;
        }
        for (int i = 0; i < n / 2 + 1; i++) {
            s[0][i][0] = 0;
            s[1][i][0] = 0;
        }
        for (int i = 1; i < n; i++) {
            int maxj = Math.min(i, n / 2 + 1);
            for (int j = 1; j < maxj; j++) {
                for (int k = 1; k < sum / 2 + 1; k++) {
                    int skipi = s[i - 1][j][k];
                    int takei = k < array[i - 1] ? 0 : s[i - 1][j - 1][k - array[i - 1]] + array[i - 1];
                    System.out.println("i=" + i + ", j=" + j + ", k=" + k + ", sum=" + Math.max(takei, skipi));
                    s[i][j][k] = Math.max(takei, skipi);
                }
            }
        }
    }

    public static void main(String[] args) {

        int[] array = {1, 2, 3, 7, 10, 11};
        int n = array.length;
        int sum = 0;
        for (int i = 0; i < n; i++) {
            sum += array[i];
        }
        int[][][] s = new int[n][n / 2 + 1][sum / 2 + 1];
        solution(array, s, n, sum);
        int left = s[n - 1][n / 2][sum / 2];
        System.out.println(left + " " + (sum - left));
    }
}