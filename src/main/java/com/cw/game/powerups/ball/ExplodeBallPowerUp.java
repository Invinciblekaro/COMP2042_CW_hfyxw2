package com.cw.game.powerups.ball;


import com.cw.controller.LevelController;
import com.cw.game.Game;
import com.cw.game.balls.Ball;
import com.cw.game.balls.BallType;
import com.cw.game.powerups.PowerUp;
import com.cw.game.powerups.PowerUpType;

public class ExplodeBallPowerUp extends PowerUp
{
    public ExplodeBallPowerUp()
    {
    	  super(PowerUpType.EXPLODE_BALL.getID(), PowerUpType.EXPLODE_BALL.getDurationInSeconds());
    }

    @Override
    public void activate(LevelController levelController, Game game)
    {
    	levelController.changeBall(new Ball(BallType.EXPLOSIVE, levelController.getGamePaneHeight()));
    }

	@Override
	public void deactivate(LevelController levelController, Game game)
	{		
		levelController.changeBall(new Ball(BallType.NORMAL, levelController.getGamePaneHeight()));
	}
}