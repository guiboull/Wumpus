package wumpusworld;

public class Board {

    protected Cell board[][];
    protected boolean proxiBoard[][][][];
    protected int col;
    protected int row;
    protected int colGold;
    protected int rowGold;
    protected boolean monsterAlive;
    protected boolean playerAlive;
    protected String cheminOpti;
    protected String cheminPasTropOpti;

    public Board(int x, int y) {
        col = x + 2;
        row = y + 2;
        board = new Cell[col][row];
        proxiBoard = new boolean[col][row][col][row];

        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                board[i][j] = new Cell();
                if (i == 0 || j == 0 || i == col - 1 || j == row - 1) {
                    board[i][j].setWall(true);
                } else {
                    int newCol = i;
                    int newRow = j;
                    if (newCol > 1) {
                        proxiBoard[newCol][newRow][newCol - 1][newRow] = true;
                        proxiBoard[newCol - 1][newRow][newCol][newRow] = true;
                    }
                    if (newCol < col - 2) {
                        proxiBoard[newCol][newRow][newCol + 1][newRow] = true;
                        proxiBoard[newCol + 1][newRow][newCol][newRow] = true;
                    }
                    if (newRow > 1) {
                        proxiBoard[newCol][newRow][newCol][newRow - 1] = true;
                        proxiBoard[newCol][newRow - 1][newCol][newRow] = true;
                    }
                    if (newRow < row - 2) {
                        proxiBoard[newCol][newRow][newCol][newRow + 1] = true;
                        proxiBoard[newCol][newRow + 1][newCol][newRow] = true;
                    }
                }
            }
        }
        monsterAlive = true;
        playerAlive = true;
        cheminPasTropOpti = "";
        for (int i = 0; i < (col * row); i++) {
            cheminPasTropOpti += "oo ";
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(int col, int y) {
        return board[col][row];
    }

    public Cell getCell(int i, int j, Direction dir) {
        switch (dir) {
            case N:
                return board[i - 1][j];
            case S:
                return board[i + 1][j];
            case E:
                return board[i][j + 1];
            case W:
                return board[i][j - 1];
        }
        return board[0][0];
    }

    public boolean getMonsterAlive() {
        return monsterAlive;
    }

    public boolean getPlayerAlive() {
        return playerAlive;
    }

    public String getCheminPasTropOpti() {
        return cheminPasTropOpti;
    }

    public String getCheminOpti() {
        return cheminOpti;
    }

    public void getProxi(int x, int y) {

        if (proxiBoard[x][y][x - 1][y]) {
            System.out.print("[" + (x - 1) + "," + y + "] ");
        }
        if (proxiBoard[x][y][x + 1][y]) {
            System.out.print("[" + (x + 1) + "," + y + "] ");
        }
        if (proxiBoard[x][y][x][y - 1]) {
            System.out.print("[" + x + "," + (y - 1) + "] ");
        }
        if (proxiBoard[x][y][x][y + 1]) {
            System.out.print("[" + x + "," + (y + 1) + "] ");
        }
    }

    public void setMonster(boolean b) {
        monsterAlive = b;
    }

    public void setBoard() {
        board[1][9].setPlayer(true);
        board[5][9].setGold(true);
    }

    public void setPlayer(boolean b) {
        playerAlive = b;
    }

    public void showBoard() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (board[i][j].getWall() == true) {
                    System.out.print("x ");
                } else if (board[i][j].getHole() == true) {
                    System.out.print("o ");
                } else if (board[i][j].getMonster() == true) {
                    System.out.print("m ");
                } else if (board[i][j].getPlayer() == true) {
                    System.out.print("p ");
                } else if (board[i][j].getGold() == true) {
                    System.out.print("g ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void showBoard2() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (board[i][j].getWind() == true) {
                    System.out.print("w ");
                } else if (board[i][j].getSmell() == true) {
                    System.out.print("s ");
                } else if (board[i][j].getWall() == true) {
                    System.out.print("x ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void showBoard3() {
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (board[i][j].getWall() == true) {
                    System.out.print("x  ");
                } else {
                    System.out.print(i + "" + j + " ");
                }
            }
            System.out.println();
        }
    }

    public void setBoard(int holes) {
        boolean ok = false;
        int i;
        int j;
        board[col - 2][1].setPlayer(true);

        while (ok == false) {
            i = (int) (Math.random() * (col - 2)) + 1;
            j = (int) (Math.random() * (row - 2)) + 1;
            if (board[i][j].getPlayer() == false) {
                board[i][j].setMonster(true);

                proxiBoard[i][j][i - 1][j] = false;
                proxiBoard[i][j][i + 1][j] = false;
                proxiBoard[i][j][i][j + 1] = false;
                proxiBoard[i][j][i][j - 1] = false;
                proxiBoard[i - 1][j][i][j] = false;
                proxiBoard[i + 1][j][i][j] = false;
                proxiBoard[i][j + 1][i][j] = false;
                proxiBoard[i][j - 1][i][j] = false;

                board[i][j].setSmell(true);
                board[i + 1][j].setSmell(true);
                board[i - 1][j].setSmell(true);
                board[i][j + 1].setSmell(true);
                board[i][j - 1].setSmell(true);

                ok = true;
            }
        }
        ok = false;
        while (ok == false) {
            i = (int) (Math.random() * (col - 2)) + 1;
            j = (int) (Math.random() * (row
                    - 2)) + 1;
            if (board[i][j].getPlayer() == false && board[i][j].getMonster() == false) {
                board[i][j].setGold(true);
                colGold = i;
                rowGold = j;
                ok = true;
            }
        }
        ok = false;

        for (int it = 0; it < holes; it++) {
            while (ok == false) {
                i = (int) (Math.random() * (col - 2)) + 1;
                j = (int) (Math.random() * (row - 2)) + 1;
                if (board[i][j].getPlayer() == false && board[i][j].getMonster() == false && board[i][j].getHole() == false && board[i][j].getGold() == false) {
                    board[i][j].setHole(true);

                    proxiBoard[i][j][i - 1][j] = false;
                    proxiBoard[i][j][i + 1][j] = false;
                    proxiBoard[i][j][i][j + 1] = false;
                    proxiBoard[i][j][i][j - 1] = false;
                    proxiBoard[i - 1][j][i][j] = false;
                    proxiBoard[i + 1][j][i][j] = false;
                    proxiBoard[i][j + 1][i][j] = false;
                    proxiBoard[i][j - 1][i][j] = false;

                    board[i + 1][j].setWind(true);
                    board[i - 1][j].setWind(true);
                    board[i][j + 1].setWind(true);
                    board[i][j - 1].setWind(true);
                    ok = true;
                } else {
                    System.out.println("RATE");
                }
            }
            ok = false;
        }
    }

    public void cheminPasTropOpti(int i, int j, Direction dir, String mem, int t) {
        if (board[i][j].getGold()) {
            mem += i;
            mem += j;
            mem += " ";
            if (mem.length() < cheminPasTropOpti.length()) {
                cheminPasTropOpti = mem;
            }
        } else if (t == 0) {
            //mem += "Chemin mort";
            //System.out.println(mem);
        } else {
            mem += i;
            mem += j;
            mem += " ";
            t -= 1;
            if (dir != Direction.N && getCell(i, j, Direction.S).isSafe()) {
                cheminPasTropOpti(i + 1, j, Direction.S, mem, t);
            }
            if (dir != Direction.S && getCell(i, j, Direction.N).isSafe()) {
                cheminPasTropOpti(i - 1, j, Direction.N, mem, t);
            }
            if (dir != Direction.E && getCell(i, j, Direction.W).isSafe()) {
                cheminPasTropOpti(i, j - 1, Direction.W, mem, t);
            }
            if (dir != Direction.W && getCell(i, j, Direction.E).isSafe()) {
                cheminPasTropOpti(i, j + 1, Direction.E, mem, t);
            }
        }
    }

    public void cheminOpti(int i, int j) {

    }
}
