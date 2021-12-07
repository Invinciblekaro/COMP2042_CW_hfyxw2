package com.cw.game.leaderboard;

import java.util.List;

/**
 * @author tanghui
 * @date 2021/12/7 12:26 下午
 */
public class Leaderboard {

    private String levelName;

    private List<PlayerInfo> playerInfos;


    public Leaderboard(String levelName, List<PlayerInfo> playerInfos) {
        this.levelName = levelName;
        this.playerInfos = playerInfos;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public List<PlayerInfo> getPlayerInfos() {
        return playerInfos;
    }

    public void setPlayerInfos(List<PlayerInfo> playerInfos) {
        this.playerInfos = playerInfos;
    }

    @Override
    public String toString() {
        return "Leaderboard{" +
                "levelName='" + levelName + '\'' +
                ", playerInfos=" + playerInfos +
                '}';
    }
}
