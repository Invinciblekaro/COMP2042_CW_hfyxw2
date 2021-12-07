package com.cw.game.powerups.ball;

import com.cw.game.Game;
import com.cw.game.balls.Ball;
import com.cw.game.balls.BallType;
import com.cw.game.powerups.PowerUp;
import com.cw.game.powerups.PowerUpType;
import com.cw.controller.LevelController;

public class NoCollisionBallPowerUp extends PowerUp
{
    public NoCollisionBallPowerUp()
    {
    	  super(PowerUpType.NO_COLLISION_BALL.getID(), PowerUpType.NO_COLLISION_BALL.getDurationInSeconds());
    }

    @Override
    public void activate(LevelController levelController, Game game)
    {
    	levelController.changeBall(new Ball(BallType.NO_COLLISION, levelController.getGamePaneHeight()));
    }

	@Override
	public void deactivate(LevelController levelController, Game game)
	{	
		levelController.changeBall(new Ball(BallType.NORMAL, levelController.getGamePaneHeight()));
	}
}