package storm.bot.commands;

import java.io.IOException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import storm.bot.Main;
import storm.bot.utils.EmbedUtils;
import storm.bot.utils.HttpUtils;

public class Dog implements Commands
{

	@Override
	public String getCommandLabel() 
	{
		return Main.prefix + "dog";
	}

	@Override
	public ArrayList<String> getArgs() 
	{
		return null;
	}

	@Override
	public int getArgLen() 
	{
		return 0;
	}

	@Override
	public void executeCommand(GuildMessageReceivedEvent e)
	{
		HttpUtils hutils = new HttpUtils();
		URLConnection con;
		String response;	
		
		try
		{
			con = new URL("https://random.dog/woof.json").openConnection();
		}
		catch (IOException exception)
		{
			System.out.println("Exception in executeCommand() in Dog. "
					+ exception.getMessage());
			return;
		}
		
		response = hutils.getResponse(con);
		Gson gson = new Gson();
		EmbedUtils eutils = new EmbedUtils();
		JsonObject responseJson = gson.fromJson(response, JsonObject.class);
		String urlValue = responseJson.get("url").getAsString();
		
		// This API has .mp4 files in it. We don't want that.
		if (urlValue.endsWith(".mp4"))
		{
			executeCommand(e);
			return;
		}
		
		EmbedBuilder embed = eutils.getEmbedTemplate();
		embed.setImage(urlValue);
		
		e.getChannel().sendMessage(embed.build()).queue();
		
		return;
	}

	@Override
	public String getHelp() 
	{
		return "Sends a random dog image to the chat";
	}

}
