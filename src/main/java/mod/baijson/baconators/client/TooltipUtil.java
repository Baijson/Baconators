package mod.baijson.baconators.client;

import net.minecraft.client.gui.GuiScreen;
import net.minecraft.client.resources.I18n;
import net.minecraftforge.fml.relauncher.Side;
import net.minecraftforge.fml.relauncher.SideOnly;

import java.util.List;

/**
 * File created by Baijson.
 *
 * Provide "shift-able" tooltips to items.
 */
@SideOnly( Side.CLIENT )
public class TooltipUtil {

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

		message = I18n.format ( message ).replace ( "&", "\u00a7" );

		tooltip.add ( "\u00a7r" + message );
	}
}