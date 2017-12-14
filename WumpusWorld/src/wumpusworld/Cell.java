package wumpusworld;

public class Cell {

    protected boolean smell;
    protected boolean wind;
    protected boolean monster;
    protected boolean gold;
    protected boolean hole;
    protected boolean wall;
    protected boolean player;
    
    public Cell() {
        smell = false;
        wind = false;
        monster = false;
        gold = false;
        hole = false;
        wall = false;
        player = false;
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
        return player;
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
        player = b;
    }

    public boolean isSafe() {
        if (getHole() || getWall() || getMonster()) {
            return false;
        }
        else {
            return true;
        }
    }
}

