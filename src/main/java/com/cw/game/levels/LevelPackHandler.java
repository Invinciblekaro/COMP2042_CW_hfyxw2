package com.cw.game.levels;

import com.cw.utils.Tool;
import com.cw.utils.file.FileUtils;
import com.cw.utils.logger.Logger;
import com.cw.utils.PathUtils;

import java.io.*;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Objects;
import java.util.stream.Collectors;

public class LevelPackHandler {
    private static final String DEFAULT_PACKAGE_NAME = "default";

    public static ArrayList<LevelPack> sort(ArrayList<LevelPack> levelPackArrayList) {
        //sort level packs based on their name
        levelPackArrayList.sort(Comparator.comparing(o -> o.getPackageName().toUpperCase()));

        //bring default to front
        LevelPack temp = levelPackArrayList.get(0);
        for (int i = 0; i < levelPackArrayList.size(); i++) {
            LevelPack currentPack = levelPackArrayList.get(i);
            if (currentPack.getPackageName().equalsIgnoreCase(DEFAULT_PACKAGE_NAME)) {
                //swap level packs
                levelPackArrayList.set(0, currentPack);
                levelPackArrayList.set(i, temp);
                break;
            }
        }
        return levelPackArrayList;
    }

    public static ArrayList<LevelPack> getAllLevelPacks() {
        ArrayList<LevelPack> levelPacks =

        getFileContent().stream()
                .map(LevelPackParser::new)
                .map(LevelPackParser::getLevelPack)
                .filter(Objects::nonNull)
                .collect(Collectors.toCollection(ArrayList::new));
        // for (String currentFileContent : getFileContent()) {
        //     LevelPackParser levelPackParser = new LevelPackParser(currentFileContent);
        //     levelPacks.add(levelPackParser.getLevelPack());
        // }
        return sort(levelPacks);
    }

    public static ArrayList<String> getFilesFromJar() {
        ArrayList<String> stringArrayList = new ArrayList<>();
        try {

            String text = new String(Files.readAllBytes(Tool.loadFile(Tool.getConfig("level.filepath")).toPath()));
            String[] filenamesArray = text.split(" ");

            for (String filename : filenamesArray) {
                stringArrayList.add(FileUtils.getFileContentFromJar(Tool.getConfig("level.folder") + "/" + filename));
            }

        } catch (Exception e) {
            System.out.println(e);
        }
        return stringArrayList;
    }

    public static ArrayList<String> getFileContentFromUserDir() {

        File folder = new File(Tool.getDir() + Tool.getConfig("level.folder"));
        PathUtils.checkFolder(folder);
        ArrayList<String> stringContent = new ArrayList<>();
        File[] files = folder.listFiles();
        if (files == null || files.length == 0) {
            return null;
        }
        ArrayList<File> fileArrayList = new ArrayList<>(Arrays.asList(files));

        for (File file : fileArrayList) {
            try {
                stringContent.add(new String(Files.readAllBytes(FileSystems.getDefault().getPath(file.getPath()))));
            } catch (IOException e) {
                Logger.error(e);
            }
        }
        return stringContent;
    }

    public static ArrayList<String> getFileContent() {
        ArrayList<String> fileArrayList = new ArrayList<>();
        ArrayList<String> userDir = getFileContentFromUserDir();
        ArrayList<String> jarDir = getFilesFromJar();
        if (userDir != null) {
            fileArrayList.addAll(userDir);
        }
        if (jarDir != null) {
            fileArrayList.addAll(getFilesFromJar());
        }
        return fileArrayList;
    }

}