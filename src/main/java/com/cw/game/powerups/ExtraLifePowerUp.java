package com.cw.game.powerups;

import com.cw.game.Game;
import com.cw.controller.LevelController;

public class ExtraLifePowerUp extends PowerUp
{
    public ExtraLifePowerUp()
    {
        super(PowerUpType.EXTRA_LIFE.getID(), PowerUpType.EXTRA_LIFE.getDurationInSeconds());
    }

	@Override
	public void activate(LevelController levelController, Game game)
	{		
		if(game.getLivesRemaining() - 1 < game.getMaxLives() - 1)
		{
			game.setLivesRemaining(game.getLivesRemaining() + 1);			
			levelController.refreshLiveCounter();
		}
	}

	@Override
	public void deactivate(LevelController levelController, Game game)
	{		
	}   
}