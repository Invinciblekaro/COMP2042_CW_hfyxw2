package com.cw.game.board;



import com.cw.controller.LevelController;
import com.cw.game.Game;
import com.cw.game.balls.Ball;
import com.cw.game.bricks.Brick;
import com.cw.game.bricks.BrickType;
import com.cw.game.powerups.ExtraLifePowerUp;
import com.cw.game.powerups.PowerUp;
import com.cw.game.powerups.ball.ExplodeBallPowerUp;
import com.cw.game.powerups.ball.NoCollisionBallPowerUp;
import com.cw.game.sound.SoundType;

import java.util.ArrayList;

public class Board
{
	// first = row, second = column
	private ArrayList<ArrayList<Brick>> bricks;
	public static final double WIDTH = 18.0;
	public static final double HEIGHT = 26.0;
	private int points = 0;
	private Game game;

	public Board(Game game)
	{
		this.game = game;

		init();
		String boardString = game.getLevel().getBoard();	
		String[] bricksAndPowerArray = boardString.split(" ");
		ArrayList<Brick> loadedBricks = new ArrayList<>();
		for(String bricksAndPower : bricksAndPowerArray)
		{
			String brickValue = bricksAndPower.substring(0, 1);
			int powerUp = Integer.parseInt(bricksAndPower.substring(1));
			Brick currentBrick = null;
			PowerUp currentPowerUp = null;
			// TODO IMPLEMENT POWERUPS
			switch(powerUp)
			{
				case 0:
					currentPowerUp = null;
					break;
				case 1:
					currentPowerUp = new ExtraLifePowerUp();
					break;
				case 2:
					break;
				case 3:
					break;
				case 4:
					currentPowerUp = new ExplodeBallPowerUp();
					break;
				case 5:
					currentPowerUp = new NoCollisionBallPowerUp();
					break;
				case 6:
					break;
				default:
					break;
			}

			switch(brickValue)
			{
				case "N":
					currentBrick = new Brick(BrickType.NORMAL, currentPowerUp);
					break;
				case "A":
					currentBrick = new Brick(BrickType.AIR, currentPowerUp);
					break;
				case "S":
					currentBrick = new Brick(BrickType.SOLID, currentPowerUp);
					break;
				case "H":
					currentBrick = new Brick(BrickType.HARD, currentPowerUp);
					break;
				case "E":
					currentBrick = new Brick(BrickType.EXTRA_HARD, currentPowerUp);
					break;
				case "I":
					currentBrick = new Brick(BrickType.INVISIBLE, currentPowerUp);
					break;
				case "T":
					currentBrick = new Brick(BrickType.TNT, currentPowerUp);
					break;
				default:
					currentBrick = new Brick(BrickType.AIR, currentPowerUp);
					break;
			}

			loadedBricks.add(currentBrick);
		}

		int currentRowNumber = 0;
		ArrayList<Brick> currentRow = new ArrayList<>();
		for(int i = 0; i < loadedBricks.size(); i++)
		{
			currentRow.add(loadedBricks.get(i));
			if(currentRow.size() == WIDTH)
			{
				bricks.set(currentRowNumber, currentRow);
				currentRow = new ArrayList<>();
				currentRowNumber++;
			}
		}
		print();
	}

	private ArrayList<Brick> getRemainingBricks()
	{
		ArrayList<Brick> remainingBricks = new ArrayList<>();
		for(ArrayList<Brick> row : bricks)
		{
			for(Brick currentBrick : row)
			{
				if(!(currentBrick.getType().equals(BrickType.AIR)) && !(currentBrick.getType().equals(BrickType.SOLID)))
				{
					remainingBricks.add(currentBrick);
				}
			}
		}
		return remainingBricks;
	}

	public int getNumberOfRemainingBricks()
	{
		return getRemainingBricks().size();
	}

	private void init()
	{
		bricks = new ArrayList<ArrayList<Brick>>();

		for(int i = 0; i < HEIGHT; i++)
		{
			ArrayList<Brick> currentRow = new ArrayList<>();
			for(int k = 0; k < WIDTH; k++)
			{
				currentRow.add(new Brick(BrickType.AIR));
			}
			bricks.add(currentRow);
		}
	}

	public ArrayList<ArrayList<Brick>> getBricks()
	{
		return bricks;
	}

	public void setBricks(ArrayList<ArrayList<Brick>> bricks)
	{
		this.bricks = bricks;
	}

	public double getWidth()
	{
		return WIDTH;
	}

	public double getHeight()
	{
		return HEIGHT;
	}

	public int hitBrick(int row, int col, Ball ball)
	{
		points = 0;

		switch(ball.getType())
		{
			case EXPLOSIVE:
				Brick currentBrick = bricks.get(row).get(col);
				if(currentBrick.getPowerUp() != null)
				{
					// deploys powerup
					game.getLevelController().addMovingPowerUp(row, col, currentBrick.getPowerUp());
				}

				bricks.get(row).set(col, new Brick(BrickType.TNT));
				destroyBrick(row, col, false, false);
				break;

			case NO_COLLISION:
				destroyBrick(row, col, true, true);
				break;

			default:
				destroyBrick(row, col, false, true);
				break;
		}

		return points;
	}

	public void destroyBrick(int row, int col, boolean instantDestroy, boolean playDestroySound)
	{
		Brick hittedBrick = bricks.get(row).get(col);

		if(hittedBrick.getType().equals(BrickType.AIR))
		{
			return;
		}

		// block was destroyed
		if(hittedBrick.hit(instantDestroy))
		{
			bricks.get(row).set(col, new Brick(BrickType.AIR));

			if(hittedBrick.getType().equals(BrickType.TNT))
			{
				explodeBrick(row, col);
				game.getSoundHandler().play(SoundType.TNT);
			}
			else
			{
				if(playDestroySound)
				{
					game.getSoundHandler().play(SoundType.DESTROY_BRICK);
				}
			}

			if(hittedBrick.getPowerUp() != null)
			{
				// deploys powerup
				game.getLevelController().addMovingPowerUp(row, col, hittedBrick.getPowerUp());
			}

			points += hittedBrick.getType().getPoints();

			if(hittedBrick.getType().getPoints() > 0)
			{
				game.getLevelController().showAnimatedPoints(row, col, hittedBrick.getType().getPoints(), game.getLevelController().getGamePaneHeight() * 0.03, false);
			}
			game.getLevelController().increaseMultiplicator(hittedBrick.getType().getPoints());			
			LevelController.redrawBrick(col, row, bricks.get(row).get(col), true);
		}
		else
		{
			game.getSoundHandler().play(SoundType.HIT_BRICK);
			LevelController.redrawBrick(col, row, bricks.get(row).get(col), false);
		}
	}

	private void explodeBrick(int row, int col)
	{
		int currentRow = row - 1;
		int currentCol = col - 1;

		for(int i = currentRow; i <= currentRow + 2; i++)
		{
			for(int k = currentCol; k <= currentCol + 2; k++)
			{
				if(i >= 0 && i < HEIGHT)
				{
					if(k >= 0 && k < WIDTH)
					{
						destroyBrick(i, k, true, true);
					}
				}
			}
		}
	}

	public String print()
	{
		StringBuilder b = new StringBuilder();
		for(int i = 0; i < HEIGHT; i++)
		{
			for(int k = 0; k < WIDTH; k++)
			{
				b.append(bricks.get(i).get(k).getType().getID());
				b.append(" ");
			}
			b.append("\n");
		}

		return b.toString();
	}

	@Override
	public String toString()
	{
		return "Board [WIDTH=" + WIDTH + ", HEIGHT=" + HEIGHT + ", bricks=" + bricks + "]";
	}
}