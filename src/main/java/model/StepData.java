package model;

import data.PlayerData;

public class StepData {
    private PlayerData mine;
    private PlayerData enemy;
    private int time;
    private long sn;


    public PlayerData getEnemy() {
        return enemy;
    }

    public void setEnemy(PlayerData enemy) {
        this.enemy = enemy;
    }

    public PlayerData getMine() {
        return mine;
    }

    public void setMine(PlayerData mine) {
        this.mine = mine;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public long getSn() {
        return sn;
    }

    public void setSn(long sn) {
        this.sn = sn;
    }
}
