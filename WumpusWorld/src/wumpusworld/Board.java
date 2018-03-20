package wumpusworld;

import wumpusworld.Kevins.Kevin;
import wumpusworld.Kevins.KevinTheFirst;
import wumpusworld.Kevins.KevinRedoublant;
import wumpusworld.Kevins.KevinTheBright;

public class Board {

    public Cell board[][];
    public boolean proxiBoard[][][][];
    public int col;
    public int row;
    public int colGold;
    public int rowGold;
    public boolean monsterAlive;
    public boolean playerAlive;
    public String cheminOpti;
    public String cheminPasTropOpti;
    public boolean displayMode;
    public boolean fogMode;

    public Kevin kevin;
    public int kevinX;
    public int kevinY;

    public Board(int Col, int Row) {
        col = Col + 2;
        row = Row + 2;
        board = new Cell[col][row];
        proxiBoard = new boolean[col][row][col][row];
        displayMode = true;
        fogMode = true;

        for (int countCol = 0; countCol < col; countCol++) {
            for (int countRow = 0; countRow < row; countRow++) {
                board[countCol][countRow] = new Cell();
                if (countCol == 0 || countRow == 0 || countCol == col - 1 || countRow == row - 1) {
                    board[countCol][countRow].setWall(true);
                } else {
                    int newCol = countCol;
                    int newRow = countRow;
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
        for (int countCol = 0; countCol < (col * row); countCol++) {
            cheminPasTropOpti += "oo ";
        }
    }

    public Cell[][] getBoard() {
        return board;
    }

    public Cell getCell(int col, int row) {
        return board[col][row];
    }

    public boolean[][][][] getProxiBoard() {
        return proxiBoard;
    }

    public void setProxiBoard(boolean[][][][] proxiBoard) {
        this.proxiBoard = proxiBoard;
    }

    public Cell getCell(int countCol, int countRow, Direction dir) {
        switch (dir) {
            case N:
                return board[countCol - 1][countRow];
            case S:
                return board[countCol + 1][countRow];
            case E:
                return board[countCol][countRow + 1];
            case W:
                return board[countCol][countRow - 1];
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

    public void getProxi(int countCol, int countRow) {

        if (proxiBoard[countCol][countRow][countCol - 1][countRow]) {
            System.out.print("[" + (countCol - 1) + "," + countRow + "] ");
        }
        if (proxiBoard[countCol][countRow][countCol + 1][countRow]) {
            System.out.print("[" + (countCol + 1) + "," + countRow + "] ");
        }
        if (proxiBoard[countCol][countRow][countCol][countRow - 1]) {
            System.out.print("[" + countCol + "," + (countRow - 1) + "] ");
        }
        if (proxiBoard[countCol][countRow][countCol][countRow + 1]) {
            System.out.print("[" + countCol + "," + (countRow + 1) + "] ");
        }
    }

    public void setMonster(boolean b) {
        monsterAlive = b;
    }

    public void setPlayer(boolean b) {
        playerAlive = b;
    }

    public void showBoard() {
        for (int countCol = 0; countCol < col; countCol++) {
            for (int countRow = 0; countRow < row; countRow++) {
                if (board[countCol][countRow].getWall() == true) {
                    System.out.print("x ");
                } else if (board[countCol][countRow].getHole() == true) {
                    System.out.print("o ");
                } else if (board[countCol][countRow].getMonster() == true) {
                    System.out.print("m ");
                } else if (board[countCol][countRow].getPlayer() == true) {
                    System.out.print("p ");
                } else if (board[countCol][countRow].getGold() == true) {
                    System.out.print("g ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void showBoard2() {
        for (int countCol = 0; countCol < col; countCol++) {
            for (int countRow = 0; countRow < row; countRow++) {
                if (board[countCol][countRow].getWind() == true) {
                    System.out.print("w ");
                } else if (board[countCol][countRow].getSmell() == true) {
                    System.out.print("s ");
                } else if (board[countCol][countRow].getWall() == true) {
                    System.out.print("x ");
                } else {
                    System.out.print("  ");
                }
            }
            System.out.println();
        }
    }

    public void showBoard3() {
        for (int countCol = 0; countCol < col; countCol++) {
            for (int countRow = 0; countRow < row; countRow++) {
                if (board[countCol][countRow].getWall() == true) {
                    System.out.print("x  ");
                } else {
                    System.out.print(countCol + "" + countRow + " ");
                }
            }
            System.out.println();
        }
    }

    public void moveKevin(int x, int y) {
        // maj des cells du board
        board[kevinY][kevinX].hasKevin = false;
        board[kevinY - y][kevinX + x].hasKevin = true;
        board[kevinY - y][kevinX + x].fog = false;
        kevinX = kevinX + x;
        kevinY = kevinY - y;
        // maj des variables de kevin
        kevin.currentCell = board[kevinY][kevinX];
    }

    public void setBoard(int holes) {
        boolean ok = false;
        int countCol;
        int countRow;
        kevinX = 1;
        kevinY = col - 2;
        board[kevinY][kevinX].setPlayer(true);
        board[kevinY][kevinX].setFog(false);
        kevin = new KevinTheBright();
        kevin.currentCell = board[kevinX][kevinY];

        while (ok == false) {
            countCol = (int) (Math.random() * (col - 2)) + 1;
            countRow = (int) (Math.random() * (row - 2)) + 1;
            if (board[countCol][countRow].getPlayer() == false) {
                board[countCol][countRow].setMonster(true);

                proxiBoard[countCol][countRow][countCol - 1][countRow] = false;
                proxiBoard[countCol][countRow][countCol + 1][countRow] = false;
                proxiBoard[countCol][countRow][countCol][countRow + 1] = false;
                proxiBoard[countCol][countRow][countCol][countRow - 1] = false;
                proxiBoard[countCol - 1][countRow][countCol][countRow] = false;
                proxiBoard[countCol + 1][countRow][countCol][countRow] = false;
                proxiBoard[countCol][countRow + 1][countCol][countRow] = false;
                proxiBoard[countCol][countRow - 1][countCol][countRow] = false;

                board[countCol][countRow].setSmell(true);
                board[countCol + 1][countRow].setSmell(true);
                board[countCol - 1][countRow].setSmell(true);
                board[countCol][countRow + 1].setSmell(true);
                board[countCol][countRow - 1].setSmell(true);

                ok = true;
            }
        }
        ok = false;
        while (ok == false) {
            countCol = (int) (Math.random() * (col - 2)) + 1;
            countRow = (int) (Math.random() * (row
                    - 2)) + 1;
            if (board[countCol][countRow].getPlayer() == false && board[countCol][countRow].getMonster() == false) {
                board[countCol][countRow].setGold(true);
                colGold = countCol;
                rowGold = countRow;
                ok = true;
            }
        }
        ok = false;

        for (int it = 0; it < holes; it++) {
            while (ok == false) {
                countCol = (int) (Math.random() * (col - 2)) + 1;
                countRow = (int) (Math.random() * (row - 2)) + 1;
                if (board[countCol][countRow].getPlayer() == false && board[countCol][countRow].getMonster() == false && board[countCol][countRow].getHole() == false && board[countCol][countRow].getGold() == false) {
                    board[countCol][countRow].setHole(true);

                    proxiBoard[countCol][countRow][countCol - 1][countRow] = false;
                    proxiBoard[countCol][countRow][countCol + 1][countRow] = false;
                    proxiBoard[countCol][countRow][countCol][countRow + 1] = false;
                    proxiBoard[countCol][countRow][countCol][countRow - 1] = false;
                    proxiBoard[countCol - 1][countRow][countCol][countRow] = false;
                    proxiBoard[countCol + 1][countRow][countCol][countRow] = false;
                    proxiBoard[countCol][countRow + 1][countCol][countRow] = false;
                    proxiBoard[countCol][countRow - 1][countCol][countRow] = false;

                    board[countCol + 1][countRow].setWind(true);
                    board[countCol - 1][countRow].setWind(true);
                    board[countCol][countRow + 1].setWind(true);
                    board[countCol][countRow - 1].setWind(true);
                    ok = true;
                } else {
                    System.out.println("RATE");
                }
            }
            ok = false;
        }
    }

    public void cheminPasTropOpti(int countCol, int countRow, Direction dir, String mem, int t) {
        if (board[countCol][countRow].getGold()) {
            mem += countCol;
            mem += countRow;
            mem += " ";
            if (mem.length() < cheminPasTropOpti.length()) {
                cheminPasTropOpti = mem;
            }
        } else if (t == 0) {
            //mem += "Chemin mort";
            //System.out.println(mem);
        } else {
            mem += countCol;
            mem += countRow;
            mem += " ";
            t -= 1;
            if (dir != Direction.N && getCell(countCol, countRow, Direction.S).isSafe()) {
                cheminPasTropOpti(countCol + 1, countRow, Direction.S, mem, t);
            }
            if (dir != Direction.S && getCell(countCol, countRow, Direction.N).isSafe()) {
                cheminPasTropOpti(countCol - 1, countRow, Direction.N, mem, t);
            }
            if (dir != Direction.E && getCell(countCol, countRow, Direction.W).isSafe()) {
                cheminPasTropOpti(countCol, countRow - 1, Direction.W, mem, t);
            }
            if (dir != Direction.W && getCell(countCol, countRow, Direction.E).isSafe()) {
                cheminPasTropOpti(countCol, countRow + 1, Direction.E, mem, t);
            }
        }
    }
}
