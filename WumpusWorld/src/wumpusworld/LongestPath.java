package wumpusworld;

import java.util.ArrayList;

public class LongestPath {

    public int countCol;
    public int countRow;
    public int t;
    public ArrayList<int[]> looooong;
    public ArrayList<int[]> nodeList;
    public Board board;

    public LongestPath(int countCol, int countRow, int t, Board board) {
        this.countCol = countCol;
        this.countRow = countRow;
        this.t = t;
        this.board = board;
        looooong = new ArrayList<int[]>();
        nodeList = new ArrayList<int[]>();
        for (int i = 0; i <= t; i++) {
            int[] node = new int[2];
            node[0] = 1;
            node[1] = 1;
            looooong.add(node);
        }
    }

    public void looooongInit() {
        if (board.getCell(countCol, countRow, Direction.S).isSafe() && !board.getCell(countCol, countRow, Direction.S).isWall()) {
            looooong(countCol + 1, countRow, Direction.S, nodeList, t, board);
        }
        if (board.getCell(countCol, countRow, Direction.N).isSafe() && !board.getCell(countCol, countRow, Direction.N).isWall()) {
            looooong(countCol - 1, countRow, Direction.N, nodeList, t, board);
        }
        if (board.getCell(countCol, countRow, Direction.W).isSafe() && !board.getCell(countCol, countRow, Direction.W).isWall()) {
            looooong(countCol, countRow - 1, Direction.W, nodeList, t, board);
        }
        if (board.getCell(countCol, countRow, Direction.E).isSafe() && !board.getCell(countCol, countRow, Direction.E).isWall()) {
            looooong(countCol, countRow + 1, Direction.E, nodeList, t, board);
        }
    }

    public void looooong(int col, int row, Direction dir, ArrayList<int[]> nodeList, int t, Board board) {
        if (board.getCell(col, row).getGold()) {
            int[] node = new int[2];
            node[0] = col;
            node[1] = row;
            nodeList.add(node);
            if (nodeList.size() < looooong.size()) {
                System.out.println("test");
                looooong = new ArrayList<int[]>();
                for (int i = 0; i < nodeList.size(); i++) {
                    looooong.add(nodeList.get(i));
                }
            }
        } else if (t == 0) {
            //System.out.println("Chemin mort");
        } else {
            int[] node = new int[2];
            node[0] = col;
            node[1] = row;
            nodeList.add(node);
            t -= 1;
            if (dir != Direction.N && board.getCell(col, row, Direction.S).isSafe() && !board.getCell(col, row, Direction.S).isWall()) {
                looooong(col + 1, row, Direction.S, nodeList, t, board);
            }
            if (dir != Direction.S && board.getCell(col, row, Direction.N).isSafe() && !board.getCell(col, row, Direction.N).isWall()) {
                looooong(col - 1, row, Direction.N, nodeList, t, board);
            }
            if (dir != Direction.E && board.getCell(col, row, Direction.W).isSafe() && !board.getCell(col, row, Direction.W).isWall()) {
                looooong(col, row - 1, Direction.W, nodeList, t, board);
            }
            if (dir != Direction.W && board.getCell(col, row, Direction.E).isSafe() && !board.getCell(col, row, Direction.E).isWall()) {
                looooong(col, row + 1, Direction.E, nodeList, t, board);
            }
        }
    }

    public void showLoooong() {
        System.out.println("LOOOOONG");
        System.out.println(looooong.size());
        for (int i = 0; i < looooong.size(); i++) {
            System.out.print("[" + looooong.get(i)[0] + "," + looooong.get(i)[1] + "]");
        }
        System.out.println("LOOOOONG OK");
    }
}
