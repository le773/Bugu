package cn.bugu.algorithm;

public class MyMaze {
    private static int startPosI;
    private static int startPosJ;
    private static int endPosI;
    private static int endPosJ;

    public void setStart(int startPosI, int startPosJ) {
        MyMaze.startPosI = startPosI;
        MyMaze.startPosJ = startPosJ;
    }

    public void setEnd(int endPosI, int endPosJ) {
        MyMaze.endPosI = endPosI;
        MyMaze.endPosJ = endPosJ;
    }

    public static void visited(int[][] cell, int i, int j) {
        cell[i][j] = 1;
        if (i == endPosI && j == endPosJ) {
            System.out.println("good way:");
            for (int m = 0; m < cell.length; m++) {
                for (int n = 0; n < cell[0].length; n++) {
                    if (cell[m][n] == 2) {
                        System.out.print("2 ");
                    } else if (cell[m][n] == 1) {
                        System.out.print("* ");
                    } else {
                        System.out.print("  ");
                    }
                }
                System.out.println("");
            }
            System.out.println("  ");
        }

        if (cell[i][j - 1] == 0) {
            visited(cell, i, j - 1);
        }

        if (cell[i][j + 1] == 0) {
            visited(cell, i, j + 1);
        }

        if (cell[i - 1][j] == 0) {
            visited(cell, i - 1, j);
        }

        if (cell[i + 1][j] == 0) {
            visited(cell, i + 1, j);
        }
        cell[i][j] = 0;
    }

    public static void main(String[] args) {
        int[][] maze = {
                {2,2,2,2,2,2,2,2,2},
                {2,0,0,0,0,0,0,0,2},
                {2,0,2,2,0,2,2,0,2},
                {2,0,2,0,0,2,0,0,2},
                {2,0,2,0,2,0,2,0,2},
                {2,0,0,0,0,0,2,0,2},
                {2,2,0,2,2,0,2,2,2},
                {2,0,0,0,0,0,0,0,2},
                {2,2,2,2,2,2,2,2,2},
        };

        MyMaze cell = new MyMaze();
        cell.setStart(1,1);
        cell.setEnd(7,7);
        cell.visited(maze, 1, 1);
        while (true) {}
    }
}
