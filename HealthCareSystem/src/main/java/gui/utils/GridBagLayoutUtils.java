package gui.utils;

import java.awt.GridBagConstraints;
import java.awt.Insets;

public class GridBagLayoutUtils {

	public static GridBagConstraints constraint(int x, int y, int inset) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.insets = new Insets(inset, inset, inset, inset);
		return c;
	}
	
	public static GridBagConstraints constraint(int x, int y, int inset, int width) {
		GridBagConstraints c = new GridBagConstraints();
		c.gridx = x;
		c.gridy = y;
		c.insets = new Insets(inset, inset, inset, inset);
		c.gridwidth = width;
		return c;
	}
}