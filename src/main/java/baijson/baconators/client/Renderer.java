package baijson.baconators.client;

import net.minecraft.client.renderer.Tessellator;
import net.minecraft.item.ItemStack;
import net.minecraft.util.IIcon;
import net.minecraftforge.client.IItemRenderer;
import org.lwjgl.opengl.GL11;

/**
 *
 */
public class Renderer implements IItemRenderer {

	/**
	 * @param stack
	 * @param type
	 *
	 * @return
	 */
	@Override
	public boolean handleRenderType ( ItemStack stack, ItemRenderType type ) {
		return type == ItemRenderType.INVENTORY;
	}

	/**
	 * @param type
	 * @param stack
	 * @param helper
	 *
	 * @return
	 */
	@Override
	public boolean shouldUseRenderHelper ( ItemRenderType type, ItemStack stack, ItemRendererHelper helper ) {
		return false;
	}

	/**
	 * @param type
	 * @param stack
	 * @param data
	 */
	@Override
	public void renderItem ( ItemRenderType type, ItemStack stack, Object... data ) {

		Tessellator tessellator = Tessellator.instance;
		GL11.glPushMatrix ( );
		GL11.glPushAttrib ( GL11.GL_ALL_ATTRIB_BITS );
		GL11.glEnable ( GL11.GL_ALPHA_TEST );
		GL11.glColor4f ( 0.8F, 0.8F, 0.8F, 0.8F );
		float zLevel = 50.0F;
		for ( int i = 0; i < 3; ++i ) {
			if ( i > 0 ) {
				zLevel = 100.0F;
			}
			IIcon icon = stack.getItem ( ).getIcon ( stack, i );
			tessellator.startDrawingQuads ( );
			tessellator.addVertexWithUV ( 0, 16, zLevel, icon.getMinU ( ), icon.getMaxV ( ) );
			tessellator.addVertexWithUV ( 16, 16, zLevel, icon.getMaxU ( ), icon.getMaxV ( ) );
			tessellator.addVertexWithUV ( 16, 0, zLevel, icon.getMaxU ( ), icon.getMinV ( ) );
			tessellator.addVertexWithUV ( 0, 0, zLevel, icon.getMinU ( ), icon.getMinV ( ) );
			tessellator.draw ( );
		}
		GL11.glPopAttrib ( );
		GL11.glPopMatrix ( );
	}
}