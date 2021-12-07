package com.cw.game.leaderboard;

/**
 * @author tanghui
 * @date 2021/12/7 12:34 下午
 */
public class PlayerInfo {
    private String name;
    private int point;

    public PlayerInfo() {
    }

    public PlayerInfo(String name, int point) {
        this.name = name;
        this.point = point;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPoint() {
        return point;
    }

    public void setPoint(int point) {
        this.point = point;
    }

    @Override
    public String toString() {
        return "PlayerInfo{" +
                "name='" + name + '\'' +
                ", point=" + point +
                '}';
    }
}
