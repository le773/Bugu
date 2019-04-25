package cn.bugu.algorithm;

public class Backpack2 {


    public static void main(String[] args) {
        int[] item = {2, 3, 5, 7};
        Solution solution = new Solution();
        System.out.print(solution.backPack(13, item));
    }

    static class Solution {
        /**
         * @param m: An integer m denotes the size of a backpack
         * @param A: Given n items with size A[i]
         * @return: The maximum size
         */
        public int backPack(int m, int[] A) {
            if (A == null || A.length == 0) return 0;

            final int M = m;
            final int N = A.length;
            int[][] bp = new int[N + 1][M + 1];

            for (int i = 0; i < N; i++) {
                for (int j = 0; j <= M; j++) {
                    if (A[i] > j) {
                        bp[i + 1][j] = bp[i][j];
                    } else {
                        bp[i + 1][j] = Math.max(bp[i][j], bp[i][j - A[i]] + A[i]);
                    }
                }
            }

            return bp[N][M];
        }
    }
}


