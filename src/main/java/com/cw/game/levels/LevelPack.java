package com.cw.game.levels;


import java.util.ArrayList;

public class LevelPack
{
    private String packageName;
    private String packageAuthor;
    private String version;
    private ArrayList<Level> levels;

    public LevelPack(String packageName, String packageAuthor, String version, ArrayList<Level> levels)
    {
        this.packageName = packageName;
        this.packageAuthor = packageAuthor;
        this.version = version;
        this.levels = levels;
    }

    public String getPackageName()
    {
        return packageName;
    }

    public String getPackageAuthor()
    {
        return packageAuthor;
    }

    public String getVersion()
    {
        return version;
    }

	public ArrayList<Level> getLevels()
	{
		return levels;
	}

    @Override
    public String toString() {
        return "LevelPack{" +
                "packageName='" + packageName + '\'' +
                ", packageAuthor='" + packageAuthor + '\'' +
                ", version='" + version + '\'' +
                ", levels=" + levels +
                '}';
    }
}