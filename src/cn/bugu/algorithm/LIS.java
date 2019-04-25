package cn.bugu.algorithm;

import java.util.Arrays;

public class LIS {
    /**
     * @param nums: The integer array
     * @return: LIS array
     */
    static public int[] longestIncreasingSubsequence(int[] nums) {
        if (nums == null || nums.length == 0) return null;

        int[] lis = new int[nums.length];
        Arrays.fill(lis, 1);

        for (int i = 1; i < nums.length; i++) {
            for (int j = 0; j < i; j++) {
                if (nums[j] <= nums[i] && (lis[i] < lis[j] + 1)) {
                    lis[i] = lis[j] + 1;
                }
            }
        }

        // get the max lis
        int max_lis = 0, index = 0;
        for (int i = 0; i < lis.length; i++) {
            if (lis[i] > max_lis) {
                max_lis = lis[i];
                index = i;
            }
        }

        // get result
        int[] result = new int[max_lis];
        for (int i = index; i >= 0; i--) {
            if (lis[i] == max_lis) {
                result[max_lis - 1] = nums[i];
                max_lis--;
            }
        }
        return result;
    }

    public static void main(String[] args) {
        int[] nums = new int[]{10, 9, 2, 5, 3, 7, 101, 18};
        Solution sol = new Solution();
        int[] result = LIS.longestIncreasingSubsequence(nums);
        for (int i : result) {
            System.out.println(i);
        }
    }
}
