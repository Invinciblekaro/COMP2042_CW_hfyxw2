package com.cw.commandLine.commands;

/**
 * Lists all available Shortcuts
 * @author Wang_xubin
 *
 */
public class CommandShortcuts extends Command
{
	public CommandShortcuts()
	{		
		super.keyword = "shortcuts";		
		super.numberOfParams = 0;
		super.helptText = "help.shortcuts";
	}

	@Override
	public void execute(String[] command, CommandBundle bundle)
	{		
		bundle.getController().print("Available Shortcuts:");
		bundle.getController().print(bundle.getLanguageBundle().getString("info.shortcuts"));
	}
}