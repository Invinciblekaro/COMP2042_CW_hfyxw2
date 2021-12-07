package com.cw.commandLine.commands;

import com.cw.controller.LevelSelectController;

/**
 * Restarts current level
 * @author Wang_xubin
 *
 */
public class CommandRestart extends Command
{
	public CommandRestart()
	{		
		super.keyword = "restart";		
		super.numberOfParams = 0;
		super.helptText = "help.restart";
	}

	@Override
	public void execute(String[] command, CommandBundle bundle)
	{		
		if(bundle.getLevelController() != null)
		{			
			LevelSelectController levelSelectController = bundle.getLevelController().restartLevel();
			levelSelectController.startLevel();
		
			bundle.getController().print(bundle.getLanguageBundle().getString("success.restart"));
		}
		else
		{
			bundle.getController().print(bundle.getLanguageBundle().getString("error.no.level"));
		}
	}
}