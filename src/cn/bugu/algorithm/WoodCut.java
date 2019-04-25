package cn.bugu.algorithm;

public class WoodCut {

    public int getMaxLength(int[] array, int k) {
        int sum = 0, avg = 0;
        for (int i = 0; i < array.length; i++) {
            sum += array[i];
        }

        avg = sum / k;
        System.out.println("avg=" + avg);
        for (int i = avg; i > 0; i--) {
            int count = 0;
            System.out.println("i=" + i);
            for (int j = 0; j < array.length; j++) {
                System.out.println("   split=" + array[j] / i);
                count += Math.floor(array[j] / i);
            }
            System.out.println("   count=" + count);
            if (count >= k)
                return i;
        }
        return -1;
    }

    public int getBinLength(int[] array, int k) {
        int tmpMax = 0;
        for (int i = 0; i < array.length; i++) {
            if (array[i] > tmpMax)
                tmpMax = array[i];
        }
        int start = 0, end = tmpMax, mid = 0, count = 0;
        while (start < end) {
            mid = start + (end - start) / 2;
            System.out.println("start=" + start + ", end=" + end + ", mid=" + mid);
            count = 0;
            for (int j = 0; j < array.length; j++) {
                //System.out.println("   split=" + array[j] / mid);
                count += Math.floor(array[j] / mid);
                if (count > k)
                    break;
            }
            System.out.println("count=" + count + ", mid=" + mid);
            if (count > k)
                start = mid + 1;
            else if (count < k)
                end = mid -1;
            else
                return mid;
        }
        return -1;
    }

    public static void main(String[] args) {
        int[] L = {232, 124, 456};
        int k = 8;

        WoodCut woodCut = new WoodCut();
        System.out.println(woodCut.getMaxLength(L, k));
        System.out.println("--------------------------------------");
        System.out.println(woodCut.getBinLength(L, k));
    }

}
