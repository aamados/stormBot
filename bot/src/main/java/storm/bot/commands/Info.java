package storm.bot.commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import storm.bot.Main;
import storm.bot.utils.MessageUtils;

public class Info implements Commands
{
	private MessageUtils msg = new MessageUtils();
	private ArrayList<String> argList = new ArrayList<>();

	@Override
	public String getCommandLabel() 
	{
		return Main.prefix + "info";
	}

	@Override
	public ArrayList<String> getArgs() 
	{
		return argList;
	}
	
	@Override
	public int getArgLen()
	{
		return argList.size();
	}

	@Override
	public void executeCommand(GuildMessageReceivedEvent e) 
	{
		msg.sendMessage("This bot was made by st0rm#8608", e);
		return;
	}

	@Override
	public String getHelp() 
	{
		
		return "Displays basic bot information";
	}

}
