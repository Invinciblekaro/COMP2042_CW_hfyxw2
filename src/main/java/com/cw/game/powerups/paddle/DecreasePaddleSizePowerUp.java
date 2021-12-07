package com.cw.game.powerups.paddle;

import com.cw.game.Game;
import com.cw.game.paddle.Paddle;
import com.cw.game.paddle.PaddleSize;
import com.cw.game.powerups.PowerUp;
import com.cw.controller.LevelController;


public class DecreasePaddleSizePowerUp extends PowerUp
{
    private Paddle paddle;

    public DecreasePaddleSizePowerUp(int id, int duration, Paddle paddle)
    {
        super(id, duration);
        this.paddle = paddle;
    }

    @Override
    public void activate(LevelController levelController, Game game)
    {
        paddle.setPaddleSize(PaddleSize.getNextSmaller(paddle.getPaddleSize()));
    }

	@Override
	public void deactivate(LevelController levelController, Game game)
	{				
	}
}