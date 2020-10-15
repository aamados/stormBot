package storm.bot.utils;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.Scanner;

import javax.imageio.ImageIO;

public class HttpUtils 
{
	// Get response from URL request
	public String getResponse(URLConnection con)
	{
		Scanner s = null;
		
		try
		{
			s = new Scanner(con.getInputStream());
		}
		catch (IOException e)
		{
			System.out.println("Error in getResponse() in HttpUtils."
					+ e.getMessage());
		}
		
		String response = s.useDelimiter("\\A").next();
		s.close();
		
		return response;
	}
	
	public BufferedImage getImage(String url)
	{
		URL dogURL;
		BufferedImage dogImage = null;
		
		try
		{
			dogURL = new URL(url);
			dogImage = ImageIO.read(dogURL);
			
		}
		catch (IOException e)
		{
			System.out.println("Exception in getImage() in HttpUtils. "
					+ e.getMessage());
		}
		
		return dogImage;
	}
}
