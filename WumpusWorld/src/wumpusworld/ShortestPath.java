package wumpusworld;

import java.util.ArrayList;

public class ShortestPath {

    protected int col;
    protected int row;
    protected int colGold;
    protected int rowGold;
    protected int[][] djikstra;
    protected ArrayList<int[]> nodeList;
    protected boolean[][][][] proxi;
    protected ArrayList<Duo> lastNode;
    protected ArrayList<int[]> path;

    public ShortestPath(int col, int row, int colGold, int rowGold, boolean[][][][] proxi) {
        col = col + 2;
        row = row + 2;
        this.col = col;
        this.row = row;
        this.colGold = colGold;
        this.rowGold = rowGold;
        nodeList = new ArrayList<int[]>();
        lastNode = new ArrayList<Duo>();
        path = new ArrayList<>();

        djikstra = new int[col][row];
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                djikstra[i][j] = 10000;
                int[] node = new int[2];
                node[0] = i;
                node[1] = j;
                if (i != 0 && j != 0 && i != col - 1 && j != row - 1) {
                    nodeList.add(node);
                    lastNode.add(new Duo(node, node));
                }
            }
        }
        djikstra[col - 2][1] = 0;
        this.proxi = proxi;
    }

    public void djikstra() {
        System.out.println("DJIKSTRA");
        int[] mem = new int[2];
        mem[0] = 9;
        mem[1] = 1;
        nodeList.remove(mem);
        
        while (djikstra[colGold][rowGold] == 10000) {
            mem = findMin();
            nodeList.remove(mem);
            if (proxi[mem[0]][mem[1]][mem[0] - 1][mem[1]]) {
                int[] mem2 = new int[2];
                mem2[0] = mem[0] - 1;
                mem2[1] = mem[1];
                updateDist(mem, mem2);
            }
            if (proxi[mem[0]][mem[1]][mem[0] + 1][mem[1]]) {
                int[] mem2 = new int[2];
                mem2[0] = mem[0] + 1;
                mem2[1] = mem[1];
                updateDist(mem, mem2);
            }
            if (proxi[mem[0]][mem[1]][mem[0]][mem[1] - 1]) {
                int[] mem2 = new int[2];
                mem2[0] = mem[0];
                mem2[1] = mem[1] - 1;
                updateDist(mem, mem2);
            }
            if (proxi[mem[0]][mem[1]][mem[0]][mem[1] + 1]) {
                int[] mem2 = new int[2];
                mem2[0] = mem[0];
                mem2[1] = mem[1] + 1;
                updateDist(mem, mem2);
            }

            proxi[mem[0]][mem[1]][mem[0] - 1][mem[1]] = false;
            proxi[mem[0]][mem[1]][mem[0] + 1][mem[1]] = false;
            proxi[mem[0]][mem[1]][mem[0]][mem[1] - 1] = false;
            proxi[mem[0]][mem[1]][mem[0]][mem[1] + 1] = false;

            proxi[mem[0] - 1][mem[1]][mem[0]][mem[1]] = false;
            proxi[mem[0] + 1][mem[1]][mem[0]][mem[1]] = false;
            proxi[mem[0]][mem[1] - 1][mem[0]][mem[1]] = false;
            proxi[mem[0]][mem[1] + 1][mem[0]][mem[1]] = false;
        }
    }

    public void showDjikstra() {
        System.out.println(colGold + " " + rowGold);
        for (int i = 0; i < col; i++) {
            for (int j = 0; j < row; j++) {
                if (djikstra[i][j] == 10000) {
                    System.out.print("x ");
                } else {
                    System.out.print(djikstra[i][j] + " ");
                }
            }
            System.out.println();
        }
        System.out.println("PATH");
        System.out.println(path.size());
        path();
        System.out.println("PATH OK");
        System.out.println(path.size());
        for (int i = 0; i < path.size(); i++) {
            System.out.print("[" + path.get(i)[0] + "," + path.get(i)[1] + "]");
        }
        System.out.print("[" + (col-2) + "," + 1 + "]");
        System.out.println("DJIKSTRA OK");
    }

    public int[] findMin() {
        int mini = 10000;
        int[] mem = new int[2];
        mem[0] = -1;
        mem[1] = -1;

        for (int i = 0; i < nodeList.size(); i++) {
            int[] node = nodeList.get(i);
            int nCol = node[0];
            int nRow = node[1];

            if (proxi[nCol][nRow][nCol - 1][nRow] && djikstra[nCol - 1][nRow] < mini) {
                mini = djikstra[nCol - 1][nRow];
                mem[0] = nCol - 1;
                mem[1] = nRow;
            }
            if (proxi[nCol][nRow][nCol + 1][nRow] && djikstra[nCol + 1][nRow] < mini) {
                mini = djikstra[nCol + 1][nRow];
                mem[0] = nCol + 1;
                mem[1] = nRow;
            }
            if (proxi[nCol][nRow][nCol][nRow + 1] && djikstra[nCol][nRow + 1] < mini) {
                mini = djikstra[nCol][nRow + 1];
                mem[0] = nCol;
                mem[1] = nRow + 1;
            }
            if (proxi[nCol][nRow][nCol][nRow - +1] && djikstra[nCol][nRow - 1] < mini) {
                mini = djikstra[nCol][nRow - 1];
                mem[0] = nCol;
                mem[1] = nRow - 1;
            }
        }
        return mem;
    }

    public void getProxi(int[] current) {

    }

    public void updateDist(int[] node1, int[] node2) {
        if (djikstra[node2[0]][node2[1]] > djikstra[node1[0]][node1[1]] + 1) {
            djikstra[node2[0]][node2[1]] = djikstra[node1[0]][node1[1]] + 1;
            for (int i = 0; i < lastNode.size(); i++) {
                if (lastNode.get(i).getFirst()[0] == node2[0] && lastNode.get(i).getFirst()[1] == node2[1]) {
                    lastNode.get(i).setSecond(node1);
                }
            }
        }
    }

    public void path() {
        int itX = colGold;
        int itY = rowGold;

        while (itX != col - 2 || itY != 1) {
            int tempItX = -1;
            int tempItY = -1;
            int[] node = new int[2];
            node[0] = itX;
            node[1] = itY;
            path.add(node);
            for (int i = 0; i < lastNode.size(); i++) {
                if (lastNode.get(i).getFirst()[0] == itX && lastNode.get(i).getFirst()[1] == itY) {
                    tempItX = lastNode.get(i).getSecond()[0];
                    tempItY = lastNode.get(i).getSecond()[1];
                }
            }
            itX = tempItX;
            itY = tempItY;
        }
    }
}
/*

A = suite vide
2 s := sfin
3 tant que s != sdeb faire
4   A = A + s                  on ajoute s à la suite A 
5   s = prédécesseur[s]        on continue de suivre le chemin 
6 fin tant que

Dijkstra(G,Poids,sdeb)
1 Initialisation(G,sdeb)
2 Q := ensemble de tous les nœuds
3 tant que Q n'est pas un ensemble vide faire
4       s1 := Trouve_min(Q)
5       Q := Q privé de s1
6       pour chaque nœud s2 voisin de s1 faire
7           maj_distances(s1,s2)
8       fin pour
9 fin tant que

maj_distances(s1,s2)
1 si d[s2] > d[s1] + Poids(s1,s2)        Si la distance de sdeb à s2 est plus grande que 
2                                        celle de sdeb à S1 plus celle de S1 à S2 
3    alors 
4        d[s2] := d[s1] + Poids(s1,s2)   On prend ce nouveau chemin qui est plus court
5        prédécesseur[s2] := s1          En notant par où on passe 
 */
