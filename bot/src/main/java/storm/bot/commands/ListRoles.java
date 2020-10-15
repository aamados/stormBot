package storm.bot.commands;

import java.awt.Color;
import java.util.ArrayList;

import net.dv8tion.jda.api.EmbedBuilder;
import net.dv8tion.jda.api.MessageBuilder;
import net.dv8tion.jda.api.entities.Role;
import net.dv8tion.jda.api.events.message.guild.GuildMessageReceivedEvent;
import storm.bot.Main;
import storm.bot.utils.MessageUtils;

public class ListRoles implements Commands
{
	MessageUtils util = new MessageUtils();
	ArrayList<String> commandArgs = new ArrayList<>();

	@Override
	public String getCommandLabel() 
	{
		return Main.prefix + "listRoles";
	}

	@Override
	public ArrayList<String> getArgs() 
	{
		// No arguments for this command
		return new ArrayList<String>();
	}

	@Override
	public int getArgLen() 
	{
		return commandArgs.size();
	}

	@Override
	public void executeCommand(GuildMessageReceivedEvent e) 
	{
		if (!(e.getChannel().getName().contentEquals("roles")))
		{
			util.sendErrorMessage("This command must be executed in the #roles channel.", e);
			return;
		}
		
		EmbedBuilder embed = new EmbedBuilder();
		MessageBuilder string = new MessageBuilder();
		
		// These values represent a light-blue RGB value.
		embed.setColor(new Color(11, 115, 227));
		
		embed.setTitle("All roles in " + e.getGuild().getName());
		
		e.getChannel().sendMessage(embed.build()).queue();
		
		// These ```s are for formatting.
		string.append("```\n");
		
		// Loops through all roles in the sever.
		for (Role r : e.getGuild().getRoles())
		{
			
			// We don't want to list @everyone.
			if (r.getName().equalsIgnoreCase("@everyone"))
			continue;
			
			string.append(r.getName() + "\n");
			
		}
		
		// End of formatting, ready to send!
		string.append("```");
		
		e.getChannel().sendMessage(string.build()).queue();
		
		return;
	}

	@Override
	public String getHelp() 
	{
		return "This command lists all roles in this server.";
	}
}
