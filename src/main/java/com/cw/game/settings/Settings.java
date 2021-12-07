package com.cw.game.settings;

import com.alibaba.fastjson.JSONObject;
import com.cw.utils.Tool;
import com.cw.controller.LanguageType;
import com.cw.utils.logger.Logger;

import java.io.File;
import java.io.FileWriter;
import java.io.Serializable;
import java.nio.file.Files;
import java.util.ResourceBundle;

public class Settings implements Serializable {

    private  String username;
    private  GameSize gameSize;
    private  LanguageType language;
    private  double volume;
    private  boolean muted;

    private transient File file;

    public Settings(boolean init) {
        String path = Tool.getDir() + Tool.getConfig("setting.filename");
        file = new File(path);
        if (file.exists()) {
            load();
        } else {
            initDefaultSettings();
            save();
        }
    }

    public Settings() {}

    private void initDefaultSettings() {
        gameSize = GameSize.NORMAL;
        language = LanguageType.ENGLISH;
        muted = false;
        volume = 0.5;
        username = "";
    }

    public void save() {
        try {
            FileWriter fileWriter = new FileWriter(file);
            String json = JSONObject.toJSONString(this);
            fileWriter.write(json);
            fileWriter.flush();
            fileWriter.close();
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public void load() {
        try {
            String jsonContent = new String(Files.readAllBytes(file.toPath()));
            Settings loadedSettings = JSONObject.parseObject(jsonContent, Settings.class);
            this.gameSize = loadedSettings.getGameSize();
            this.language = loadedSettings.getLanguage();
            this.muted = loadedSettings.getMuted();
            this.volume = loadedSettings.getVolume();
            this.username = loadedSettings.getUsername();
            // MORE settings go here
        } catch (Exception e) {
            Logger.error(e);
        }
    }

    public GameSize getGameSize() {
        return gameSize;
    }

    public void setGameSize(GameSize gameSize) {
        this.gameSize = gameSize;
    }

    public LanguageType getLanguage() {
        return language;
    }

    public void setLanguage(LanguageType language) {
        this.language = language;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public boolean getMuted() {
        return muted;
    }

    public void setMuted(boolean muted) {
        this.muted = muted;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Override
    public String toString() {
        return "Settings{" +
                "username='" + username + '\'' +
                ", gameSize=" + gameSize +
                ", language=" + language +
                ", volume=" + volume +
                ", muted=" + muted +
                '}';
    }
}