package storm.bot;

import java.util.HashMap;

import javax.security.auth.login.LoginException;

import net.dv8tion.jda.api.JDA;
import net.dv8tion.jda.api.JDABuilder;
import storm.bot.commands.CommandManager;
import storm.bot.commands.Commands;
import storm.bot.commands.Embed;
import storm.bot.events.EventListeners;
import storm.bot.events.Starboard;

public class Main {
	
	public static HashMap<String, Commands> cmds = new HashMap<>();
	public static String prefix = ".";
	public static String iconURL = "https://i.imgur.com/R9f8kEe.png";
	
	public static void main(String[] args) throws InterruptedException
	{
		JDA bot = null;
				
		try
		{
			
			String token = System.getenv("TOKEN");
			bot = JDABuilder.createDefault(token).build();		
		}
		catch(LoginException e)
		{
			System.out.println("Error:" + e.getMessage() + "\n> ");
			e.printStackTrace();
		}
		
		bot.addEventListener(
				new EventListeners(), new Embed(), new CommandManager(),
				new Starboard());
		
		bot.awaitReady();
		
		return;
	}

}
