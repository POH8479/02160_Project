package gui.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

/**
 * This Class sets the constraints with a GidLayout. 
 * @author Andrea Burattin
 */
public class GridBagLayoutUtils {
	/**
	 * Sets the constrains with a simple x y grid.
	 * @param x The X constraint
	 * @param y The Y constraint
	 * @param inset The Inset
	 * @return
	 */
	public static GridBagConstraints constraint(int x, int y, int inset) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.insets = new Insets(inset, inset, inset, inset);
		return c;
	}
}