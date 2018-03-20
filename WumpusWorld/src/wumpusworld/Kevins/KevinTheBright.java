package wumpusworld.Kevins;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;
import wumpusworld.ShortestPath;

/**
 * enum self board : -1 = wall 1 = safe + deja visité 2 = safe 3 = wind 4 =
 * smell 5 = possible hole 6 = possible monster 7 = hole 8 = monster 9 = gold
 */
public class KevinTheBright extends Kevin {

    int maxX = 100;
    int maxY = 100;
    public boolean proxiBoard[][][][];

    public KevinTheBright() {
        //init position kevin
        x = 1;
        y = 1;
        previousX = 1;
        previousY = 1;
        //init age kevin
        age = 12;
        isAlive = true;
        status = 0;
        //init board kevin
        board[x][y] = 0;
        board[0][0] = -1;
        board[1][0] = -1;
        board[0][1] = -1;
        //init proxi board
        proxiBoard = new boolean[maxX][maxY][maxX][maxY];

    }

    @Override
    public int[] go() {
        thinkKevinTHINK();
        int[] move = new int[2];
        move[0] = 0;
        move[1] = 0;

        switch (board[x][y]) {
            case -1:
                move[0] = previousX - x;
                move[1] = previousY - y;
                x = previousX;
                y = previousY;
                break;
            case 9:
                //cest fini
                status = 2;
                 
                break;
            case 3:
                // si ca smell on fait demi tour
                move[0] = previousX - x;
                move[1] = previousY - y;
                x = previousX;
                y = previousY;
                break;
            case 4:
                //si il y a une odeur, on fait demi tour
                move[0] = previousX - x;
                move[1] = previousY - y;
                x = previousX;
                y = previousY;
                break;
            default:
                move = randomSafeMove();
                break;
        }

        proxiBoard[previousX][previousY][x][y] = true;
        proxiBoard[x][y][previousX][previousY] = true;

        return move;
    }

    private int[] randomSafeMove() {
        int[] move = new int[2];
        move[0] = 0;
        move[1] = 0;
        boolean gotMove = false;
        
        //test si les cases adj sont a chier ( aucunes a 2 ) on cherche a se barrer
        boolean keblo = true;
        if(board[x+1][y] == 2){
            keblo = false;
        }else if(board[x][y+1] == 2){
            keblo = false;
        }else if(board[x-1][y] == 2){
            keblo = false;
        }else if(board[x][y-1] == 2){
            keblo = false;
        }
        if( keblo ){
            //coord du point sur lequel on souhaite se rendre
            int[] nearest = this.findNearestSafe();
            
            move[0] = nearest[0] - x;
            move[1] = nearest[1] - y;
            
            return move;
        }

        List<Integer> liste = new ArrayList<Integer>();
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        int nbTry = 0;
        while (!gotMove && nbTry < 4) {
            nbTry++;
            int randomNum = liste.get(ThreadLocalRandom.current().nextInt(0, liste.size()));
            switch (randomNum) {
                case 1:
                    gotMove = tryAbsPl(move);
                    break;
                case 2:
                    gotMove = tryAbsMs(move);
                    break;
                case 3:
                    gotMove = tryOrdPl(move);
                    break;
                case 4:
                    gotMove = tryOrdMs(move);
                    break;
            }

            for (int i = 0; i < liste.size(); i++) {
                if (liste.get(i) == randomNum) {
                    liste.remove(i);
                }
            }
        }
        liste.add(1);
        liste.add(2);
        liste.add(3);
        liste.add(4);
        nbTry = 0;
        while (!gotMove && nbTry < 4) {
            nbTry++;
            int randomNum = liste.get(ThreadLocalRandom.current().nextInt(0, liste.size()));
            switch (randomNum) {
                case 1:
                    gotMove = tryAbsPlSafe(move);
                    break;
                case 2:
                    gotMove = tryAbsMsSafe(move);
                    break;
                case 3:
                    gotMove = tryOrdPlSafe(move);
                    break;
                case 4:
                    gotMove = tryOrdMsSafe(move);
                    break;
            }

            for (int i = 0; i < liste.size(); i++) {
                if (liste.get(i) == randomNum) {
                    liste.remove(i);
                }
            }
        }

        return move;
    }

    private boolean tryAbsPl(int[] move) {
        if (board[x + 1][y] == 2 && x + 1 < maxX) {
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            x += 1;
            move[0] = 1;
            move[1] = 0;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryAbsMs(int[] move) {
        if (board[x - 1][y] == 2 && x - 1 != 0) {
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            x -= 1;
            move[0] = -1;
            move[1] = 0;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryOrdPl(int[] move) {
        if (board[x][y + 1] == 2 && y + 1 < maxY) {
            board[x][y] = 1;
            previousY = y;
            previousX = x;
            y += 1;
            move[0] = 0;
            move[1] = 1;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryOrdMs(int[] move) {
        if (board[x][y - 1] == 2 && y - 1 != 0) {
            board[x][y] = 1;
            previousY = y;
            previousX = x;
            y -= 1;
            move[0] = 0;
            move[1] = -1;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryAbsPlSafe(int[] move) {
        if (board[x + 1][y] == 1 && x + 1 < maxX) {
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            x += 1;
            move[0] = 1;
            move[1] = 0;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryAbsMsSafe(int[] move) {
        if (board[x - 1][y] == 1 && x - 1 != 0) {
            board[x][y] = 1;
            previousX = x;
            previousY = y;
            x -= 1;
            move[0] = -1;
            move[1] = 0;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryOrdPlSafe(int[] move) {
        if (board[x][y + 1] == 1 && y + 1 < maxY) {
            board[x][y] = 1;
            previousY = y;
            previousX = x;
            y += 1;
            move[0] = 0;
            move[1] = 1;
            return true;
        } else {
            return false;
        }
    }

    private boolean tryOrdMsSafe(int[] move) {
        if (board[x][y - 1] == 1 && y - 1 != 0) {
            board[x][y] = 1;
            previousY = y;
            previousX = x;
            y -= 1;
            move[0] = 0;
            move[1] = -1;
            return true;
        } else {
            return false;
        }
    }

    /**
     * pose des marqueurs sur la case ou se trouve kevin et sur les cases
     * autours pour savoir ce qu'il s'y trouver
     */
    private void thinkKevinTHINK() {
        if (currentCell.isWall()) {
            board[x][y] = -1;
            //determination des walls
            int diffX = previousX - x;
            int diffY = previousY - y;
            if (diffX == 0) {
                //wall sur les y
                maxY = y;
            } else {
                //wall sur les x
                maxX = x;
            }
        } else if (currentCell.isEmpty()) {
            if (board[x + 1][y] == 0) {
                board[x + 1][y] = 2;
            }
            if (board[x - 1][y] == 0) {
                board[x - 1][y] = 2;
            }
            if (board[x][y + 1] == 0) {
                board[x][y + 1] = 2;
            }
            if (board[x][y - 1] == 0) {
                board[x][y - 1] = 2;
            }
        } else if (currentCell.getGold()) {
            board[x][y] = 9;
        } else if (currentCell.getWind()) {
            board[x][y] = 3;
            if (board[x + 1][y] == 0) {
                board[x + 1][y] = 5;
            }
            if (board[x - 1][y] == 0) {
                board[x - 1][y] = 5;
            }
            if (board[x][y + 1] == 0) {
                board[x][y + 1] = 5;
            }
            if (board[x][y - 1] == 0) {
                board[x][y - 1] = 5;
            }
        } else if (currentCell.getSmell()) {
            board[x][y] = 4;
            if (board[x + 1][y] == 0) {
                board[x + 1][y] = 6;
            }
            if (board[x - 1][y] == 0) {
                board[x - 1][y] = 6;
            }
            if (board[x][y + 1] == 0) {
                board[x][y + 1] = 6;
            }
            if (board[x][y - 1] == 0) {
                board[x][y - 1] = 6;
            }
        } else {
            board[x][y] = 1;
        }
        System.out.println("etat case " + board[x][y]);
        System.out.print("");
    }

    /**
     * methode pour récupérer la position de la case la plus proche et la plus
     * safe de kevin
     *
     * @return la position sur la grille de kevin
     */
    public int[] findNearestSafe() {
        System.out.println("!!!findNearestSafe!!!");

        int[] result = new int[2];
        result[0] = x;
        result[1] = y;
        int distMax = 9999;

        List<StructCoord> listCell = new ArrayList<>();

        //selection des cases avec 2 comme valeur ( pas visité mais safe )
        for (int i = 0; i < maxX; i++) {
            for (int j = 0; j < maxY; j++) {
                if (board[i][j] == 2) {
                    StructCoord coord = new StructCoord();
                    coord.x = i;
                    coord.y = j;
                    listCell.add(coord);
                }
            }
        }

        for (int i = 0; i < listCell.size(); i++) {

            ShortestPath sp = new ShortestPath(maxX, maxY, x, y, listCell.get(i).x, listCell.get(i).y, proxiBoard);
            sp.djikstra();
            sp.path();
            if (distMax > sp.path.size()) {
                distMax = sp.path.size();
                result[0] = listCell.get(i).x;
                result[1] = listCell.get(i).y;
            }

        }

        return result;
    }

    //struct pour parcourir le tableau et sauvegarder une cell
    class StructCoord {

        int x;
        int y;
    }

}
