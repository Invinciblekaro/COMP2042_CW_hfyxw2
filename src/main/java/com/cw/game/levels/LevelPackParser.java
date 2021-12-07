package com.cw.game.levels;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.cw.game.paddle.PaddleSize;
import com.cw.utils.logger.Logger;

import java.util.ArrayList;

public class LevelPackParser {

    private String fileContents;
    private LevelPack levelPack;

    public LevelPackParser(String fileContents) {
        this.fileContents = fileContents;
        read();
    }

    private ArrayList<Level> parseLevels(JSONArray levelPackArray) {

        ArrayList<Level> levels = new ArrayList<>();
        for (Object levelJson : levelPackArray) {
            JSONObject jsonObject = (JSONObject) levelJson;

            String levelName = jsonObject.getString("name");
            String levelAuthor = jsonObject.getString("author");
            int levelPosition = jsonObject.getIntValue("position");
            int difficulty = jsonObject.getIntValue("difficulty");
            int startLives = jsonObject.getIntValue("startLives");
            String initPadSize = jsonObject.getString("initPadSize");
            String leaderboard = jsonObject.getString("leaderboard");


            PaddleSize padSize;

            try {
                padSize = PaddleSize.valueOf(initPadSize);
            } catch (Exception e) {
                padSize = PaddleSize.MEDIUM;
            }

            String boardString = jsonObject.getString("board");
            Level level = new Level(levelName, levelAuthor, levelPosition, difficulty, startLives, padSize, boardString,leaderboard);
            levels.add(level);
        }

        levels.sort((o1, o2) -> {
            if (o1.getPosition() == o2.getPosition()) {
                return 0;
            }

            if (o1.getPosition() > o2.getPosition()) {
                return 1;
            } else {
                return -1;
            }
        });

        return levels;
    }

    private void read() {
        try {
            JSONObject root = JSONObject.parseObject(fileContents);
            String packageName = root.getString("name");
            String packageAuthor = root.getString("author");
            String packageVersion = root.getString("version");
            ArrayList<Level> levels = parseLevels(root.getJSONArray("levelPack"));
            levelPack = new LevelPack(packageName, packageAuthor, packageVersion, levels);
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public LevelPack getLevelPack() {
        return levelPack;
    }
}