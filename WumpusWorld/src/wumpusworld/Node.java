package wumpusworld;

public class Node {

    Cell cell;
    Cell nodeN;
    Cell nodeS;
    Cell nodeE;
    Cell nodeW;

    public Node(Cell cell) {
        this.cell = cell;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }

    public Cell getNodeN() {
        return nodeN;
    }

    public void setNodeN(Cell nodeN) {
        this.nodeN = nodeN;
    }

    public Cell getNodeS() {
        return nodeS;
    }

    public void setNodeS(Cell nodeS) {
        this.nodeS = nodeS;
    }

    public Cell getNodeE() {
        return nodeE;
    }

    public void setNodeE(Cell nodeE) {
        this.nodeE = nodeE;
    }

    public Cell getNodeW() {
        return nodeW;
    }

    public void setNodeW(Cell nodeW) {
        this.nodeW = nodeW;
    }
}
