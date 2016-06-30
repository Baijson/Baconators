package baijson.baconators.client;

import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;
import net.minecraft.client.gui.GuiScreen;
import net.minecraft.util.StatCollector;

import java.util.List;

/**
 * Provide "shift-able" tooltips to items.
 */
@SideOnly( Side.CLIENT )
public class Tooltips {

	/**
	 * @param tooltip
	 * @param runnable
	 */
	static public void construct ( List<String> tooltip, Runnable runnable ) {
		if ( GuiScreen.isShiftKeyDown ( ) ) {
			runnable.run ( );
		} else {
			insert ( tooltip, "item.tooltip.before" );
		}
	}

	/**
	 * @param tooltip
	 * @param message
	 */
	static public void insert ( List<String> tooltip, String message ) {
		message = StatCollector.translateToLocal ( message ).replace ( "&", "\u00a7" );

		tooltip.add ( "\u00a7r" + message );
	}
}
