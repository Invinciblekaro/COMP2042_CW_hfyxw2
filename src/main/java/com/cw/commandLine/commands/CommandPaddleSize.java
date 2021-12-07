package com.cw.commandLine.commands;

import com.cw.game.paddle.PaddleSize;
import com.cw.controller.LevelController;

/**
 * Changes the paddle size
 * 
 * @author Wang_xubin
 *
 */
public class CommandPaddleSize extends Command
{
	public CommandPaddleSize()
	{
		super.keyword = "paddleSize";
		super.numberOfParams = 1;
		super.helptText = "help.paddleSize";
	}

	@Override
	public void execute(String[] command, CommandBundle bundle)
	{
		if(!isValid(command))
		{
			bundle.getController().print(bundle.getLanguageBundle().getString("error.invalid.arguments"));
			return;
		}

		LevelController levelController = bundle.getLevelController();
		if(levelController != null)
		{
			try
			{
				int size = Integer.parseInt((command[1]));
				bundle.getLevelController().initPaddle(PaddleSize.getPaddleSize(size));
			}
			catch(Exception e)
			{
				bundle.getController().print(bundle.getLanguageBundle().getString("error.invalid.arguments"));
			}
		}
		else
		{
			bundle.getController().print("Can't change paddleSize without a paddle");
		}
		// implement your code here
	}
}