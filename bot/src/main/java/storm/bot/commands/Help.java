package storm.bot.commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import storm.bot.Main;

public class Help implements Commands
{

	@Override
	public String getCommandLabel() 
	{
		return Main.prefix + "help";
	}

	@Override
	public ArrayList<String> getArgs() 
	{
		return null;
	}

	@Override
	public int getArgLen() 
	{
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void executeCommand(GuildMessageReceivedEvent e) 
	{
		StringBuilder message = new StringBuilder("```Commands:\n");
		
		
		for (String key : Main.cmds.keySet())
		{
			message.append("\t " + key + " - " + Main.cmds.get(key).getHelp() + "\n");
		}
		
		message.append("```");
		
		e.getChannel().sendMessage(message).queue();;
		
	}

	@Override
	public String getHelp() 
	{
		return "Displays information on how to use the bot.";
	}

}
