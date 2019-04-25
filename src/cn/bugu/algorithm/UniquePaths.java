package cn.bugu.algorithm;

public class UniquePaths {
    public static void main(String[] args) {
        int[][] map = {{1,5,2,8},{5,-9,10,-3},{2,7,-5,-18},{2,4,1,18}};
        System.out.print(path(map, 1,1));
    }
    static public int path(int[][] array, int x, int y) {
        int width = array[0].length;
        int height = array.length;
        int[][] distance = new int[height][width];
        for (int col=0; col<width; col++) {
            distance[0][col] = 1;
        }

        for (int row=0; row<height; row++) {
            distance[row][0] = 1;
        }

        for (int row=1; row<height; row++) {
            for (int col=1; col<height; col++) {
                if (array[row][col] < 0) {
                    distance[row][col] = 0;
                } else
                    distance[row][col] = distance[row-1][col] + distance[row][col-1];
            }
        }

        return distance[x][y];
    }
}
