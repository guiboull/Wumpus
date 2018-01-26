package wumpusworld;

public class Cell {

    protected boolean smell;
    protected boolean wind;

    protected boolean monster;
    protected boolean gold;
    protected boolean hole;
    protected boolean wall;
    protected boolean hasKevin;

    public Cell() {
        smell = false;
        wind = false;
        monster = false;
        gold = false;
        hole = false;
        wall = false;
        hasKevin = false;
    }

    public boolean getSmell() {
        return smell;
    }

    public boolean getWind() {
        return wind;
    }

    public boolean getMonster() {
        return monster;
    }

    public boolean getGold() {
        return gold;
    }

    public boolean getHole() {
        return hole;
    }

    public boolean getWall() {
        return wall;
    }

    public boolean getPlayer() {
        return hasKevin;
    }

    public void setSmell(boolean b) {
        smell = b;
    }

    public void setWind(boolean b) {
        wind = b;
    }

    public void setMonster(boolean b) {
        monster = b;
    }

    public void setGold(boolean b) {
        gold = b;
    }

    public void setHole(boolean b) {
        hole = b;
    }

    public void setWall(boolean b) {
        wall = b;
    }

    public void setPlayer(boolean b) {
        hasKevin = b;
    }

    public boolean isSafe() {
        if (getHole() || getWall() || getMonster()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isEmpty() {
        if (getSmell() || getWind() || getGold()) {
            return false;
        } else {
            return true;
        }
    }

    public boolean isWall() {
        return getWall();
    }
}
