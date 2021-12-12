package com.cw.game.levels;


import com.cw.game.paddle.PaddleSize;

public class Level
{
    private String name;
    private String author;
    private int position;
    private int difficulty;
    private int startLives;
    private PaddleSize initPadSize;
    private String board;
    private String leaderboard;

    public Level(String name, String author, int position, int difficulty, int startLives, PaddleSize initPadSize, String board,String leaderboard)
    {
        this.name = name;
        this.author = author;
        this.position = position;
        this.difficulty = difficulty;
        this.startLives = startLives;
        this.initPadSize = initPadSize;
        this.board = board;
        this.leaderboard = leaderboard;
    }

	public String getName()
	{
		return name;
	}

	public String getAuthor()
	{
		return author;
	}

	public int getPosition()
	{
		return position;
	}

	public int getDifficulty()
	{
		return difficulty;
	}

	public int getStartLives()
	{
		return startLives;
	}

	public PaddleSize getInitPadSize()
	{
		return initPadSize;
	}

	public String getBoard()
	{
		return board;
	}

	public String getLeaderboard() {
		return leaderboard;
	}

	public void setLeaderboard(String leaderboard) {
		leaderboard = leaderboard;
	}

	@Override
	public String toString() {
		return "Level{" +
				"name='" + name + '\'' +
				", author='" + author + '\'' +
				", position=" + position +
				", difficulty=" + difficulty +
				", startLives=" + startLives +
				", initPadSize=" + initPadSize +
				", board='" + board + '\'' +
				", leaderboard='" + leaderboard + '\'' +
				'}';
	}
}