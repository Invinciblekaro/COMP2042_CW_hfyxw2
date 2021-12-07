package com.cw.controller.cells;

import com.cw.game.settings.GameSize;
import javafx.scene.control.cell.ComboBoxListCell;

public class ComboBoxResolutionCell extends ComboBoxListCell<GameSize>
{	
	@Override
	public void updateItem(GameSize item, boolean empty)
	{
		super.updateItem(item, empty);

		if( ! empty)
		{	
			setText(item.getWidth() + " x " + item.getHeight());					
		}
		else
		{
			setText(null);
		}
	}
}