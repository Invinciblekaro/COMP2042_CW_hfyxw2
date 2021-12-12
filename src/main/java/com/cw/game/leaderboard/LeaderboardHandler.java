package com.cw.game.leaderboard;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cw.utils.Tool;
import com.cw.utils.logger.Logger;

import java.io.File;
import java.io.FileWriter;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

/**
 * @author tanghui
 * @date 2021/12/7 12:26 下午
 */
public class LeaderboardHandler {

    public static Leaderboard loadLeaderboardByLevelName(String levelName){
        List<Leaderboard> leaderboards = loadLeaderboard();


        for (int i = 0; i < leaderboards.size(); i++) {
            Leaderboard leaderboard = leaderboards.get(i);
            if (Objects.equals(leaderboard.getLevelName(),levelName)) {
                return leaderboard;
            }
        }

        return new Leaderboard(levelName, new ArrayList<>());
    }

    public static List<Leaderboard> loadLeaderboard(){
        String path = Tool.getDir() + Tool.getConfig("leaderboard.filename");
        File file = new File(path);
        List<Leaderboard> array = new ArrayList<>();
        try {
            String jsonContent = new String(Files.readAllBytes(file.toPath()));
            array = JSONArray.parseArray(jsonContent,Leaderboard.class);
        } catch (Exception e) {
            Logger.error(e);
        }
        return array;
    }


    public static List<Leaderboard> sort(List<Leaderboard> list){
        for (Leaderboard leaderboard : list) {
//            leaderboard.getPlayerInfos().sort((x,y) -> Integer.compare(y.getPoint(), x.getPoint()));
             leaderboard.getPlayerInfos().sort(Comparator.comparing(PlayerInfo::getPoint));
        }
        return list;
    }

    public static void save(List<Leaderboard> list){
        String path = Tool.getDir() + Tool.getConfig("leaderboard.filename");
        File file = new File(path);

        try {
            FileWriter fileWriter = new FileWriter(file);
            String json = JSONObject.toJSONString(list);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public static void save(Leaderboard leaderboard){
        Logger.info("save leaderboard");
        String path = Tool.getDir() + Tool.getConfig("leaderboard.filename");
        File file = new File(path);

        List<Leaderboard> leaderboards = sort(loadLeaderboard());

        boolean match = leaderboards.stream().anyMatch(p -> Objects.equals(p.getLevelName(), leaderboard.getLevelName()));

        if (match) {
            for (int i = 0; i < leaderboards.size(); i++) {
                Leaderboard leaderboard1 = leaderboards.get(i);
                if (Objects.equals(leaderboard1.getLevelName(),leaderboard.getLevelName())) {
                    leaderboards.set(i, leaderboard);
                    break;
                }
            }
        } else {
            leaderboards.add(leaderboard);
        }


        try {
            FileWriter fileWriter = new FileWriter(file);
            String json = JSONObject.toJSONString(leaderboards);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Logger.error(e);
        }
    }



    public static void main(String[] args) {
        System.out.println(sort(loadLeaderboard()));
    }

}
