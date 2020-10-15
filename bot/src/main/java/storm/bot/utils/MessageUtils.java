package storm.bot.utils;

import net.dv8tion.jda.api.events.message.guild.GenericGuildMessageEvent;

public class MessageUtils 
{
	public void sendErrorMessage(String message, GenericGuildMessageEvent event)
	{
		event.getChannel().sendMessage("```Error: " + message + "```").queue();
		return;
	}
	
	public void sendMessage(String message, GenericGuildMessageEvent event)
	{
		event.getChannel().sendMessage("```" + message + "```").queue();
		return;
	}
}
