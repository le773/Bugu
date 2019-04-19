package cn.bugu.algorithm;

public class DistinctSubseque {


    public static void main(String[] args) {
        String S = "rabbbbit", T = "rabbit";
        Solution solution = new Solution();
        System.out.println(solution.numDistinct(S, T));
        System.out.println(solution.numDistinct2(S, T));
    }
}

class Solution {
    public int numDistinct(String S, String T) {
        if (S == null || T == null) {
            return 0;
        }

        int lenS = S.length();
        int lenT = T.length();

        if (lenS < lenT) {
            return 0;
        }

        int[][] D = new int[lenS + 1][lenT + 1];

        // BUG 1: forget to use <= instead of <....
        for (int i = 0; i <= lenS; i++) {
            for (int j = 0; j <= lenT; j++) {
                // both are empty.
                if (i == 0 && j == 0) {
                    D[i][j] = 1;
                } else if (i == 0) {
                    // S is empty, can't form a non-empty string.
                    D[i][j] = 0;
                } else if (j == 0) {
                    // T is empty. S is not empty.
                    D[i][j] = 1;
                } else {
                    D[i][j] = 0;
                    // keep the last character of S.
                    if (S.charAt(i - 1) == T.charAt(j - 1)) {
                        D[i][j] += D[i - 1][j - 1];
                    }

                    // discard the last character of S.
                    D[i][j] += D[i - 1][j];
                }
                System.out.print(D[i][j] + " ");
            }
            System.out.println("");
        }

        return D[lenS][lenT];
    }

    public int numDistinct2(String S, String T) {
        if (S == null || T == null) return 0;
        if (S.length() < T.length()) return 0;
        if (T.length() == 0) return 1;

        int[][] f = new int[S.length() + 1][T.length() + 1];
        for (int i = 0; i < S.length(); i++) {
            f[i][0] = 1;
            for (int j = 0; j < T.length(); j++) {
                if (S.charAt(i) == T.charAt(j)) {
                    f[i + 1][j + 1] = f[i][j + 1] + f[i][j];
                } else {
                    f[i + 1][j + 1] = f[i][j + 1];
                }
                System.out.print(f[i + 1][j + 1] + " ");
            }
            System.out.println("");
        }

        return f[S.length()][T.length()];
    }
}