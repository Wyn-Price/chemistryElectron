package com.wynprice.chemistry;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class ImageWriter 
{
	private final BufferedImage bufferedImage;
	private final Graphics2D graphics;
	
	private static final File BASE = new File("./images");
	static {
		BASE.mkdirs();
	}
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
			ImageIO.write(bufferedImage, "PNG", new File(BASE, name + ".png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
