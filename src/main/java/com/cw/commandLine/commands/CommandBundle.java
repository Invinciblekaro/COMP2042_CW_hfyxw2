package com.cw.commandLine.commands;

import com.cw.commandLine.CommandLineController;
import com.cw.game.Game;
import com.cw.controller.LevelController;

import java.util.ResourceBundle;


public class CommandBundle {
    private CommandLineController controller;
    private ResourceBundle languageBundle;
    private Game game;
    private boolean showFPS = false;
    private LevelController levelController;

    public CommandBundle(Game game) {
        this.game = game;
    }

    public CommandLineController getController() {
        return controller;
    }

    public ResourceBundle getLanguageBundle() {
        return languageBundle;
    }

    public void setController(CommandLineController controller) {
        this.controller = controller;
    }

    public void setLanguageBundle(ResourceBundle languageBundle) {
        this.languageBundle = languageBundle;
    }

    public Game getGame() {
        return game;
    }

    public void setGame(Game game) {
        this.game = game;
    }

    public boolean isShowFPS() {
        return showFPS;
    }

    public void setShowFPS(boolean showFPS) {
        this.showFPS = showFPS;
    }

    public LevelController getLevelController() {
        return levelController;
    }

    public void setLevelController(LevelController levelController) {
        this.levelController = levelController;
    }
}