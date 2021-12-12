package com.cw.game.levels;

import com.alibaba.fastjson.JSONObject;
import com.cw.utils.Tool;
import junit.framework.TestCase;
import org.junit.Test;

import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

/**
 * @author Wang_xubin
 * @date 2021/12/2 7:09 下午
 */
public class LevelPackHandlerTest  {


    LevelPackHandler handler = new LevelPackHandler();

    @Test
    public void testSort() {
    }

    @Test
    public void testGetAllLevelPacks() {
        ArrayList<LevelPack> allLevelPacks = LevelPackHandler.getAllLevelPacks();

        System.out.println(allLevelPacks);
    }

    @Test
    public void testGetFilesFromJar() throws IOException {
        ArrayList<String> filesFromJar = handler.getFilesFromJar();
        System.out.println(filesFromJar);
    }

    @Test
    public void testGetFileContentFromUserDir() {
        JSONObject root = JSONObject.parseObject("{\n" +
                "  \"name\": \"Default\",\n" +
                "  \"author\": \"Wang_xubin\",\n" +
                "  \"version\": \"1.0\",\n" +
                "  \"levelPack\":\n" +
                "    [{\n" +
                "        \"name\": \"First Game\",\n" +
                "        \"author\": \"Wang_xubin\",\n" +
                "        \"position\": 1,\n" +
                "        \"difficulty\": 1,\n" +
                "        \"startLives\": 7,\n" +
                "        \"initPadSize\": \"LARGE\",\n" +
                "        \"board\": \"A0 A\"\n" +
                "\t  }]}");
        System.out.println(root);
    }

    public void testGetFileContent() {
        ArrayList<String> fileContent = handler.getFileContent();
        System.out.println(fileContent);
    }
}