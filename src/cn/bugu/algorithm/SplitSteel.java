package cn.bugu.algorithm;

public class SplitSteel {
    static int[] result = new int[]{0, 1, 0, 0, 0, 0};
    static int p[] = {0, 1, 5, 8, 9, 10, 17, 17, 20, 24, 30};

    public static void main(String[] args) {
        // System.out.println(bottomUp(4, p));
        Debug d = new Debug();
        String A = "ABCDTJHDLKHSNGH";
        String B = "EABCIJKYSKL";
        System.out.println(d.longestCommonSubsequence(A, B));
    }

    static public int bottomUp(int n, int[] array) {
        for (int i = 1; i <= n; i++) {
            int tmpMaxPrive = 0;
            int max = 0;
            for (int j = 1; j <= i; j++) {
                max = result[j] + array[i - j];
                if (max > tmpMaxPrive)
                    tmpMaxPrive = max;
            }
            result[i] = tmpMaxPrive;
        }
        return result[n];
    }



}

class Debug {
    public int longestCommonSubsequence(String A, String B) {
        // write your code here
        int m = A.length();
        int n = B.length();
        int[][] f = new int[m + 1][n + 1];

        for(int i = 1; i <= m; i++){
            for(int j = 1; j <= n; j++){
                if(A.charAt(i - 1) == B.charAt(j - 1)){
                    f[i][j] = f[i - 1][j - 1] + 1;
                }else{
                    f[i][j] = Math.max(f[i - 1][j], f[i][j - 1]);
                }
            }
        }
        return f[m][n];
    }
}