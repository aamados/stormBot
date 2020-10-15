package storm.bot.commands;

import java.util.ArrayList;

import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;

public interface Commands 
{	
	public String getCommandLabel();
	
	public ArrayList<String> getArgs();
	
	public int getArgLen();
	
	public void executeCommand(GuildMessageReceivedEvent e); 
	
	public String getHelp();
}
