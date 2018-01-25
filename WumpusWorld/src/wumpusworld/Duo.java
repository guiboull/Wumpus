package wumpusworld;

public class Duo {
    protected int[] first;
    protected int[] second;

    public Duo() {
    }

    public Duo(int[] first, int[] second) {
        this.first = first;
        this.second = second;
    }

    public int[] getFirst() {
        return first;
    }

    public void setFirst(int[] first) {
        this.first = first;
    }

    public int[] getSecond() {
        return second;
    }

    public void setSecond(int[] second) {
        this.second = second;
    }
    
    
}
