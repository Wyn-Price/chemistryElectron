package com.wynprice.chemistry;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;

public class ImageWriter 
{
	private final BufferedImage bufferedImage;
	private final Graphics2D graphics;
	
	public ImageWriter(int xSize, int ySize) 
	{
		bufferedImage = new BufferedImage(xSize, ySize, BufferedImage.TYPE_INT_ARGB);
		graphics = bufferedImage.createGraphics();
	}
	
	public Graphics2D getGraphics() {
		return graphics;
	}
	
	public void build(String name)
	{
		try {
			ImageIO.write(bufferedImage, "PNG", new File(new File(ImageWriter.class.getProtectionDomain().getCodeSource().getLocation().toURI().getPath()).getParentFile().getAbsolutePath() + "\\images\\" + name + ".png"));
		} catch (IOException | URISyntaxException e) {
			e.printStackTrace();
		}
	}
}
