package com.cw.game.leaderboard;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class LeaderboardHandlerTest {



    @Test
    public void loadLeaderboardByLevelName() {
        Leaderboard the_hard_way = LeaderboardHandler.loadLeaderboardByLevelName("The Hard Way");
        System.out.println(the_hard_way);
    }

    @Test
    public void loadLeaderboard() {
        List<Leaderboard> leaderboards = LeaderboardHandler.loadLeaderboard();
        System.out.println(leaderboards);
    }

    @Test
    public void sort() {
        List<Leaderboard> sort = LeaderboardHandler.sort(LeaderboardHandler.loadLeaderboard());
        System.out.println(sort);
    }

    @Test
    public void save() {

        Leaderboard leaderboard = new Leaderboard("The Hard Way", new ArrayList<>() {{
            add(new PlayerInfo("pinkman", 850));
        }});
        LeaderboardHandler.save(leaderboard);


    }
}